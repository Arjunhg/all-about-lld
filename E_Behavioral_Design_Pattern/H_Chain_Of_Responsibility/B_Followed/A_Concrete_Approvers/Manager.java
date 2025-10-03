package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers;

import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.Approver;

public class Manager extends Approver {
    @Override
    public void processRequest(int leaveDays){
        if(leaveDays<=7){
            System.out.println("Manager approved " + leaveDays + " days of leave.");
        }else if(nextApprover!=null){
            nextApprover.processRequest(leaveDays);
        }
    }
}
