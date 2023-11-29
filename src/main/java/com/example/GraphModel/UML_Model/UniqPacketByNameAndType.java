package com.example.GraphModel.UML_Model;

import java.util.HashMap;

public class UniqPacketByNameAndType<V extends Typeable>  extends UniqPacketByKey<V>{
    /**
     * 
     * @param value
     * @throws AlreadyExistingStringException
     */
    public void addValueByNameAndtype(V value) throws AlreadyExistingStringException{
        String name = value.getName();
        String type = value.getType();
        String key = name+type;
        super.addValueByKey(value, key);
    }

    /**
     * 
     * @param name
     * @return Value matching Name, null if none is matching
     */
    public V getValue(String name, String type){
       return super.getValue(name+type);
    }

    /**
     * 
     * @param name
     */
    public void deleteValueByNameAndType(String name, String type){
        super.deleteValueBykey(name+type);
    }

}
