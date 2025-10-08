// package E_Behavioral_Design_Pattern.J_Memento_Design_Pattern.A_NotFollowed;


// /*
//  * 📝 Text Editor Undo Feature Challenge
//  * 
//  * Scenario: Building a Smart Text Editor
//  * • User is actively typing and making changes to their document
//  * • User needs the ability to undo mistakes seamlessly
//  * • User wants to revert to any previous version of their text
//  * 
//  * ❌ Traditional Approach Problems:
//  * • Manually storing previous states within the text editor class
//  * • Code becomes messy and difficult to maintain as features grow
//  * • Tight coupling between editor logic and state management
//  * • Hard to extend with new undo/redo functionality
//  * 
//  * 🔍 What you'll see next:
//  * • How this problematic approach looks in practice
//  * • Why it fails to scale with complex applications
//  * • The issues that arise from poor state management
//  */
// public class TextEditor {

//     private String text;

//     public TextEditor(String text){
//         this.text = text;
//     }

//     public void setText(String text){
//         this.text = text;
//     }

//     public void undo(String previousText){
//         this.text = previousText;
//     }

//     public void showText(){
//         System.out.println("Current Text: " + text);
//     }

//     public static void main(String[] args) {
        
//         TextEditor editor = new TextEditor("Hello World");
//         editor.showText();

//         String backUp = "Hello World"; //Manual Backup      
//         editor.setText("New World");
//         editor.showText();

//         editor.undo(backUp);
//         editor.showText();
//     }
// }


// /*
//  * 🔍 Code Analysis & Problems Identified:
//  * 
//  * 📌 Manual State Management Issues:
//  *   • We manually save previous state in a simple variable
//  *   • No structured approach to handle multiple undo operations
//  *   • Backup logic is scattered throughout the code
//  * 
//  * 📌 Scalability Nightmares:
//  *   • Adding multi-level undo requires extensive code changes
//  *   • Each new feature forces modification of the core TextEditor class
//  *   • Code becomes increasingly tangled and hard to maintain
//  * 
//  * 🎯 The Interviewer's Challenge: 
//  *    "This Code Looks Messy. How Can You Improve It?" 😮
//  * 
//  * 💭 What They're Really Testing:
//  *   • Your ability to identify design pattern opportunities
//  *   • Understanding of clean code principles
//  *   • Knowledge of state management best practices
//  * 
//  * ⚠️ Real-World Consequences:
//  *   • Maintenance becomes a developer's nightmare 😵💥
//  *   • Code duplication spreads like wildfire
//  *   • Adding features breaks existing functionality
//  *   • Team productivity plummets due to code complexity
//  * 
//  * 🚀 Next Step: 
//  *    Let's solve this with the Memento Design Pattern!
//  */