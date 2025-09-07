package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators;

import java.util.ArrayList;
import java.util.Collections;

import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.Playlist;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.PlaylistIterator;

public class ShuffledPlaylistIterator implements PlaylistIterator{
    private Playlist playlist;
    private ArrayList<String> shuffledSongs;
    private int index;
    
    public ShuffledPlaylistIterator(Playlist playlist){
        this.playlist = playlist;
        this.shuffledSongs = new ArrayList<>(playlist.getSongs());
        Collections.shuffle(shuffledSongs);
        this.index = 0;
    }

    @Override
    public boolean hasNext(){
        return index < shuffledSongs.size();
    }

    @Override
    public String next(){
        return shuffledSongs.get(index++);
    }
}
