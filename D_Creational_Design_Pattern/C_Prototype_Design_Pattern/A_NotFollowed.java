package D_Creational_Design_Pattern.C_Prototype_Design_Pattern;

class Character{
    private String name;
    private String health;
    private String power;
    private String level;

    public Character(String name, String health, String power, String level) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.level = level;
    }

    public String characterInfo(){
        return "Character [name=" + name + ", health=" + health + ", power=" + power + ", level=" + level + "]";
    }
}

class CharacterFactory{
    public Character createCharacterWithName(String name){
        return new Character(name, "100", "50", "1");
    }
    public Character createCharacterWithHealth(String health){
        return new Character("Hero", health, "50", "1");
    }
    public Character createCharacterWithPower(String power){
        return new Character("Hero", "100", power, "1");
    }
}

public class A_NotFollowed {
    public static void main(String[] args) {
        // Character character = new Character("Hero", "100", "50", "1");
        // System.out.println(character.characterInfo());

        CharacterFactory factory = new CharacterFactory();
        Character c1 = factory.createCharacterWithName("Hero");
        System.out.println(c1.characterInfo());
    }
}

/*
 * Issues with This Approach:
 *
 * 1. Code Duplication:
 *    - Creating characters with minor changes requires writing multiple similar methods.
 *    - Leads to repeated code for each variation.
 *
 * 2. Inefficiency:
 *    - For many characters with slight differences, many redundant methods are needed.
 *    - Results in unnecessary repetitive work.
 *
 * 3. Difficult Maintenance:
 *    - Adding or modifying properties (e.g., introducing "armor") requires updating all creation methods.
 *    - Makes the codebase harder to maintain and prone to errors.
 */
