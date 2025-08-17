package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subjects;

import java.util.ArrayList;
import java.util.List;

import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface.Subscriber;
import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface.YouTubeChannel;

// Subjects are the publishers

public class YouTubeChannelSubject implements YouTubeChannel {
    List<Subscriber> subscribers = new ArrayList<>();
    private String video;

    @Override
    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String video){
        for(Subscriber subscriber : subscribers) {
            subscriber.update(video);
        }
    }

    public void uploadNewVideo(String video){
        this.video = video;
        notifySubscribers(video);
    }
}
