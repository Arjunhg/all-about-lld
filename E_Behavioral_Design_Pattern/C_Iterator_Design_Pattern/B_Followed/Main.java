package E_Behavioral_Design_Pattern.C_Iterator_Design_Pattern.B_Followed;

// 🎯 Iterator Design Pattern - Your Collection Navigation Toolkit!
//
// What is it?
// • A behavioral design pattern that lets you traverse collections
// • No need to know the internal structure - just iterate!
// • Think of it as your personal tour guide through data
//
// 🎶 Real-Life Scenario: Music Playlist Iterator
//
// Imagine you're building the next Spotify! 🎵
// • Users create playlists with their favorite songs
// • They want to play songs one by one
// • But wait... there are different playlist types!
//
// Different Playlist Flavors:
// • 📝 Simple Playlist: Songs in the order they were added
// • 🔀 Shuffled Playlist: Random song order for variety
// • ⭐ Favorites Playlist: Only the starred tracks
//
// The Challenge:
// • How do we iterate through each type differently?
// • Without writing separate code for each playlist type?
// • While keeping our code clean and maintainable?
//
// 💡 Solution: Iterator Pattern to the rescue!
// • One unified way to access all playlist types
// • Hide the complexity behind a simple interface
// • Let each playlist handle its own iteration logic


public class Main {
    public static void main(String[] args) {
        
        Playlist playlist = new Playlist();
        playlist.addSong("Song 1");
        playlist.addSong("Fav Song 2");
        playlist.addSong("Song 3");
        playlist.addSong("Fav Song 4");
        playlist.addSong("Song 5");

        // Simple Playlist Iterator
        System.out.println("Simple Playlist: ");
        PlaylistIterator simpleIteraor = playlist.iterator("simple");
        while(simpleIteraor.hasNext()){
            System.out.println("Playing: " + simpleIteraor.next());
        }

        // Shuffled Playlist Iterator
        System.out.println("\nShuffled Playlist: ");
        PlaylistIterator shuffledIterator = playlist.iterator("shuffled");
        while(shuffledIterator.hasNext()){
            System.out.println("Playing: " + shuffledIterator.next());
        }

        // Favorites Playlist Iterator
        System.out.println("\nFavorites Playlist: ");
        PlaylistIterator favoritesIterator = playlist.iterator("favorites");
        while(favoritesIterator.hasNext()){
            System.out.println("Playing: " + favoritesIterator.next());
        }
    }
}
