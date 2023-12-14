package com.example.GraphModel.UML_Model;
//TODO: treat the throws exception
public class Method extends Typeable implements HaveArguments{

   Arguments arguments;

/**
 * @information: Do not use this method, use getMethodFromDefinition
 * @param Visibility
 * @param type
 * @param name
 * @param args
 * @throws NoValidVisibilityException
 * @throws AlreadyExistingStringException
 */
   public Method(String Visibility, String type, String name, String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        super(name, type, Visibility);
        this.arguments = new Arguments(args);
   }

   /**
    * @information Do not use constructor, use getMethodFromDefinition 
    * @param type
    * @param name
    * @param args
    * @throws NoValidVisibilityException
    * @throws AlreadyExistingStringException
    */
   public Method(String type, String name, String[] args) throws NoValidVisibilityException, AlreadyExistingStringException{
        this(null,type, name, args);
   }

   /**
    * @definition ReturnType MethodName(Arguments) / public ReturnType MethodName(Arguments) / public static ReturnType MethodName(Arguments)
    * @param definition
    * @return
    * @throws NoValidVisibilityException
    * @throws AlreadyExistingStringException
    */
   public static Method getMethodFromDefinition(String definition) throws NoValidVisibilityException, AlreadyExistingStringException{
        String def = definition;
        if(def.contains(" throws ")){
            def = def.split(" throws ")[0];
        }
        //System.out.println(def);
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

    public String getMethodForGUI(){
        return super.toString()+" "+this.getName()+"("+this.arguments.toString()+")";
    }

    @Override
    public String toString(){
        return super.toString()+" "+this.getName()+"("+this.arguments.toString()+"){\n\n\t}";
    }

    @Override
    public Arguments getArguments() {
        return this.arguments;
    }

    public boolean compareTo(Method m2) {
        if(m2.getName() != this.name){
            return false;
        }
        return m2.getArguments().compareTo(this.getArguments());
    }
    
}
