package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed;

public abstract class Approver {

    protected Approver nextApprover;

    // Next handler in the chain
    public void setNextApprover(Approver nextApprover){
        this.nextApprover = nextApprover;
    }

    // Method to process the request
    public abstract void processRequest(int leaveDays);

}