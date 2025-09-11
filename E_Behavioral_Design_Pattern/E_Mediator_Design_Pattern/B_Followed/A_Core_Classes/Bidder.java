package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.AuctionMediator;

/*
 * Key Benefits of the Mediator Pattern Implementation:
 * 
 * • Decoupled Communication: Each bidder communicates through the mediator rather than 
 *   directly with other bidders, eliminating tight coupling between objects.
 * 
 * • Centralized Control: The mediator acts as a single point of control for all 
 *   bidder interactions, making the system easier to manage and modify.
 * 
 * • Clean Architecture: By removing direct bidder-to-bidder dependencies, the code 
 *   becomes more maintainable and follows the Single Responsibility Principle.
 * 
 * • Enhanced Flexibility: New bidders can be added or removed without affecting 
 *   existing bidder implementations - they only need to know about the mediator.
 */

public class Bidder {
    private String name;
    private AuctionMediator mediator;

    public Bidder(String name, AuctionMediator mediator){
        this.name = name;
        this.mediator = mediator;
    }

    public String getName(){
        return name;
    }

    public void placeBid(int amount){
        mediator.placeBid(this, amount);
    }

    public void receiveBid(Bidder bidder, int amount){
        System.out.println(this.name + " received notification: " + bidder.getName() + " placed a bid of $ " + amount);
    }
}
