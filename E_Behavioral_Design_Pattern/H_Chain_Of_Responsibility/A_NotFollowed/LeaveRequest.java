package E_Behavioral_Design_Pattern.H_Chain_Of_Responsibility.A_NotFollowed;

/*
 * CHAIN OF RESPONSIBILITY PATTERN - SCENARIO OVERVIEW
 * ================================================
 * 
 * 🎯 SITUATION: Employee Leave Request Processing
 * 
 * 📋 THE CHALLENGE:
 *    • An employee submits a leave request
 *    • Different approval levels based on leave duration
 *    • Need to route request to appropriate authority
 * 
 * 👥 APPROVAL HIERARCHY:
 *    • Short Leave (1-3 days)    → Supervisor handles
 *    • Moderate Leave (4-7 days) → Manager handles  
 *    • Long Leave (8-14 days)     → Director handles
 * 
 * ⚠️  TRADITIONAL APPROACH PROBLEMS:
 *    • Tight coupling between request and handlers
 *    • Hard-coded approval logic
 *    • Difficult to modify approval chain
 *    • No flexibility for dynamic routing
 * 
 * 🔍 WHAT WE'LL EXPLORE:
 *    First: Traditional implementation (this example)
 *    Then: Chain of Responsibility pattern solution
 */

public class LeaveRequest{
    public static void main(String[] args) {

        int leaveDays = 12;
        if(leaveDays > 0){
            if(leaveDays <= 3){
                System.out.println("Supervisor approved leave for " + leaveDays + " days.");
            }else{
                if(leaveDays <= 7){
                    System.out.println("Manager approved leave for " + leaveDays + " days.");
                }else{
                    if(leaveDays <= 14){
                        System.out.println("Director approved leave for " + leaveDays + " days.");
                    }else{
                        System.out.println("Leave request for " + leaveDays + " denied. Exceeds maximum limit.");
                    }
                }
            }
        }else{
            System.out.println("Invalid leave request for " + leaveDays + " days.");
        }

    }
}