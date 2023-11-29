package com.example.GraphModel.UML_Model;


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
            this.argumentList.addValueByName(newAtt);
        }
    }

    private boolean hasVisibility(String firstWordOfAttribute){
        return (firstWordOfAttribute.compareToIgnoreCase("private") == 0 || firstWordOfAttribute.compareToIgnoreCase("public") == 0 || firstWordOfAttribute.compareToIgnoreCase("protected") == 0 || firstWordOfAttribute.compareTo("+") == 0 || firstWordOfAttribute.compareTo("-") == 0 || firstWordOfAttribute.compareToIgnoreCase("p") == 0);
    }

    public String toString(){
        String str ="(";
        str+=this.argumentList.toStringWithSeparator(", ", false);
        str+=")";
        return str;
    }
}
