package com.example.GraphModel.UML_Model;

public class Method extends Typeable{

   Arguments arguments;
    
   public Method(String Visibility, String type, String name, String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        super(name, type, Visibility);
        this.arguments = new Arguments(args);
   }

   public Method(String type, String name, String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        this(null,type, name, args);
   }

   // definition: ReturnType MethodName(Arguments) / public ReturnType MethodName(Arguments) / public static ReturnType MethodName(Arguments)
   public static Method getMethodFromDefinition(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        String def = definition;
        if(def.contains(" throws ")){
            def = def.split(" throws ")[0];
        }
        String[] elements = def.substring(0, def.indexOf("(")).split(" ");
        String args = def.substring(def.indexOf("(") + 1, def.indexOf(")"));
        if(elements.length == 2){
            return new Method(elements[0], elements[1].split("\\(")[0], args.split(", "));
        }
        if(elements.length == 3){
            return new Method(elements[0], elements[1], elements[2].split("\\(")[0], args.split(", "));
        }
        if(elements.length == 4){
            Method newMethod = new Method(elements[0], elements[2], elements[3].split("\\(")[0], args.split(", "));
            newMethod.setStatic(true);
            return newMethod;
        }
        return null;
   }


    public String getStringAttributes(){
        return this.arguments.toString();
    }

    

    @Override
    public String toString(){
        return super.toString()+" "+this.getName()+this.arguments.toString()+"{\n\n\t}";
    }
    
}
