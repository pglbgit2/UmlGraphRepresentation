package com.example.GraphModel.UML_Model;

import java.util.ArrayList;
import java.util.List;


public class PackageClass extends UniqPacketByName<Classes> implements Nameable {
    private String name;

    public PackageClass(String name){
        this.name = name;
    }

    public String toString(){
        return this.name+"\n********\n"+this.toStringWithSeparator("\n-----------------\n", false);
    }

 
    public Classes[] getClasses(){
        List<Classes> theValues = new ArrayList<Classes>();
        for(String key : myHashMap.keySet()){
            theValues.add(myHashMap.get(key));
        }
        Classes[] arrayClass = new Classes[theValues.size()];
        theValues.toArray(arrayClass);
        return arrayClass;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
