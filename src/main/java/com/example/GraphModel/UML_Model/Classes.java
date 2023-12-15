package com.example.GraphModel.UML_Model;
import java.util.ArrayList;

import com.example.GraphModel.GraphFileManager.NotGoodFormatException;

public class Classes implements Nameable {
    private Arguments myAttributes;
    private UniqPacketByNameAndArguments<Method> myMethods;
    private String name;
    private String parentName;

    public Classes(String name, String[] attributes) throws NoValidVisibilityException, AlreadyExistingStringException{
        this.name = name;
        this.myMethods = new UniqPacketByNameAndArguments<Method>();
        this.myAttributes = new Arguments(attributes);
        this.parentName = "";
    }

    public Classes(String name, String parentName, String[] attributes) throws NoValidVisibilityException, AlreadyExistingStringException{
        this(name, attributes);
        this.parentName = parentName;
    }

    /**
     * @example definition: int toto, private String name, + int nbElements...
     * @specification : Attribute from Classes definition must respect Attribute definition from Attribute class
     * @param definition
     * @throws NoValidVisibilityException
     * @throws AlreadyExistingStringException
     */
    public Attribute addAttribute(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        Attribute toAdd = Attribute.getAttributeFromString(definition);
        this.myAttributes.addAttributeByName(toAdd);
        return toAdd;
    }

    public void addMethod(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        Method toAdd = Method.getMethodFromDefinition(definition);
        if(toAdd != null){
            this.myMethods.addValue(toAdd);
       }
    }
    
    public boolean hasMethod(String name, String[] args) throws NotGoodFormatException {
        return this.myMethods.hasValueWithArgsAndName(name, args);
    }

    public boolean hasConstructor(String[] args) {
        return this.myMethods.hasValueWithArgsAndName(this.name,args);
    }

    public void addConstructor(String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        Method constructor = new Method("public",null, this.name, args);
        if(constructor != null){
            this.myMethods.addValue(constructor);
        }
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

     public ArrayList<Method> getMethods(){
        return this.myMethods.getPackets();
    }

    public boolean hasAttribute(Attribute a){
        return this.myAttributes.hasAttribute(a);
    }

    public boolean hasMethod(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        Method m = Method.getMethodFromDefinition(definition);
        return this.myMethods.hasValueWithArgsAndName(m.getName(), (String[]) m.getArguments().getArgs().toArray());
    }

    public String manageExtends(){
        if(this.parentName.compareTo("") == 0){
            return "";
        }
        return " extends "+parentName;
    }

    public String toString(){
        return "public class "+this.getName()+manageExtends()+"{\n\t"+this.myAttributes.toStringWithSeparator(";\n\t", true)+this.myMethods.toString()+"}";
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public void setName(String name) {
       this.name = name;
    }

    public void addAttribute(Attribute newAttribute) throws AlreadyExistingStringException {
        this.myAttributes.addAttributeByName(newAttribute);
    }

    public void resetAttributes() {
        try {
            this.myAttributes = new Arguments(null);
        } catch (NoValidVisibilityException e) {
        } catch (AlreadyExistingStringException e) {
        }
    }

    public void resetMethods() {
        this.myMethods = new UniqPacketByNameAndArguments<Method>();
    }

    

    

   
}
