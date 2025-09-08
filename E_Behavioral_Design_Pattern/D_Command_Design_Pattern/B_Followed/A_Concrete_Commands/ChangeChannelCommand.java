package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.A_Concrete_Commands;

import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.Command;
import E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes.TV;

public class ChangeChannelCommand implements Command{
    private TV tv;
    private int channel;

    public ChangeChannelCommand (TV tv, int channel){
        this.tv = tv;
        this.channel = channel;
    }   

    @Override
    public void execute(){
        tv.changeChannel(channel);
    }
    
}
