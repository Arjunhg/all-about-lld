package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.A_NotFollowed;

// Let’s say you have a simple playlist class, and you want to iterate over the songs using the traditional approach

import java.util.ArrayList;

public class Playlist {
    private ArrayList<String> songs;

    public Playlist(){
        songs = new ArrayList<>();
    }

    public void addSongs(String song){
        songs.add(song);
    }

    public void playPlaylist(boolean shuffle){
        if(shuffle){
            // Shuffle the songs and then play
            System.out.println("Shuffling playlist...");
        }else{
            for(int i=0; i<songs.size(); i++){
                System.out.println("Playing: " + songs.get(i));
            }
        }
    }
}

// Issues with the current approach:
// • The playPlaylist() method has become cluttered with additional functionality for shuffling
// • Adding more features like repeat functionality will make the method even messier
// • Adding filtering songs capability will increase code complexity
// • Violates Single Responsibility Principle - method handles both iteration and additional features
// • Difficult to maintain and extend with new iteration behaviors
// • Tight coupling between playlist management and iteration logic
