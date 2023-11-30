package com.example.GraphModel.GraphFileManager;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
/**
 * @author: Pierre-Gilles Le Botlan
 * Objective: Transform text file into Classe instance
 */
public class ClassFileRetriever {

    public int test;
    public ClassFileRetriever(){

    }

    public static String removeTroublesomeSpaces(String sentense){
        String str = new String(sentense);
        str =  str.replaceAll("^\\s+|\\s{2,}|\\s+$", "") ;
        return str;
    }

   

    /**
     * 
     * @param filepath
     * @return Classe : a Class extracted from file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotGoodFormatException
     * @throws AlreadyExistingStringException
     * @throws NoValidVisibilityException
     */
    public Classes retrieveClass(String filepath) throws FileNotFoundException, IOException, NotGoodFormatException, NoValidVisibilityException, AlreadyExistingStringException{
        String[] pathElements = filepath.split("/");
        String file = pathElements[pathElements.length-1];
        if(!file.contains(".java")){
            throw new NotGoodFormatException(file+".java",file);
        }
        String filename = file.replace(".java", "");
        Classes newClasse = null;
        BufferedReader theFile = new BufferedReader(new InputStreamReader (new FileInputStream (filepath)));
        String newLine = theFile.readLine();
        boolean hasPassedConstructor = false;
        boolean hasPassedClassDefinition = false;
        int nbOfHookOpened = 0;

        while(newLine != null){
            newLine = removeTroublesomeSpaces(newLine);
                if(!newLine.startsWith("*") && !newLine.startsWith("import") &&!newLine.startsWith("package") &&!newLine.startsWith("//") &&!newLine.startsWith("*")&&!newLine.startsWith("/*")){
                    if(newLine.contains(" ")){
                        if( newLine.contains("class ")){
                            String[] parsedLine = newLine.split(" ");
                            if(!hasPassedClassDefinition && parsedLine.length >= 3){
                                //isClassDefinition
                                if(parsedLine[2].compareTo(filename) != 0){
                                    theFile.close();
                                    throw new NotGoodFormatException(filename, parsedLine[2]);
                                }
                                newClasse = new Classes(filename, new String[]{});
                                hasPassedClassDefinition = true;
                            }
                        }

                        if(hasPassedClassDefinition && !hasPassedConstructor && newLine.endsWith(";") && nbOfHookOpened == 1){
                            //isAttribute
                            newClasse.addAttribute(newLine.substring(newLine.indexOf(";")));
                            //System.out.println("Attribute:"+newLine);
                        }  

                        if(hasPassedClassDefinition && nbOfHookOpened == 1 && !newLine.endsWith(";")){
                            //isMethod
                            newClasse.addMethod(newLine.substring(newLine.indexOf("\\)")));
                        }
                    }

                if(newLine.endsWith("{")){
                    nbOfHookOpened += 1;
                }
                    
                if(newLine.endsWith("}")){
                    nbOfHookOpened -= 1;
                }
            }
            newLine = theFile.readLine();
        }
        
        theFile.close();
        return newClasse;
    }

 
}
