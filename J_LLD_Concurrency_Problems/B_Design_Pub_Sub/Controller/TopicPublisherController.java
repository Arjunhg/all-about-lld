package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Controller;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Topic;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.PublisherInterface;

public class TopicPublisherController {
    private final Topic topic;
    private final PublisherInterface publisher;

    public TopicPublisherController(Topic topic, PublisherInterface publisher){
        this.topic = topic;
        this.publisher = publisher;
    }

    public synchronized void publish(Message message, KafkaController controller){
        controller.publish(publisher, topic.getTopicId(), message);
        System.out.println("Publisher " + publisher.getId() + " published message to topic " + topic.getTopicId());
    }
}
