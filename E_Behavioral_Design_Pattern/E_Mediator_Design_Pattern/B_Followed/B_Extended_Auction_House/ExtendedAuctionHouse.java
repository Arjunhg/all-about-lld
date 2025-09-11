package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.B_Extended_Auction_House;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.AuctionHouse;
import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.Bidder;

public class ExtendedAuctionHouse extends AuctionHouse {
    private long biddingEndTime;

    public ExtendedAuctionHouse(long biddingEndTime) {
        this.biddingEndTime = biddingEndTime;
    }

    @Override
    public void placeBid(Bidder bidder, int amount){
        if(System.currentTimeMillis() > biddingEndTime){
            System.out.println("Bidding time is over. No more bids accepted.");
            return;
        }

        System.out.println("LOG: "+ bidder.getName() + " is placing a bid of $ " + amount);
        super.placeBid(bidder, amount);
    }
}
