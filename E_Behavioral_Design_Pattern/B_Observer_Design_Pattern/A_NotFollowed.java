package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

/*
 * Scenario:
 * 1. You have a YouTube channel.
 * 2. You want to notify your subscribers every time a new video is uploaded.
 * 3. Traditionally, subscribers might manually check for updates each time.
 *
 * Traditional Approach:
 * - The YouTubeChannel acts as the subject.
 * - The YouTubeSubscriber acts as the observer.
 * - Notification is not automatic; subscribers must check for new videos themselves.
 */

class YouTubeChannel {
    private List<String> subscribers = new ArrayList<>();
    private String video;

    public String getVideo() {
        return video;
    }

    // Add new subscriber
    public void addSubscriber(String subscriber){
        subscribers.add(subscriber);
    }
    // upload new video
    public void videoUpload(String video){
        this.video = video;
        notifySubscribers();
    }

    public void notifySubscribers(){
        for(String subscriber : subscribers){
            System.out.println("Notifying " + subscriber + " about new video: " + video);
        }
    }
}

class YouTubeSubscriber{
    private String name;

    public YouTubeSubscriber(String name){
        this.name = name;
    }

    public void subscribe(YouTubeChannel channel){
        channel.addSubscriber(name);
    }

    public void watchVideo(YouTubeChannel channel){
        System.out.println(name + " is watching the latest video: " + channel.getVideo());
    }
}

public class A_NotFollowed {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();
        YouTubeSubscriber subscriber1 = new YouTubeSubscriber("Alice");
        YouTubeSubscriber subscriber2 = new YouTubeSubscriber("Bob");

        // Subscribers subscribe to the channel
        subscriber1.subscribe(channel);
        subscriber2.subscribe(channel);

        // Channel uploads a new video
        channel.videoUpload("Observer Design Pattern Explained");

        // Subscribers watch the video
        subscriber1.watchVideo(channel);
        subscriber2.watchVideo(channel);
    }
}

/*
 * Why Is This Approach Not Ideal? 
 *
 * 1. Manual Checking:
 *    - Each subscriber must be notified manually every time a new video is uploaded.
 *    - Becomes cumbersome and inefficient with a large number of subscribers.
 *
 * 2. Not Scalable:
 *    - Adding new notification methods (e.g., email, SMS) requires modifying the YouTubeChannel class.
 *    - Leads to tight coupling and makes maintenance difficult.
 *
 * 3. Hard to Extend:
 *    - Adding more observers (e.g., app notifications) requires changes to the YouTubeChannel class.
 *    - Violates the open/closed principle, making the system less flexible and harder to extend.
 */
