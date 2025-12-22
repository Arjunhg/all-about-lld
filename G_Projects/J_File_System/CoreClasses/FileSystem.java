package G_Projects.J_File_System.CoreClasses;

import G_Projects.J_File_System.CompositePattern.FileSystemNode;
import G_Projects.J_File_System.CompositePattern.ConcreteImplementations.Directory;
import G_Projects.J_File_System.CompositePattern.ConcreteImplementations.File;

public class FileSystem {

    private FileSystemNode root;

    public FileSystem() {
        this.root = new Directory("/");
    }

    public boolean isValidFilePath(String path) {
        return path != null && !path.isEmpty() && path.startsWith("/");
    }

    public boolean createPath(String path) {
        if (!isValidFilePath(path))
            return false;

        String[] pathComponents = path.split("/");
        Directory current = (Directory)root;

        for (int i = 1; i < pathComponents.length - 1; i++) {
            String component = pathComponents[i];
            if (component.isEmpty()) continue;

            if (!current.hasChild(component)) {
                Directory newDir = new Directory(component);
                current.addChild(newDir);
            }

            FileSystemNode child = current.getChild(component);
            if (child.isFile())return false;

            current = (Directory)child;
        }

        String lastComponent = pathComponents[pathComponents.length - 1];
        if (lastComponent.isEmpty())
            return false;

        if (current.hasChild(lastComponent)) {
            return false; // help prevent duplicate
        }

        FileSystemNode newNode;
        if (lastComponent.contains(".")) {
            newNode = new File(lastComponent);
        } else {
            newNode = new Directory(lastComponent);
        }

        current.addChild(newNode);
        return true;
    }

    private FileSystemNode getNode(String path) {
        if (!isValidFilePath(path))
            return null;

        if (path.equals("/"))
            return root;

        String[] pathComponents = path.split("/");
        FileSystemNode current = root;

        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty())
                continue;

            if(current.isFile()){
                return null; // root is one index behind of pathComponents. So for any value of i if current is a file it means we are trying to traverse inside a file which is invalid.
            }

            Directory currentDir = (Directory) current;

            if (!currentDir.hasChild(component)) {
                return null;
            }
            current = currentDir.getChild(component);
        }
        return current;
    }

    public boolean deletePath(String path) {
        if (!isValidFilePath(path))
            return false;

        if (path.equals("/"))
            return false;

        String parentPath = getParentPath(path);
        FileSystemNode parentNode = getNode(parentPath);

        if (parentNode == null || parentNode.isFile())
            return false;

        String lastComponent = path.substring(path.lastIndexOf('/') + 1);

        Directory parent = (Directory) parentNode;

        if (!parent.hasChild(lastComponent)) {
            return false;
        }

        return parent.removeChild(lastComponent);
    }

    private String getParentPath(String path) {
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash <= 0) {
            return "/";
        }
        return path.substring(0, lastSlash);
    }

    public void display() {
        root.display(0);
    }

    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return false;

        File file = (File) node;
        file.setContent(content);
        return true;
    }

    public String getFileContent(String path) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return null;

        File file = (File) node;
        return file.getContent();
    }
}
