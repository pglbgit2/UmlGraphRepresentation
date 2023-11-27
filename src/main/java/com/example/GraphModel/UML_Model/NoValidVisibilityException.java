package com.example.GraphModel.UML_Model;

public class NoValidVisibilityException extends Exception {
    String attribute;
    public NoValidVisibilityException(String attr){
        this.attribute = attr;
    }
}
