package com.example.GraphModel.UML_Model;

import java.util.ArrayList;

public class Arguments {
    private UniqPacketByName<Attribute> argumentList;
    /**
     * @example ["public int number", "String name", "public transient static int somme"]
     * @requirements Visibility must be first, name last and type penultimate, and if theres more than name and type visibility must be include
     * @param attributeStringTab
     * @throws AlreadyExistingStringException
     */
    public Arguments(String[] attributeStringTab) throws NoValidVisibilityException, AlreadyExistingStringException{
        this.argumentList = new UniqPacketByName<Attribute>();
        for(String attString : attributeStringTab){
            Attribute newAtt = Attribute.getAttributeFromString(attString);
            if(newAtt != null){
                this.argumentList.addValueByName(newAtt);
            }
        }
    }


    public void addAttributeByName(Attribute a) throws AlreadyExistingStringException{
        this.argumentList.addValueByKey(a, a.getName());
    }

    // attString must respect Attribute conventions
    public void addAttributeByString(String attString) throws NoValidVisibilityException, AlreadyExistingStringException{
        Attribute toAdd = Attribute.getAttributeFromString(attString);
        this.argumentList.addValueByKey(toAdd, toAdd.name);
    }

    public void deleteAttribute(String name){
        this.argumentList.deleteValueBykey(name);
    } 

    
    public ArrayList<Attribute> getArgs(){
        return this.argumentList.getPackets();
    }

    public String toStringWithSeparator(String separator, boolean keepLastSeparator){
        String str = "";
        str+=this.argumentList.toStringWithSeparator(separator, keepLastSeparator);
        return str;
    }

    public String toString(){
        String str = "";
        str+=this.argumentList.toStringWithSeparator(", ", false);
        return str;
    }


    public boolean hasAttribute(Attribute a) {
        return this.argumentList.hasValueWithName(a.getName());
    }


    
    
}
