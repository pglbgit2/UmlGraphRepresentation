package com.example.GraphModel.UML_Model;

import java.util.HashMap;

public class UniqPacketByKey<V> {
    private HashMap<String,V> myHashMap;

    public UniqPacketByKey(){
        this.myHashMap = new HashMap<String,V>();
    }

    /**
     * 
     * @param value
     * @throws AlreadyExistingStringException
     */
    public void addValueByKey(V value, String key) throws AlreadyExistingStringException{
        if(this.myHashMap.containsKey(key) && this.myHashMap.get(key) != null){
            throw new AlreadyExistingStringException(key);
        }
        this.myHashMap.put(key,value);
    }

    /**
     * 
     * @param key
     * @return Value matching key, null if none is matching
     */
    public V getValue(String key){
        if(!this.myHashMap.containsKey(key)){
            return null;
        }
        return this.myHashMap.get(key);
    }

    /**
     * 
     * @param key
     */
    public void deleteValueBykey(String key){
        this.myHashMap.put(key, null);
    }

    public String toStringWithSeparator(String separator, boolean keepLastSeparator){
        String str = "";
        for(String key : this.myHashMap.keySet()){
            String content = this.myHashMap.get(key).toString();
            if(content != null){
                str += content+separator;
            }
        }
        if(str != "" && !keepLastSeparator){
            str = str.substring(0, str.length()-separator.length());
        }
        return str;
    }
}
