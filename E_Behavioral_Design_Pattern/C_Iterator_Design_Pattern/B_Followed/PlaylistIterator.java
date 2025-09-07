package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed;

// 1) Let's solve the not followed code problem using the Iterator Design Pattern!
// 
// Key Points:
// • Instead of directly modifying the playPlaylist() method
// • We will define an iterator to abstract the iteration logic
// • This allows us to easily add new functionality
// • Without modifying the core logic of the Playlist class
// 
// Benefits:
// ✓ Clean separation of concerns
// ✓ Enhanced code maintainability
// ✓ Flexible iteration strategies
// ✓ Future-proof design


public interface PlaylistIterator {
    boolean hasNext(); //check if there is a next song
    String next(); //get the next song
}

// We'll create three different iterators for different playlist types: Simple Playlist, Shuffled Playlist, and Favorites Playlist.
