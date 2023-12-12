package com.example.GraphModel.GraphFileManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.GraphModel.UML_Model.Classes;


public class ClassWriter {
    Classes myClasse;

    public ClassWriter(Classes someClasse){
        this.myClasse = someClasse;
    }

    /**
     * @Use this is used for testing with temporal files and to avoid code duplication with writeFile.
     * @param theFile
     * @throws FileNotFoundException
     */
    public void writeFileWithFile(File theFile) throws FileNotFoundException{
        PrintWriter someWriter = new PrintWriter(theFile);
        someWriter.print(this.myClasse.toString());
        someWriter.close();
    }
    /**
     * write instance of Classes in file with given name 
     * @param filedirpath
     * @throws IOException
     */
    public void writeFile(String filedirpath) throws IOException{
        String filepath = filedirpath+this.myClasse.getName()+".java";
        File theFile = new File(filepath);
        theFile.createNewFile(); // if file already exists will do nothing 
        writeFileWithFile(theFile);
    }
}
