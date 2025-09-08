package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes;

import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.Command;

public class RemoteControl {
    private Command onCommand;
    private Command offCommand;

    public void setOnCommand(Command onCommand){
        this.onCommand = onCommand;
    }

    public void setOffCommand(Command offCommand){
        this.offCommand = offCommand;
    }

    public void pressOnButton(){
        onCommand.execute();
    }

    public void pressOffButton(){
        offCommand.execute();
    }
}
