package G_Projects.J_File_System.CompositePattern;

import java.time.LocalDateTime;

import G_Projects.J_File_System.CompositePattern.ConcreteImplementations.Directory;

/*
FileSystemNode
 ├── File        (parent ✅, children ❌)
 └── Directory   (parent ✅, children ✅)
*/

public abstract class FileSystemNode {
    
    private String name; // Name of the node
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Directory parent; // Parent reference for upward traversal

    public FileSystemNode(String name){
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public abstract boolean isFile();
    public abstract void display(int depth);

    public String getName(){
        return name;
    }

    protected void updateModifiedAt(){
        this.modifiedAt = LocalDateTime.now();
    }

    public LocalDateTime getModifiedAt(){
        return modifiedAt;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setParent(Directory parent){
        this.parent = parent;
    }
    public Directory getParent(){
        return parent;
    }
}
