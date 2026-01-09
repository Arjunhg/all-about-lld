package J_LLD_Concurrency_Problems.B_Design_Pub_Sub;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Controller.KafkaController;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Topic;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.ConcretePublisher.SimplePublisher;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.ConcreteSubscriber.SimpleSubscriber;

public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController = new KafkaController();

        Topic topic1 = kafkaController.createTopic("Topic-1");
        Topic topic2 = kafkaController.createTopic("Topic-2");

        SimpleSubscriber subscriber1 = new SimpleSubscriber("Subscriber-1");
        SimpleSubscriber subscriber2 = new SimpleSubscriber("Subscriber-2");
        SimpleSubscriber subscriber3 = new SimpleSubscriber("Subscriber-3");

        kafkaController.subscribe(subscriber1, topic1.getTopicId());
        kafkaController.subscribe(subscriber1, topic2.getTopicId());
        kafkaController.subscribe(subscriber2, topic1.getTopicId());
        kafkaController.subscribe(subscriber3, topic2.getTopicId());

        SimplePublisher publisher1 = new SimplePublisher("Publisher-1", kafkaController);
        SimplePublisher publisher2 = new SimplePublisher("Publisher-2", kafkaController);

        publisher1.publish(topic1.getTopicId(), new Message("Message m1"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m2"));
        publisher2.publish(topic2.getTopicId(), new Message("Message m3"));

        // Allow time for subscribers to process messages.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publisher2.publish(topic2.getTopicId(), new Message("Message m4"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m5"));

        // Reset offset for subscriber1 on topic1
        kafkaController.resetOffset(topic1.getTopicId(), subscriber1, 0);

        // Allow some time before shutting down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaController.shutdown();
    }
}
