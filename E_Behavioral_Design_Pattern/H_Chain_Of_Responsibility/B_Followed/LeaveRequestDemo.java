package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed;

import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers.Director;
import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers.HR;
import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers.Manager;
import E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.B_Followed.A_Concrete_Approvers.Supervisor;

public class LeaveRequestDemo {
    public static void main(String[] args) {
        
        Approver supervisor = new Supervisor();
        Approver manager = new Manager(); 
        Approver director = new Director();
        Approver hr = new HR();

        // Setting up the chain of responsibility: Supervisor -> Manager -> Director -> HR
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(hr);

        int leaveDays = 20;
        System.out.println("Requesting " + leaveDays + " days of leave.");
        supervisor.processRequest(leaveDays);
    }
}

