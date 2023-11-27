package com.example.GraphModel.UML_Model;

/**
 * @author Pierre-Gilles Le Botlan
 * @description 
 * The Typeable class model the type & visibility & optional information of something that is typeable (function, attribute)
 */
public class Typeable implements Nameable {
    String type; // int, String, ArrayList<> ...
    String visibility; // public <=> +, private <=> - , protected <=> P, 
    boolean isTransient; // if the element that is typeable is going to be serialized
    boolean isStatic; // if is class attribute, not just instance attribute
    String name; // name of the element that is typeable

    public Typeable(String name, String _type, String _visibility) throws NoValidVisibilityException{
        this.type = _type;
        this.name = name;
        this.visibility = _visibility;
        this.isStatic = false;
        this.isTransient = false;
        if( _visibility != null){
            boolean isValidVisibility = false;
            if(_visibility.compareTo("+") == 0 || _visibility.compareTo("public") == 0){
                this.visibility = "public";
                isValidVisibility = true;
            }
            if( _visibility.compareTo("-") == 0 || _visibility.compareTo("private") == 0){
                this.visibility = "private";
                isValidVisibility = true;
            }
            if(_visibility.compareTo("P") == 0 || _visibility.compareTo("protected") == 0){
                this.visibility = "protected";
                isValidVisibility = true;
            }
            if(isValidVisibility == false){
                throw new NoValidVisibilityException(_visibility);
            }
        }
    }

    /**
     * 
     * @param name
     * @param _type
     */
    public Typeable(String name, String _type)throws NoValidVisibilityException {
        this(name, _type, null);
    }

    /**
     * 
     * @param name
     */
    public Typeable(String name) throws NoValidVisibilityException{
        this(name, null, null);
    }

    
    public void setStatic(boolean b){
        this.isStatic = b;
    }

   
    public void setTransient(Boolean b){
        this.isTransient = b;
    }
    
    public String getVisibility(){
        return this.visibility;
    }

    public void setVisibility(String _visibility){
        if( _visibility != null){
            if(_visibility.compareTo("+") == 0 || _visibility.compareTo("public") == 0){
                this.visibility = "public";
            }
            if( _visibility.compareTo("-") == 0 || _visibility.compareTo("private") == 0){
                this.visibility = "private";
            }
            if(_visibility.compareTo("P") == 0 || _visibility.compareTo("protected") == 0){
                this.visibility = "protected";
            }
        }
    }

    public String getType(){
        return this.type;
    }

    public String toString(){
        String  s = "";
        if(this.visibility != null){
            s+=visibility+" ";
        }
        if(this.isStatic){
            s += "static ";
        }
        if(isTransient){
            s+="transient ";
        }
        if(this.type != null){
            s += this.type;
        }
        return s;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
       this.name = newName;
    }    
}
