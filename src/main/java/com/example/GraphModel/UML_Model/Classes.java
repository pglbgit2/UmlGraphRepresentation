package com.example.GraphModel.UML_Model;
//TODO: treat the extends

import java.util.ArrayList;

public class Classes implements Nameable {
    private Arguments myAttributes;
    private UniqPacketByNameAndArguments<Method> myMethods;
    private String name;

    public Classes(String name, String[] attributes) throws NoValidVisibilityException, AlreadyExistingStringException{
        this.name = name;
        this.myMethods = new UniqPacketByNameAndArguments<Method>();
        this.myAttributes = new Arguments(attributes);
    }

    /**
     * @example definition: int toto, private String name, + int nbElements...
     * @specification : Attribute from Classes definition must respect Attribute definition from Attribute class
     * @param definition
     * @throws NoValidVisibilityException
     * @throws AlreadyExistingStringException
     */
    public void addAttribute(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        Attribute toAdd = Attribute.getAttributeFromString(definition);
        this.myAttributes.addAttributeByType(toAdd);
    }

    public void addMethod(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        Method toAdd = Method.getMethodFromDefinition(definition);
        this.myMethods.addValue(toAdd);
    }


    public void addConstructor(String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        Method constructor = new Method("public",null, this.name, args);
        this.myMethods.addValue(constructor);
    }

    public void removeAttribute(String name){
        this.myAttributes.deleteAttribute(name);
    }

    public void removeMethod(String methodName, String[] args){
        this.myMethods.deleteValue(methodName, args);
    }


    public ArrayList<Attribute> getAttributes(){
        return this.myAttributes.getArgs();
    }

    public void modifyMethod(){
        //TODO
    }

    public void modifyAttribute(){
        //TODO
    }


    public String toString(){
        return "public class "+this.getName()+"{\n\t"+this.myAttributes.toStringWithSeparator(";\n\t", true)+this.myMethods.toString()+"}";
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
