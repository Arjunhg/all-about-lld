package E_Behavioral_Design_Pattern.G_Template_Design_Pattern.A_NotFollowed;

import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.A_NotFollowed.ConcreteClass.Coffee;
import E_Behavioral_Design_Pattern.G_Template_Design_Pattern.A_NotFollowed.ConcreteClass.Tea;

/**
 * 🎯 Template Design Pattern - Traditional Beverage Preparation
 * 
 * 📋 Let's explore a common scenario: Making beverages!
 * 
 * 🔍 The Problem:
 *    • Whether it's coffee ☕ or tea 🍵, the preparation process follows similar steps
 *    • Boil water → Brew the drink → Pour into cup → Add condiments
 *    • Without proper design, we end up with duplicate code that looks almost identical
 *    • Only a few steps differ between different beverages
 * 
 * ⚠️  Current Approach Issues:
 *    • Code duplication across Coffee and Tea classes
 *    • Similar algorithms scattered in different places
 *    • Hard to maintain and extend for new beverages
 */

public class TraditionalBeverages {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Tea tea = new Tea();

        System.out.println("Preparing Coffee...");
        coffee.prepare();

        System.out.println("\nPreparing Tea...");
        tea.prepare();
    }
}

// 💬 Interview Scenario: Code Review Discussion

// 👨‍💼 Interviewer: "I see you have a lot of duplicated code. How would you refactor this 
//                   to make it more maintainable and less ugly?"

// 🤔 Your Response Strategy:
//    • Point 1: Identify the common algorithm pattern across beverages
//    • Point 2: Explain how Template Design Pattern can eliminate duplication
//    • Point 3: Show how abstract methods can handle variations
//    • Point 4: Demonstrate improved maintainability for future beverages

// 📝 Key Talking Points:
//    ✅ "I'd use Template Design Pattern to abstract common preparation steps"
//    ✅ "This creates a template method that defines the algorithm skeleton"
//    ✅ "Subclasses override only the steps that differ between beverages"
//    ✅ "Result: DRY principle followed, easier to add new beverage types"
