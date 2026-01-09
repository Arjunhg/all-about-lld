package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages;

    public Topic(String topicName, String topicId){
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public synchronized void addMessage(Message message) {
        this.messages.add(message);
    }

    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}

/* Some important points:

- In a typical Pub/Sub pattern, topics should NOT be aware of publishers.

-Key Principles:
    Decoupling: Publishers and subscribers are decoupled through topics

        Publishers only need to know which topic to publish to
        Topics are just message containers/channels
        Subscribers only need to know which topics to subscribe to
        
    Publisher is just a client: Publishers are clients that send messages to topics, similar to how subscribers are clients that consume from topics.

*/