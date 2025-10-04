package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.AdultPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.ChildPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.SeniorPatient;

public interface Visitor {
    // • This interface defines a visit method for every type of patient.
    // • We can now add as many visitors (operations) as you need without modifying the patient classes.
    void visit(AdultPatient adultPatient);
    void visit(ChildPatient childPatient);
    void visit(SeniorPatient seniorPatient);
}
