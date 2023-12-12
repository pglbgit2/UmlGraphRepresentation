package com.example.GraphModel.UML_Model;
/**
 * thrown if visibility is different from public, private, protected 
 *  
 */
public class NoValidVisibilityException extends Exception {
    String attribute;
    public NoValidVisibilityException(String attr){
        this.attribute = attr;
    }

    public String getAttr(){
        return this.attribute;
    }
}
