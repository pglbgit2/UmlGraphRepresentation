package com.example.GraphModel.UML_Model;

/**
 * Used in UniqPackets
 * @example:
 * new value id3 -> ["id1","id2"] -> OK
 * new value id1 -> ["id1", "id2", "id3"] -> throw AlreadyExistingStringException 
 */
public class AlreadyExistingStringException extends Exception{
    String wantedValue;
    public AlreadyExistingStringException(String Value){
        this.wantedValue = Value;
    }

    public String getWanted(){
        return this.wantedValue;
    }
}
