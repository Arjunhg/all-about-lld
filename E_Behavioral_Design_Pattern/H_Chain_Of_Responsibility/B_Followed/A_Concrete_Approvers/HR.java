package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers;

import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.Approver;

public class HR extends Approver {
    @Override
    public void processRequest(int leaveDays){
        System.out.println("HR: Leave request requires further discussions");
    }
}
