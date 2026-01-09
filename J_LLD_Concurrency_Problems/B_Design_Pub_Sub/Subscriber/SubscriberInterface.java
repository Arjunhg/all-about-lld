package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;

public interface SubscriberInterface {
    String getId();
    void OnMessage(Message message) throws InterruptedException;
}

// But we want to give each subscriber the ability to chose which topics they want to subscribe.
// Currently all subscribers are forced to subscribe to all topics. Because subscriber doesn't have the topicID associated with it.