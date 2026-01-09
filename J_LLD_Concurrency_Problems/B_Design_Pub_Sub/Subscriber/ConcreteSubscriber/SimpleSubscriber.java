package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.ConcreteSubscriber;

import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core.Message;
import J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Subscriber.SubscriberInterface;

public class SimpleSubscriber implements SubscriberInterface {
    private final String id;

    public SimpleSubscriber(String id){
        this.id = id;
    }
    
    @Override
    public String getId(){
        return id;
    }

    @Override
    public void OnMessage(Message message) throws InterruptedException{
        System.out.println("Subscriber " + id + " received message: " + message.getMessage());
    }
}
