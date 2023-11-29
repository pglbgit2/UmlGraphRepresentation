package com.example.GraphModel.UML_Model;


/**
 * @author Pierre-Gilles Le Botlan
 * @description modelling attribute
 * 
 * @extend Typeable
 */
public class Attribute extends Typeable{
    /**
     * @description create attribute from name and type
     * @param name : String
     * @param type : String
     */
    public Attribute(String name, String type) throws NoValidVisibilityException {
        super(name, type);
    }

    /**
     * @description create attribute from name and type, used with class attributes with visibility 
     * @param name : String
     * @param type : String 
     * @param vision : String
     */
    public Attribute(String name, String type, String vision) throws NoValidVisibilityException{
        super(name, type, vision);
    }

    public static Attribute getAttributeFromString(String attString) throws NoValidVisibilityException{
        String[] att = attString.split(" ");
        Attribute newAtt = null;
        int n = att.length;
        if(n > 2){
            if(!hasVisibility(att[0])){
                throw new NoValidVisibilityException(attString);
            }
            newAtt = new Attribute(att[n-1], att[n-2], att[0]);
        }
        else if(n == 2){
            newAtt = new Attribute(att[1], att[0]);
        }

        return newAtt;
    }

    static boolean hasVisibility(String firstWordOfAttribute){
        return (firstWordOfAttribute.compareToIgnoreCase("private") == 0 || firstWordOfAttribute.compareToIgnoreCase("public") == 0 || firstWordOfAttribute.compareToIgnoreCase("protected") == 0 || firstWordOfAttribute.compareTo("+") == 0 || firstWordOfAttribute.compareTo("-") == 0 || firstWordOfAttribute.compareToIgnoreCase("p") == 0);
    }

    public String getType(){
        return super.type;
    }

    public String getVisibility(){
        return super.visibility;
    }


    /**
     * @return String: Visibility Type Name
     */
    @Override
    public String toString(){
        return super.toString()+" "+this.getName();
    }
}
