package G_Projects.J_File_System;


import java.util.Scanner;

import G_Projects.J_File_System.CoreClasses.FileSystem;

public class Main {
    public static void main(String[] args) {
        
        FileSystem fs = new FileSystem();
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true; 
        
        System.out.println("File System Manager - Commands:");
        System.out.println("1. create <path> - Create a new path");
        System.out.println("2. write <path> <content> - Write content to a file");
        System.out.println("3. read <path> - Read content from a file");
        System.out.println("4. delete <path> - Delete a path");
        System.out.println("5. display - Show the entire file system structure");
        System.out.println("6. exit - Exit the program");

        while (isRunning) {
            System.out.print("nEnter command: ");
            String input = scanner.nextLine().trim(); 
            String[] parts = input.split("\\s+", 3); 
            if (parts.length == 0)
                continue; 
            String command = parts[0].toLowerCase(); 
            try {
                switch (command) {
                    case "create":
                        if (parts.length >= 2) {
                            String path = parts[1]; 
                            boolean isCreated = fs.createPath(path); 
                            System.out.println(isCreated ? "Path created successfully" : "Failed to create path");
                        } else {
                            System.out.println("Usage: create <path>");
                        }
                        break;
                    case "write":
                        if (parts.length >= 3) {
                            String path = parts[1]; 
                            String content = parts[2]; 
                            boolean isWritten = fs.setFileContent(path, content);
                            System.out.println(
                                    isWritten ? "Content written successfully" : "Failed to write content");
                        } else {
                            System.out.println("Usage: write <path> <content>");
                        }
                        break;
                    case "read":
                        if (parts.length >= 2) {
                            String path = parts[1];
                            String content = fs.getFileContent(path); 
                            if (content != null) {
                                System.out.println("Content: " + content);
                            } else {
                                System.out.println("Failed to read content");
                            }
                        } else {
                            System.out.println("Usage: read <path>");
                        }
                        break;
                    case "delete":
                        if (parts.length >= 2) {
                            String path = parts[1]; 
                            boolean isDeleted = fs.deletePath(path); 
                            System.out.println(isDeleted ? "Path deleted successfully" : "Failed to delete path");
                        } else {
                            System.out.println("Usage: delete <path>");
                        }
                        break;
                    case "display":
                        System.out.println("nFile System Structure:");
                        fs.display();
                        break;
                    case "exit":
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        
                        System.out.println(
                                "Unknown command. Available commands: create, write, read, delete, display, exit");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

/* Example Interaction:

File System Manager - Commands:
1. create <path> - Create a new path
2. write <path> <content> - Write content to a file
3. read <path> - Read content from a file
4. delete <path> - Delete a path
5. display - Show the entire file system structure 
6. exit - Exit the program
nEnter command: create /root.txt
Path created successfully
nEnter command: create /pdf/file.pdf
Path created successfully
nEnter command: create /pdf/file1.pdf
Path created successfully
nEnter command: create /word/file.word
Path created successfully
nEnter command: create /root.txt
Failed to create
nFile System Structure: display
? / (3 items)
  ? pdf (2 items)      
    ? file.pdf [.pdf]  
    ? file1.pdf [.pdf] 
  ? root.txt [.txt]    
  ? word (1 items)     
    ? file.word [.word]
nEnter command: write /root.txt "First Write"
Content written successfully
nEnter command: read /root.txt "First Write"
Content: "First Write"
nEnter command: exit  
Exiting...

*/