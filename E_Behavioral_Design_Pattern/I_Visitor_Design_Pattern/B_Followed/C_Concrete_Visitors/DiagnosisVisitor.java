package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.C_Concrete_Visitors;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces.Visitor;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.AdultPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.ChildPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.SeniorPatient;

public class DiagnosisVisitor implements Visitor {
    @Override
    public void visit(ChildPatient childPatient) {
        System.out.println("Performing diagnosis for child patient: ");
        // Diagnosis logic for child patients
    }
    @Override
    public void visit(AdultPatient adultPatient) {
        System.out.println("Performing diagnosis for adult patient: ");
        // Diagnosis logic for adult patients
    }
    @Override
    public void visit(SeniorPatient seniorPatient){
        System.out.println("Performing diagnosis for senior patient: ");
        // Diagnosis logic for senior patients
    }
}
