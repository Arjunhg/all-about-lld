package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.B_Followed.B_Core_Classes;

public class TV {
    public void turnOn(){
        System.out.println("TV is ON");
    }
    public void turnOff(){
        System.out.println("TV is OFF");
    }
    public void changeChannel(int channel){
        System.out.println("Channel is changed to " + channel);
    }
    public void adjustVolume(int volume){
        System.out.println("Volume is adjusted to " + volume);
    }
}
