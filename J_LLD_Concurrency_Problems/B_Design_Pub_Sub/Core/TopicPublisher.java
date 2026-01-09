package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher.PublisherInterface;

public class TopicPublisher {
    private final Topic topic;
    private final PublisherInterface publisher;

    public TopicPublisher(Topic topic, PublisherInterface publisher){
        this.topic = topic;
        this.publisher = publisher;
    }

    public Topic getTopic() {
        return topic;
    }

    public PublisherInterface getPublisher() {
        return publisher;
    }
}
