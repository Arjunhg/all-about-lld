package F_Structural_Design_Pattern.A_Adapter_Design_Pattern.B_Followed;

/*
 * STEP 1: Define Common Interface for Smart Devices
 * 
 * Key Points:
 * • Creates a unified contract for all smart devices
 * • Enables SmartHomeController to interact with any device consistently
 * • Abstracts away device-specific implementation details
 * • Follows Interface Segregation Principle
 * 
 * Benefits:
 * → Consistent API across different device types
 * → Easy to add new devices without modifying existing code
 * → Loose coupling between controller and devices
 * → Protocol-agnostic design approach
 */


// Common interface for all smart devices
public interface SmartDevice{
    void turnOn(); // Method to turn on the device
    void turnOff(); // Method to turn off the device
}
