package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.A_NotFollowed;

// Auction System Without Mediator Pattern - Key Issues:
// • Every bidder must directly know about all other bidders
// • Bidders need to communicate new bids to each participant individually
// • Creates tight coupling between all auction participants
// • Makes the system complex and hard to maintain
// • Adding new bidders requires updating all existing bidder classes

class Bidder {
    String name;
    int bid;

    public Bidder(String name){
        this.name = name;
    }

    public void placeBid(int amount, Bidder[] bidders){
        this.bid = amount;
        System.out.println(name + " placed a bid of $" + amount);
        for(Bidder bidder : bidders){
            if(bidder != this){
                bidder.receiveBid(this, amount);
            }
        }
    }

    public void receiveBid(Bidder bidder, int amount){
        System.out.println(name + " received a bid of $" + amount + " from " + bidder.name);
    }
}
public class Auction {
    public static void main(String[] args) {
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");
        Bidder bidder3 = new Bidder("Charlie");

        Bidder[] bidders = {bidder1, bidder2, bidder3};

        bidder1.placeBid(100, bidders);
        bidder2.placeBid(200, bidders);
        bidder3.placeBid(150, bidders);
    }
}
