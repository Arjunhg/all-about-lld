package J_LLD_Concurrency_Problems.B_Design_Pub_Sub.Core;

public class Message {
    private final String message;
    public Message(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
