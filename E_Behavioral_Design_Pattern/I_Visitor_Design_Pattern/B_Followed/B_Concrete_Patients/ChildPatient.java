package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces.Patient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces.Visitor;

public class ChildPatient implements Patient {
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
