package D_Creational_Design_Pattern.C_Prototype_Design_Pattern;

class Character implements Cloneable {
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

    // getters
    public String getName() {return name;}
    public String getHealth() {return health;}
    public String getPower() {return power;}
    public String getLevel() {return level;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setHealth(String health) {this.health = health;}    
    public void setPower(String power) {this.power = power;}    
    public void setLevel(String level) {this.level = level;}

    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }

    public String characterInfo() {
        return "Character [name=" + name + ", health=" + health + ", power=" + power + ", level=" + level + "]";
    }
}

class CharacterFactory {
    private Character prototype;

    public CharacterFactory(){
        prototype = new Character("Default", "100", "50", "1");
    }

    public Character createCharacterWithName(String name) throws CloneNotSupportedException {
        Character cloned = prototype.clone();
        cloned.setName(name);
        return cloned;
    }

    public Character createCharacterWithHealth(String health) throws CloneNotSupportedException {
        Character cloned = prototype.clone();
        cloned.setHealth(health);
        return cloned;
    }

    public Character createCharacterWithPower(String power) throws CloneNotSupportedException {
        Character cloned = prototype.clone();
        cloned.setPower(power);
        return cloned;
    }
}

public class B_Followed {
    public static void main(String[] args) {
        
        try {
            CharacterFactory factory = new CharacterFactory();
            Character c1 = factory.createCharacterWithName("Hero");
            Character c2 = factory.createCharacterWithHealth("200");
            Character c3 = factory.createCharacterWithPower("1000");
            
            System.out.println(c1.characterInfo());
            System.out.println(c2.characterInfo());
            System.out.println(c3.characterInfo());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}