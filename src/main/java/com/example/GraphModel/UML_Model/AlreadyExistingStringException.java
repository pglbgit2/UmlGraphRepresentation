package com.example.GraphModel.UML_Model;

public class AlreadyExistingStringException extends Exception{
    String wantedValue;
    public AlreadyExistingStringException(String Value){
        this.wantedValue = Value;
    }

    public String getWanted(){
        return this.wantedValue;
    }
}
