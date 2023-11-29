package com.example.GraphModel.UML_Model;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * @description hashtable identify by name (String)
 */
public class UniqPacketByName<V extends Nameable> {
    private HashMap<String,V> myHashMap;
    private ArrayList<String> myKeys;

    public UniqPacketByName(){
        this.myHashMap = new HashMap<String,V>();
    }

    /**
     * 
     * @param value
     * @throws AlreadyExistingStringException
     */
    public void addValueByName(V value) throws AlreadyExistingStringException{
        String name = value.getName();
        if(this.myHashMap.containsKey(name)){
            throw new AlreadyExistingStringException(name);
        }
        this.myHashMap.put(name,value);
    }

    /**
     * 
     * @param name
     * @return Value matching Name, null if none is matching
     */
    public V getValue(String name){
        if(!this.myHashMap.containsKey(name)){
            return null;
        }
        return this.myHashMap.get(name);
    }

    /**
     * 
     * @param name
     */
    public void deleteValueByName(String name){
        this.myHashMap.put(name, null);
    }
}
