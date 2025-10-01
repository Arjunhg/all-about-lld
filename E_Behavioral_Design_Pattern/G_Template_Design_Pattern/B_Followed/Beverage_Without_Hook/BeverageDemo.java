package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook;

import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook.Concrete_Beverages.Coffee;
import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.B_Followed.Beverage_Without_Hook.Concrete_Beverages.Tea;

/*
 * 🎯 THE TEMPLATE DESIGN PATTERN EXPLAINED!
 * 
 * ✅ What is it?
 *    • Creates an abstract class that holds the common algorithm steps
 *    • Lets subclasses override only the specific parts they need
 *    • Provides a cleaner, more organized approach to shared workflows
 * 
 * 🏗️ How it works:
 *    • Define a fixed "template" for an algorithm in the base class
 *    • Keep the overall process steps consistent across all implementations
 *    • Allow subclasses to customize specific details (like brewing vs. steeping)
 * .
 * 🍪 Think of it like a recipe:
 *    • You follow the same cookie recipe steps every time
 *    • But you can swap out ingredients (chocolate chips vs. raisins)
 *    • The process stays the same, only the details change!
 * 
 * 💡 Benefits:
 *    • Ensures consistent workflow across different implementations
 *    • Reduces code duplication
 *    • Provides flexibility for variations while maintaining structure
 *    • Makes code easier to maintain and extend
 */


public class BeverageDemo {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        Beverage tea = new Tea();

        System.out.println("\nMaking Coffee...");
        coffee.prepareRecipe();

        System.out.println("\nMaking Tea...");
        tea.prepareRecipe();
    }
}

/*
 * 🤔 INTERVIEW FOLLOW-UP QUESTION:
 * 
 * 📝 Question: "What if the customer sometimes doesn't want any condiments? How would you handle that?"
 * 
 * 💡 Answer: We can add a hook method to allow optional steps:
 * 
 * 🔧 Solution Points:
 *    • Add a hook method in the abstract class (e.g., `customerWantsCondiments()`)
 *    • Default implementation returns `true` for backward compatibility
 *    • Subclasses can override to return `false` when no condiments needed
 *    • Template method checks the hook before executing condiment steps
 *    • This gives us optional workflow steps without breaking the pattern!
 * 
 * 🎯 Result: Maximum flexibility while maintaining the template structure!
 */
