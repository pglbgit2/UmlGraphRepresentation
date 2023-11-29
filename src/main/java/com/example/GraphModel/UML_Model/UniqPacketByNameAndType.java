package com.example.GraphModel.UML_Model;

import java.util.ArrayList;
import java.util.HashMap;

public class UniqPacketByNameAndType<V extends Typeable> {
    private HashMap<String,V> myHashMap;

    public UniqPacketByNameAndType(){
        this.myHashMap = new HashMap<String,V>();
    }

    /**
     * 
     * @param value
     * @throws AlreadyExistingStringException
     */
    public void addValueByNameAndtype(V value) throws AlreadyExistingStringException{
        String name = value.getName();
        String type = value.getType();
        String key = name+type;
        if(this.myHashMap.containsKey(key)){
            throw new AlreadyExistingStringException(key);
        }
        this.myHashMap.put(key,value);
    }

    /**
     * 
     * @param name
     * @return Value matching Name, null if none is matching
     */
    public V getValue(String name, String type){
        String key = name+type;
        if(!this.myHashMap.containsKey(key)){
            return null;
        }
        return this.myHashMap.get(key);
    }

    /**
     * 
     * @param name
     */
    public void deleteValueByNameAndType(String name, String type){
        this.myHashMap.put(name+type, null);
    }

    public String toStringWithSeparator(String separator, boolean keepLastSeparator){
        String str = "";
        for(String key : this.myHashMap.keySet()){
            str += this.myHashMap.get(key)+separator;
        }
        if(!keepLastSeparator){
            str = str.substring(0, str.length()-separator.length());
        }
        return str;
    }
}
