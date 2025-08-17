package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface;

/*
 * Observer Design Pattern - Key Points:
 * 
 * 1. Observer Pattern Overview:
 *    - The Observer is the component that reacts to changes
 *    - Similar to a subscriber reacting to a new video notification
 * 
 * 2. Interface Design:
 *    - Create an interface called Subscriber to define observer behavior
 *    - This interface standardizes what methods observers should implement
 * 
 * 3. Core Method:
 *    - update() method is the primary notification mechanism
 *    - Called when the subject (observable) has changes to communicate
 * 
 * 4. Pattern Purpose:
 *    - Enables loose coupling between subject and observers
 *    - Allows multiple observers to react to the same event
*/

public interface Subscriber{
    void update(String content); // Will be used by observers to receive updates
}