package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers;

import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.Approver;

public class Director extends Approver {
    @Override
    public void processRequest(int leaveDays){
        if(leaveDays <= 14){
            System.out.println("Director approved " + leaveDays + " days of leave.");
        }else if(nextApprover != null){
            nextApprover.processRequest(leaveDays);
        }else{
            System.out.println("Leave request for " + leaveDays + " days cannot be approved.");
        }
    }
}
