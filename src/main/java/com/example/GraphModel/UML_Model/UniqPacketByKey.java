package com.example.GraphModel.UML_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class UniqPacketByKey<V> {
    protected HashMap<String,V> myHashMap;

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
    public V deleteValueBykey(String key){
        V val = this.myHashMap.get(key);
        this.myHashMap.put(key, null);
        return val;
    }

    public Set<String> getKeysSet(){
        return this.myHashMap.keySet();
    }
    
    public ArrayList<V> getPackets(){
        ArrayList<V> theValues = new ArrayList<V>();
        for(String key : this.myHashMap.keySet()){
            V Val = this.myHashMap.get(key);
            if(Val != null){
                theValues.add(Val);
            }
        }
        return theValues;
    }

    // null values will not be count 
    public int getSize(){
        ArrayList<V> theValues = new ArrayList<V>();
        for(String key : this.myHashMap.keySet()){
            V Val = this.myHashMap.get(key);
            if(Val != null){
                theValues.add(Val);
            }
        }
        return theValues.size();
    }

    
    // ["A","B","C"] -> toStringWithSeparator(", ",false) -> "A, B, C"
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
