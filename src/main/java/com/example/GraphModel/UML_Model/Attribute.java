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
