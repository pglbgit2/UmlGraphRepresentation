package com.example.GraphModel.UML_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    public Set<String> getKeysSet(){
        return this.myHashMap.keySet();
    }
    
    @SuppressWarnings("unchecked")
    public V[] getPackets(){
        ArrayList<V> theValues = new ArrayList<V>();
        for(String key : this.myHashMap.keySet()){
            theValues.add(this.myHashMap.get(key));
        }
        return (V[]) theValues.toArray();
    }

    public String[] getValues(){
        ArrayList<String> theValues = new ArrayList<String>();
        for(String key : this.myHashMap.keySet()){
            theValues.add(this.myHashMap.get(key).toString());
        }
        return (String[]) theValues.toArray();
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
