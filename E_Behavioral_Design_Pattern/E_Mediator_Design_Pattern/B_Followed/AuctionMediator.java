package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.B_Followed.A_Core_Classes.Bidder;

public interface AuctionMediator {
    void registerBidder(Bidder bidder);
    void placeBid(Bidder bidder, int amount);
}
