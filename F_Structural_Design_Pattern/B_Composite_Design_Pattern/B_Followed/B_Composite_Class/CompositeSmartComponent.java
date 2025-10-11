package F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.B_Composite_Class;

import java.util.ArrayList;
import java.util.List;

import F_Structural_Design_Pattern.B_Composite_Design_Pattern.B_Followed.SmartComponent;

public class CompositeSmartComponent implements SmartComponent {
    
    private List<SmartComponent> components = new ArrayList<>();

    public void addComponent(SmartComponent component){
        components.add(component);
    }

    public void removeComponent(SmartComponent component){
        components.remove(component);
    }

    @Override
    public void turnOn(){
        for(SmartComponent component : components){
            component.turnOn();
        }
    }

    @Override
    public void turnOff(){
        for(SmartComponent component : components){
            component.turnOff();
        }
    }
}
