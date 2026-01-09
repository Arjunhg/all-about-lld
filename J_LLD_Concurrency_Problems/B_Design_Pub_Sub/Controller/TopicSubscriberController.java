package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Controller;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Topic;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.TopicSubscriber;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.SubscriberInterface;

public class TopicSubscriberController implements Runnable {
    private final TopicSubscriber topicSubscriber;

    public TopicSubscriberController(TopicSubscriber topicSubscriber){
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run(){
        Topic topic = topicSubscriber.getTopic();
        SubscriberInterface subscriber = topicSubscriber.getSubsciber();
        while(true){
            Message messageToProcess = null;
            synchronized(topicSubscriber){
                //  Check if there are new messages to process (offset < topic.messages.size())
                while(topicSubscriber.getOffset().get() >= topic.getMessages().size()){
                    try{
                        topicSubscriber.wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // Get the next message to process and increment the offset
                int currentOffset = topicSubscriber.getOffset().getAndIncrement();
                messageToProcess = topic.getMessages().get(currentOffset);
            }
            // Process the message outside the synchronized block
            try{
                subscriber.OnMessage(messageToProcess);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
