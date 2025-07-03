package C_Design_Principles.B_DRY_Principle;

/* Violation of DRY Principle
class SubmitButton {
    void onClick(){
        System.out.println("Submit Button Clicked");
    }
}
class CancelButton {
    void onClick(){
        System.out.println("Cancel Button Clicked");
    }
}
*/

// Following DRY Principle
abstract class Button {
    abstract void onClick();
}
class SubmitButton extends Button{
    @Override
    void onClick() {
        System.out.println("Submit Button Clicked");
    }
}
class CancelButton extends Button{
    @Override
    void onClick() {
        System.out.println("Cancel Button Clicked");
    }
}
// 🔑 Overriding is not a DRY violation — it's polymorphism in action.


public class B_OOPS_DRY {
    public static void main(String[] args) {
        
        /* Violation of DRY Principle
        SubmitButton submit = new SubmitButton();
        submit.onCLick();
        CancelButton cancel = new CancelButton();
        cancel.onClick();
        */

        // Following DRY Principle
        Button submit = new SubmitButton();
        submit.onClick();
        Button cancel = new CancelButton();
        cancel.onClick();
    }
}