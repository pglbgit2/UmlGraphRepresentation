package com.example.GraphModel.GraphFileManager;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.GraphModel.UML_Model.Classes;


public class ClassWriter {
    Classes myClasse;

    public ClassWriter(Classes someClasse){
        this.myClasse = someClasse;
    }

    
    /**
     * 
     * @param filedirpath
     * @throws IOException
     */
    public void writeFile(String filedirpath) throws IOException{
        String filepath = filedirpath+this.myClasse.getName()+".java";
        File theFile = new File(filepath);
        theFile.createNewFile(); // if file already exists will do nothing 
        PrintWriter someWriter = new PrintWriter(theFile);
        someWriter.println(this.myClasse.toString());
        someWriter.close();
    }
}
