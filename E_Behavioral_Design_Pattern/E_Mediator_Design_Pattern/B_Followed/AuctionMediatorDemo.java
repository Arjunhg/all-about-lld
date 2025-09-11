package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.AuctionHouse;
import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.Bidder;

public class AuctionMediatorDemo {
    public static void main(String[] args) {
        
        AuctionMediator mediator = new AuctionHouse();

        Bidder bidder1 = new Bidder("Alice", mediator);
        Bidder bidder2 = new Bidder("Bob", mediator);
        Bidder bidder3 = new Bidder("Charlie", mediator);

        mediator.registerBidder(bidder1);
        mediator.registerBidder(bidder2);
        mediator.registerBidder(bidder3);

        bidder1.placeBid(100);
        bidder2.placeBid(150);
        bidder3.placeBid(200);
    }
}
