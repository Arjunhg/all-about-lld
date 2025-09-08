package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed;

import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands.AdjustVolumeCommand;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands.ChangeChannelCommand;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands.TurnOffCommand;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands.TurnOnCommand;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes.RemoteControl;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes.TV;

public class Main {
    public static void main(String[] args) {
        TV tv = new TV();

        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);
        Command changeChannel = new ChangeChannelCommand(tv, 5);
        Command adjustVolume = new AdjustVolumeCommand(tv, 10);

        RemoteControl remote = new RemoteControl();
        remote.setOnCommand(turnOn);
        remote.setOffCommand(turnOff);
        remote.pressOffButton();
        remote.pressOnButton();

        changeChannel.execute();
        adjustVolume.execute();

    }
}
