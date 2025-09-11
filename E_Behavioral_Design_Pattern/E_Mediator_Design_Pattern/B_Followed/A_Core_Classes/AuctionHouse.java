package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes;

import java.util.ArrayList;
import java.util.List;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.AuctionMediator;

/*
 * Key Features of AuctionHouse (Mediator Implementation):
 * 
 * • Maintains a centralized registry of all participating bidders
 * • Acts as the communication hub between bidders during auctions
 * • Broadcasts bid notifications to all registered bidders except the bidder who placed the bid
 * • Ensures decoupled communication - bidders don't need to know about each other directly
 * • Facilitates real-time auction interactions through the mediator pattern
 */

public class AuctionHouse implements AuctionMediator {
    private List<Bidder> bidders = new ArrayList<>();
    
    @Override
    public void registerBidder(Bidder bidder){
        bidders.add(bidder);
    }

    @Override
    public void placeBid(Bidder bidder, int amount){
        System.out.println(bidder.getName() + " placed a bid of $ " + amount);
        for(Bidder b : bidders){
            if(b != bidder){
                b.receiveBid(bidder, amount);
            }
        }
    }
}
