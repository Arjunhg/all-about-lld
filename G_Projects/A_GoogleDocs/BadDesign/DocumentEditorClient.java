package G_Projects.A_GoogleDocs.BadDesign;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

class DocumentEditor {
    private List<String> documentElements;
    private String renderedDocument;

    public DocumentEditor(){
        documentElements = new ArrayList<>();
        renderedDocument = "";
    }

    void addText(String text){
        documentElements.add(text);
    } 
    void addImage(String imagePath){
        documentElements.add(imagePath);
    }

    // render the document by checking type of each element
    String renderDocument(){
        if(renderedDocument.isEmpty()){
            StringBuilder result = new StringBuilder();
            for(String element : documentElements){
                if(element.length() > 4 && (element.endsWith(".jpg") || element.endsWith(".png"))){
                    result.append("[Image: ").append(element).append("]\n");
                } else {
                    result.append(element).append("\n");
                }
            }
            renderedDocument = result.toString();
        }
        return renderedDocument;
    }

    void saveToFile(){
        try {
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.out.println("Document saved to file.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save document to file.");
        }
    }
}

public class DocumentEditorClient {
    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();
        editor.addText("Hello, this is a sample document.");
        editor.addImage("image1.jpg");
        editor.addText("This document contains text and images.");

        System.out.println(editor.renderDocument());

        editor.saveToFile();
    }
}
