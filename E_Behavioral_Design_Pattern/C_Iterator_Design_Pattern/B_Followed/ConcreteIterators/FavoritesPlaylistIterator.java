package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.ConcreteIterators;

import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.Playlist;
import E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed.PlaylistIterator;

public class FavoritesPlaylistIterator implements PlaylistIterator{
    private Playlist playlist;
    private int index;

    public FavoritesPlaylistIterator(Playlist playlist){
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext(){
        while(index < playlist.getSongs().size()){
            if(playlist.getSongs().get(index).contains("Fav")){
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public String next(){
        return playlist.getSongs().get(index++);
    }
}
