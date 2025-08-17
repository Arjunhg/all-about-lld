package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subscribers;

import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface.Subscriber;

public class YouTubeSubscriber implements Subscriber {
    private String channelName;

    public YouTubeSubscriber(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void update(String content) {
        System.out.println("New video uploaded to " + channelName + ": " + content);
    }

    public String getChannelName() {
        return channelName;
    }
    
}
