package G_Projects.J_File_System.CompositePattern.ConcreteImplementations;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import G_Projects.J_File_System.CompositePattern.FileSystemNode;

public class Directory extends FileSystemNode {

    private Map<String, FileSystemNode> children; // For directories to hold child nodes. Only folder can have children and not files
    
    public Directory(String name){
        super(name);
        this.children = new HashMap<>();
    }

    public void addChild(FileSystemNode child){
        String name = child.getName();
        if(children.containsKey(name)){
            throw new IllegalArgumentException(
                "Node with name " + name + " already exists"
            );
        }
        child.setParent(this); //this refers to the object whose method is currently executing.
        /*
            Directory root = new Directory("Grand");
            Directory parent = new Directory("Parent");

            root.addChild(parent);

            root.addChild(parent);
            ^^^^ here, 'this' refers to 'root' object.
        */
        children.put(name, child);
        updateModifiedAt();
    }

    public Collection<FileSystemNode> getChildren(){
        // return children.values(); can be bypassed using: directory.getChildren().clear();
        return Collections.unmodifiableCollection(children.values());
    }

    public boolean hasChild(String name){
        return children.containsKey(name);
    }

    public FileSystemNode getChild(String name){
        return children.get(name);
    }

    public boolean removeChild(String name){
        if(children.containsKey(name)){
            children.remove(name);
            updateModifiedAt();
            return true;
        }
        return false;
    }

    @Override
    public boolean isFile(){
        return false;
    }

    @Override
    public void display(int depth){

        String indent = " ".repeat(depth * 2);

        // Print directory name with proper identation and emoji
        System.out.println(indent + "📁 " + getName() + " (" + getChildren().size() + " items)");

        for(FileSystemNode child : getChildren()){
            child.display(depth+1);
        }
    }
}
