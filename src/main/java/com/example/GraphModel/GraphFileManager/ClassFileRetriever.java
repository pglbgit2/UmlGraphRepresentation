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

    /**
     * @example removeTroubleSomeSpaces("       toto      tata    titi") -> "toto tata titi"
     * @param sentense
     * @return
     */
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
     * @example retrieveClass("/home/user/MyJavaProjects/someClass.java")
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
                                String supposedClassName = parsedLine[2];
                                if(supposedClassName.contains("<")){
                                    supposedClassName = supposedClassName.split("<")[0];
                                }
                                if(supposedClassName.compareTo(filename) != 0){
                                    theFile.close();
                                    throw new NotGoodFormatException(filename, supposedClassName);
                                }
                                newClasse = new Classes(filename, new String[]{});
                                //System.out.println("class def:"+newLine);
                                hasPassedClassDefinition = true;
                            }
                        }
                        
                        if(hasPassedClassDefinition && newLine.endsWith(";") && nbOfHookOpened == 1){
                            //isAttribute
                            newClasse.addAttribute(newLine.substring(0, newLine.indexOf(";")));
                            //System.out.println("Attribute:"+newLine);
                        }  

                        if(hasPassedClassDefinition && nbOfHookOpened == 1 && !newLine.endsWith(";")){
                            //isMethod
                            //System.out.println("Method:"+newLine);
                            
                            if(isConstructor(newLine, newClasse.getName())){
                                //System.out.println("Constructor:"+newLine);
                                String argString = newLine.substring(newLine.indexOf("(") + 1, newLine.indexOf(")"));
                                String[] args = argString.split(", ");
                                newClasse.addConstructor(args);
                            }
                            else{
                                newClasse.addMethod(newLine.substring(0, newLine.indexOf(")")+1));
                            }
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



    /**
     * @BEWARE do not use this function outside of retrieveClass. Its purpose is to work in a special context, will not work otherwise
     * @param newLine
     * @param name
     * @return
     */
    private boolean isConstructor(String newLine, String name) {
        return newLine.startsWith("public "+name+"(");
    }

 
}
