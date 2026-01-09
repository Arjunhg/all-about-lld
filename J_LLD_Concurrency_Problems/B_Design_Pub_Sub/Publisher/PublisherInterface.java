package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Publisher;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;

// Used by publishers to implement publisher methods for sending messages to topics
/* Why not directly use Publisher class?
*/

public interface PublisherInterface {
    String getId(); // unique id for each publisher
    void publish(String topicId, Message message) throws IllegalArgumentException;
}