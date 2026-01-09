package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core;

import java.util.concurrent.atomic.AtomicInteger;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.SubscriberInterface;

public class TopicSubscriber {
    private final Topic topic;
    private final SubscriberInterface subsciber;
    private final AtomicInteger offset;

    public TopicSubscriber(Topic topic, SubscriberInterface subsciber){
        this.topic = topic;
        this.subsciber = subsciber;
        this.offset = new AtomicInteger(0);
    }

    public Topic getTopic() {
        return topic;
    }

    public SubscriberInterface getSubsciber() {
        return subsciber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
