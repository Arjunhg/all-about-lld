package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Topic;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.TopicSubscriber;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.PublisherInterface;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.SubscriberInterface;

public class KafkaController {
    private final Map<String, Topic> topics; // topicId -> Topic mapping, 
    private final Map<String, List<TopicSubscriber>> topicSubscribers; // topicId -> list of TopicSubscribers mapping

    // executor service to run concurrent subscriber message consumption
    private final ExecutorService subscriberExecutor;
    private final AtomicInteger topicIdCounter; 

    public KafkaController(){
        topics = new ConcurrentHashMap<>();
        topicSubscribers = new ConcurrentHashMap<>();
        subscriberExecutor = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName){
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Topic created with id: " + topicId + " and name: " + topicName);
        return topic;
    }

    public void subscribe(SubscriberInterface subscriber, String topicId){
        Topic topic = topics.get(topicId);
        if(topic == null){
            System.out.println("Topic with id " + topicId + " does not exist.");
            return;
        }
        TopicSubscriber topicSubscriber = new TopicSubscriber(topic, subscriber);
        topicSubscribers.get(topicId).add(topicSubscriber);
        System.out.println("Subscriber " + subscriber.getId() + " subscribed to topic " + topicId);
        subscriberExecutor.submit(new TopicSubscriberController(topicSubscriber));
    }

    public void publish(PublisherInterface publisher, String topicId, Message message){
        Topic topic = topics.get(topicId);
        if(topic == null){
            throw new IllegalArgumentException("Topic with id " + topicId + " does not exist.");
        }
        topic.addMessage(message);

        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        for(TopicSubscriber topicSubscriber : subscribers){
            synchronized(topicSubscriber){
                topicSubscriber.notify();
            }
        }

        System.out.println("Publisher " + publisher.getId() + " published message to topic " + topicId);
    }

    public void resetOffset(String topicId, SubscriberInterface subscriber, int newOffset){
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if(subscribers == null){
            System.out.println("Topic with id " + topicId + " does not exist.");
            return;
        }
        for(TopicSubscriber topicSubscriber : subscribers){
            if(topicSubscriber.getSubsciber().getId().equals(subscriber.getId())){
                topicSubscriber.getOffset().set(newOffset);
                // notify the subscriber thread in case it is waiting for new messages
                synchronized(topicSubscriber){
                    topicSubscriber.notify();
                }
                System.out.println("Subscriber " + subscriber.getId() + " offset reset to " + newOffset + " for topic " + topicId);
                break;
            }
        }
    }

    public void shutdown(){
        subscriberExecutor.shutdown();
        try {
            if (!subscriberExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                subscriberExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            subscriberExecutor.shutdownNow();
        }
    }
}
