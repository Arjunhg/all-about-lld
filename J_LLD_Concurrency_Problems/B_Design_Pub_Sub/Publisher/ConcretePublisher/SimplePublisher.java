package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.ConcretePublisher;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Controller.KafkaController;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.PublisherInterface;

public class SimplePublisher implements PublisherInterface {
    private final String id;
    private final KafkaController kafkaController;

    public SimplePublisher(String id, KafkaController kafkaController){
        this.id = id;
        this.kafkaController = kafkaController;
    }
    
    @Override
    public String getId(){
        return id;
    }

    @Override
    public void publish(String topicId, Message message) throws IllegalArgumentException{ 
        kafkaController.publish(this, topicId, message);
        System.out.println("Publisher " + id + " published message to topic " + topicId);
    }
}

/* Why pass 'this'
Passing 'this' lets KafkaController know which publisher is publishing the message. For example, it might:

    Read the publisher’s ID via publisher.getId()
    Log who sent the message
    Apply permissions or routing logic based on the publisher’s
*/
