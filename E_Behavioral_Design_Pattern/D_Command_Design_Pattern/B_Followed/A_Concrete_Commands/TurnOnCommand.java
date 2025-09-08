package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands;

import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.Command;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes.TV;

public class TurnOnCommand implements Command{
    private TV tv;

    public TurnOnCommand(TV tv){
        this.tv = tv;
    }

    @Override
    public void execute(){
        tv.turnOn();
    }
}
