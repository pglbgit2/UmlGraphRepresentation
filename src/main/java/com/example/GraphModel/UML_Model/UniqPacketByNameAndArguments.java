package com.example.GraphModel.UML_Model;


public class UniqPacketByNameAndArguments<V extends HaveArguments & Nameable> extends UniqPacketByKey<V> {

    public void addValue(V value) throws AlreadyExistingStringException{
        String name = value.getName();
        String args = value.getArguments().toString();
        String key = name+args;
        super.addValueByKey(value, key);
    }

    
    public void deleteValue(String name, String[] args){
        super.deleteValueBykey(name+args.toString());
    }

    public String toString(){
        return super.toStringWithSeparator("\n", false);
    }


    public boolean hasValueWithArgsAndName(String name, String args[]) {
        return false;
    }
}
