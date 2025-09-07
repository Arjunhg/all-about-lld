package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.Playlist;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.PlaylistIterator;

public class SimplePlaylistIterator implements PlaylistIterator {
    private Playlist playlist;
    private int index;

    public SimplePlaylistIterator(Playlist playlist){
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext(){
        return index < playlist.getSongs().size();
    }

    @Override
    public String next(){
        return playlist.getSongs().get(index++);
    }
}
