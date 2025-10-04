package E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.C_Concrete_Visitors;

import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.A_Interfaces.Visitor;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.AdultPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.ChildPatient;
import E_Behavioral_Design_Pattern.I_Visitor_Design_Pattern.B_Followed.B_Concrete_Patients.SeniorPatient;

/*
 * BillingVisitor - Concrete Implementation of Visitor Pattern
 * 
 * Key Features:
 * • Encapsulates billing logic for different patient types
 * • Maintains separation of concerns from patient classes
 * • Enables adding new operations without modifying existing patient code
 * • Demonstrates polymorphic behavior through visitor pattern
 * 
 * Benefits:
 * • Independent from patient class hierarchy
 * • Perfect for extending functionality without code modification
 * • Centralizes billing-related operations in one place
 */

public class BillingVisitor implements Visitor {
    @Override
    public void visit(AdultPatient adultPatient) {
        System.out.println("Calculating billing for adult patient: ");
        // Billing logic for adult patients
    }
    @Override
    public void visit(ChildPatient childPatient) {
        System.out.println("Calculating billing for child patient: ");
        // Billing logic for child patients
    }
    @Override
    public void visit(SeniorPatient seniorPatient){
        System.out.println("Calculating billing for senior patient: ");
        // Billing logic for senior patients
    }
}
