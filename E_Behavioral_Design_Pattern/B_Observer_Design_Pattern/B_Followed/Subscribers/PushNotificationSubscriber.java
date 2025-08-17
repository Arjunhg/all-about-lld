package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subscribers;

import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface.Subscriber;

public class PushNotificationSubscriber implements Subscriber {
    private String deviceToken;

    public PushNotificationSubscriber(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public void update(String content) {
        System.out.println("Push notification sent to device " + deviceToken + " with content: " + content);
    }

    public String getDeviceToken() {
        return deviceToken;
    }
    
}
