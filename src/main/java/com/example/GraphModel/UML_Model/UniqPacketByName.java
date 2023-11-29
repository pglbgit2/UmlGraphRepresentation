package com.example.GraphModel.UML_Model;

/**
 * @description hashtable identify by name (String)
 */
public class UniqPacketByName<V extends Nameable> extends UniqPacketByKey<V> {

    /**
     * 
     * @param value
     * @throws AlreadyExistingStringException
     */
    public void addValueByName(V value) throws AlreadyExistingStringException{
        String name = value.getName();
        super.addValueByKey(value, name);
    }

    /**
     * 
     * @param name
     * @return Value matching Name, null if none is matching
     */
    public V getValue(String name){
        return super.getValue(name);
    }

    /**
     * 
     * @param name
     */
    public void deleteValueByName(String name){
        super.deleteValueBykey(name);
    }

}
