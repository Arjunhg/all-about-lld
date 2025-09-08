package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands;

import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.Command;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes.TV;

public class AdjustVolumeCommand implements Command{
    private TV tv;
    private int volume;

    public AdjustVolumeCommand(TV tv, int volume){
        this.tv = tv;
        this.volume = volume;
    }

    @Override
    public void execute(){
        tv.adjustVolume(volume);
    }
}
