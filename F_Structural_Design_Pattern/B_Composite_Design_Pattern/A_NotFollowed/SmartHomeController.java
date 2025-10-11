package F_Structural_Design_Pattern.B_Composite_Design_Pattern.A_NotFollowed;


public class SmartHomeController {
    public static void main(String[] args) {
        
        // Manually controlling each device
        AirConditioner ac = new AirConditioner();
        SmartLight light = new SmartLight();

        System.out.println("--- Turning On Devices in Room 1 ---");
        ac.turnOn();
        light.turnOn();
        System.out.println("--- Turning Off Devices in Room 1 ---");
        ac.turnOff();
        light.turnOff();

        System.out.println("--- Turning On Devices in Floor 1 ---");
        ac.turnOn();
        light.turnOn();
        ac.turnOn(); // Room 2
        light.turnOn(); // Room 2
        System.out.println("--- Turning Off Devices in Floor 1 ---");
        ac.turnOff();
        light.turnOff();
        ac.turnOff(); // Room 3
        light.turnOff(); // Room 3
    }
}

/*
 * 🏠 The Smart Home Management Challenge 🤔
 * 
 * An interviewer might ask these tough questions:
 * 
 * 📋 Key Problems We Face:
 * 
 * 1️⃣ SCALABILITY NIGHTMARE 📈
 *    • What happens when you add a new component type?
 *      - New Garage with automated doors?
 *      - Garden with sprinkler systems?
 *      - Basement with security cameras?
 *    • Current approach: Modify code everywhere! 😱
 * 
 * 2️⃣ HIERARCHY EVOLUTION 🔄
 *    • What if the structure changes dynamically?
 *      - Introducing "Zones" that group rooms
 *      - Multi-building properties
 *      - Temporary device additions/removals
 *    • Current approach: Rewrite major portions! 💥
 * 
 * 3️⃣ MANAGEMENT COMPLEXITY 🧩
 *    • How do we handle nested operations efficiently?
 *      - Turn off entire floor vs single room
 *      - Bulk operations across multiple zones
 *      - Conditional device grouping
 *    • Current approach: Hardcoded traversal logic! 🔨
 * 
 * 💥 Why This Code is PROBLEMATIC:
 * 
 * ❌ HARDCODED LOGIC EVERYWHERE
 *    • Each level (device → room → floor → house) managed manually
 *    • Duplicate code for similar operations
 *    • Maintenance nightmare when requirements change
 * 
 * ❌ FRAGILE DESIGN
 *    • Adding new components = touching multiple files
 *    • One change breaks multiple parts
 *    • No consistent interface for operations
 * 
 * ❌ TIGHT COUPLING DISASTER
 *    • Controller knows too much about each device type
 *    • Cannot reuse logic for different hierarchies
 *    • Testing becomes extremely difficult
 * 
 * ❌ SCALING IMPOSSIBILITY
 *    • Real-world scenario: 100+ devices, 20+ rooms
 *    • Code becomes unreadable and unmaintainable
 *    • Performance issues with manual traversal
 * 
 * 🎯 SOLUTION NEEDED: A pattern that treats individual objects and 
 *    compositions uniformly! Enter the Composite Pattern! 🚀
 */
