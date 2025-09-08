package E_Behavioral_Design_Pattern.D_Command_Design_Pattern.A_NotFollowed;

// Building a Smart Remote Control System - Let's Break It Down!
//
// 🎯 Your Mission: Create a remote control for your TV
//
// 📺 What should your remote do?
//    • Turn the TV on and off
//    • Change channels up and down
//    • Adjust volume (increase/decrease)
//    • Maybe even switch inputs or access smart features
//
// 🤔 The Challenge: How do we organize all these different actions?
//
// 💡 First Approach: Let's try the traditional way (without Command pattern)
//    • We'll see what problems we run into
//    • Then we'll learn why the Command pattern is so powerful
//
// 🚀 Ready? Let's start coding and see what happens...


class TV {
    public void turnOn() { System.out.println("TV turned on"); }
    public void turnOff() { System.out.println("TV turned off"); }
    public void changeChannel(int channel) { System.out.println("Channel changed to " + channel); }
    public void adjustVolume(int volume) { System.out.println("Volume adjusted to " + volume); }
}

public class RemoteControl {
    TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }
    public void pressOnButton() {
        tv.turnOn();
    }
    public void pressOffButton() {
        tv.turnOff();
    }
    public void pressChannelButton(int channel) {
        tv.changeChannel(channel);
    }
    public void pressVolumeButton(int volume) {
        tv.adjustVolume(volume);
    }
}

/*
 * 🚨 What's the Issue?
 * 
 * • 🔗 Direct Coupling: We're directly calling TV methods inside RemoteControl
 * • 🔧 Hard to Modify: Adding new features means changing RemoteControl class
 * • 📝 Code Duplication: Same patterns repeated for each new functionality
 * • 🚫 No Flexibility: System can't easily adapt to new requirements
 * 
 * 
 * 🤔 The Interviewer's Questions (Think About These!)
 * 
 * • ❓ What if we want to add MORE functionalities to the remote?
 *   (Smart features, recording, streaming apps?)
 * 
 * • ❓ What if we want to STORE a sequence of operations?
 *   (Turn on TV → Change to channel 5 → Set volume to 15)
 *   And execute them later as a "macro"?
 * 
 * • ❓ How would you handle MULTIPLE remotes controlling DIFFERENT devices?
 *   (TV remote, Speaker remote, AC remote - all in one system?)
 * 
 * 
 * 💥 What's Breaking Our Code?
 * 
 * • 🔄 Code Duplication Problem:
 *   - Every new action = modify RemoteControl class
 *   - Repetitive patterns everywhere
 *   - More bugs as code grows
 * 
 * • 🧱 Hard to Extend Problem:
 *   - Want to control a smart speaker? Modify RemoteControl
 *   - Want to add AC unit? Modify RemoteControl again
 *   - System becomes a nightmare to maintain!
 * 
 * 🎯 Bottom Line: Our current approach is like building a house of cards!
 */