package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed;

import java.util.ArrayList;

import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators.FavoritesPlaylistIterator;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators.ShuffledPlaylistIterator;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators.SimplePlaylistIterator;

public class Playlist {
    private ArrayList<String> songs;

    public Playlist(){
        songs = new ArrayList<>();
    }

    public void addSong(String song){
        songs.add(song);
    }

    public PlaylistIterator iterator(String type){
        switch(type){
            case "simple":
                return new SimplePlaylistIterator(this);
            case "shuffled":
                return new ShuffledPlaylistIterator(this);
            case "favorites":
                return new FavoritesPlaylistIterator(this);
            default:
                return null;

        }
    }

    public ArrayList<String> getSongs(){
        return songs;
    }
}
