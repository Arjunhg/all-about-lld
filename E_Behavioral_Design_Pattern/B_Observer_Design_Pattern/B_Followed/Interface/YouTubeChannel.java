package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface;

public interface YouTubeChannel {
    void subscribe(Subscriber subscriber); // Method to add a subscriber
    void unsubscribe(Subscriber subscriber); // Method to remove a subscriber
    void notifySubscribers(String video); // Method to notify all subscribers about a new video
}
