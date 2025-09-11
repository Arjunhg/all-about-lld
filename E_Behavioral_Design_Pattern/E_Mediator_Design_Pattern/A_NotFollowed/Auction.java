package E_Behavioral_Design_Pattern.E_Mediator_Design_Pattern.A_NotFollowed;

// Auction System Without Mediator Pattern - Key Issues:
// • Every bidder must directly know about all other bidders
// • Bidders need to communicate new bids to each participant individually
// • Creates tight coupling between all auction participants
// • Makes the system complex and hard to maintain
// • Adding new bidders requires updating all existing bidder classes

class Bidder{
    String name;
    int bid;

    public Bidder(String name){
        this.name = name;
    }

    public void placeBid(Bidder[] bidders, int bid){
        this.bid = bid;
        System.out.println("[" + name + "] placed a bid: " + bid);
        System.out.println("LOG: " + name + " bid " + bid + " at " + System.currentTimeMillis());
        notifyAll(bidders);
    }

    public void notifyAll(Bidder[] bidders){
        for(Bidder bidder : bidders){
            if(bidder != this){
                System.out.println(name + " notifies " + bidder.name + " of a new bid of $" + this.bid);

                System.out.println("[" + name + "] notifies: " + bidder.name + " of a new bid of $" + this.bid);
                System.out.println("EMAIL: Sending email to " + bidder.name + " about the bid from " + name);
            }
        }
    }
}

public class Auction{
    public static void main(String[] args) {
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");
        Bidder bidder3 = new Bidder("Charlie");

        Bidder[] bidders = {bidder1, bidder2, bidder3};

        bidder1.placeBid(bidders, 100);
        bidder2.placeBid(bidders, 150);
        bidder3.placeBid(bidders, 200);
    }
}

/*
 * Key Problems with This Approach:
 * 
 * • 🚨 Tangled Code: Every bidder is tightly coupled with all other bidders
 * • 🔧 Hard to Extend: Adding new features means modifying EVERY bidder class
 * • 📈 Scalability Issues: More bidders = exponentially more complexity
 * • 🐛 Maintenance Nightmare: One change breaks multiple classes
 * • 💥 Violation of SOLID Principles: Too many responsibilities in one class
 * • 🔄 Code Duplication: Notification logic repeated everywhere
 * 
 * What happens when you need to add email notifications? SMS alerts? Bid validation?
 * You'll have to modify ALL bidder classes! 😬💥
 * 
 * The Mediator Pattern solves this by centralizing communication! 🎯
 */