package com.example.GraphModel.UML_Model;

import java.util.ArrayList;

public class Arguments {
    private ArrayList<Attribute> argumentList;
    /**
     * @example ["public int number", "String name", "public transient static int somme"]
     * @requirements Visibility must be first, name last and type penultimate, and if theres more than name and type visibility must be include
     * @param attributeStringTab
     */
    public Arguments(String[] attributeStringTab) throws NoValidVisibilityException{
        this.argumentList = new ArrayList<Attribute>();
        for(String attString : attributeStringTab){
            String[] att = attString.split(" ");
            Attribute newAtt;
            int n = att.length;
            if(n > 2){
                if(!hasVisibility(att[0])){
                    throw new NoValidVisibilityException(attString);
                }
                newAtt = new Attribute(att[n-1], att[n-2], att[0]);
            }
            else{
                newAtt = new Attribute(att[1], att[0]);
            }
            this.argumentList.add(newAtt);
        }
    }

    private boolean hasVisibility(String firstWordOfAttribute){
        return (firstWordOfAttribute.compareToIgnoreCase("private") == 0 || firstWordOfAttribute.compareToIgnoreCase("public") == 0 || firstWordOfAttribute.compareToIgnoreCase("protected") == 0 || firstWordOfAttribute.compareTo("+") == 0 || firstWordOfAttribute.compareTo("-") == 0 || firstWordOfAttribute.compareToIgnoreCase("p") == 0);
    }

    public String toString(){
        String str ="(";
        str += this.argumentList.get(0).toString();
        for(int i = 1; i < this.argumentList.size(); i++){
           str += ", "+this.argumentList.get(i).toString();
        }
        str+=")";
        return str;
    }
}
