package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subjects.YouTubeChannelSubject;
import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subscribers.YouTubeSubscriber;

public class Main {
    public static void main(String[] args) {
        
        YouTubeChannelSubject channel = new YouTubeChannelSubject();

        YouTubeSubscriber subscriber1 = new YouTubeSubscriber("Alice");

        channel.subscribe(subscriber1);

        channel.uploadNewVideo("New Video Added");

    }
}
