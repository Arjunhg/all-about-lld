package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.A_NotFollowed;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.A_NotFollowed.Concrete_Patients.AdultPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.A_NotFollowed.Concrete_Patients.ChildPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.A_NotFollowed.Concrete_Patients.SeniorPatient;

/*
 * 🏥 THE HOSPITAL CHALLENGE - Let's Break It Down! 🏥
 * 
 * 📝 Picture This Scenario:
 *    • We have THREE types of patients in our hospital:
 *      ➤ ChildPatient 👶
 *      ➤ AdultPatient 👨‍💼
 *      ➤ SeniorPatient 👴
 * 
 * 🎯 Each Patient Needs Tailored Care:
 *    • Customized diagnosis approaches
 *    • Different billing calculations
 *    • Specialized treatment plans
 * 
 * 😬 The Traditional Problem:
 *    • Each patient class handles its own operations
 *    • New operations = More methods in every class
 *    • Result: Cluttered classes with endless if-else checks
 *    • Examples of operations that keep growing:
 *      ➤ Health reports
 *      ➤ Medication calculations
 *      ➤ Insurance processing
 *      ➤ Follow-up scheduling
 * 
 * 😵 The Traditional Treatment: The If-Else Way
 * Let's start with the traditional approach and see why it gets messy...
 */



public class Hospital {
    public static void main(String[] args) {
        
        Object patient = new AdultPatient(); // This could be any patient type: Child, Adult, or Senior

        if(patient instanceof ChildPatient){
            ((ChildPatient)patient).diagnose();
            ((ChildPatient)patient).billing();
        }else if(patient instanceof AdultPatient){
            ((AdultPatient)patient).diagnose();
            ((AdultPatient)patient).billing();
        }else if(patient instanceof SeniorPatient){
            ((SeniorPatient)patient).diagnose();
            ((SeniorPatient)patient).billing();
        }
    }
}
