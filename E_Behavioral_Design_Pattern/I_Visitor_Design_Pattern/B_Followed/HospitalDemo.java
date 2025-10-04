package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces.Patient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.AdultPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.ChildPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.SeniorPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.C_Concrete_Visitors.BillingVisitor;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.C_Concrete_Visitors.DiagnosisVisitor;

public class HospitalDemo {
    public static void main(String[] args) {
        
        Patient[] patients = {
            new ChildPatient(),
            new AdultPatient(),
            new SeniorPatient()
        };

        for(Patient patient : patients){
            patient.accept(new DiagnosisVisitor());
            patient.accept(new BillingVisitor());
        }
    }
}
