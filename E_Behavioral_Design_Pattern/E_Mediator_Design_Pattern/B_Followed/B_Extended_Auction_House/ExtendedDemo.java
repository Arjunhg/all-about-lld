package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.B_Extended_Auction_House;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.AuctionMediator;
import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.Bidder;

import java.lang.Thread;

public class ExtendedDemo {
    public static void main(String[] args) {

        long biddingEndTime = System.currentTimeMillis() + 50000; // Bidding ends in 50 seconds

        AuctionMediator mediator = new ExtendedAuctionHouse(biddingEndTime);

        Bidder bidder1 = new Bidder("Alice", mediator);
        Bidder bidder2 = new Bidder("Bob", mediator);
        Bidder bidder3 = new Bidder("Charlie", mediator);

        mediator.registerBidder(bidder1);
        mediator.registerBidder(bidder2);
        mediator.registerBidder(bidder3);

        bidder1.placeBid(100);

        try {
            Thread.sleep(2000); // Simulate time delay of 2 seconds
            bidder2.placeBid(150);

            Thread.sleep(4000); // Simulate time delay of 4 seconds
            bidder3.placeBid(200); // Rejected
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
