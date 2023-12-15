package com.example.GraphController;

public class ClassInfo {
    String name;
    String parentName;
    String[] args;

    public ClassInfo(String name, String parentName, String[] args){
        this.name = name;
        this.parentName = parentName;
        this.args = args;
    }

    public ClassInfo(String name){
        this(name,"", new String[]{});
    }

    public ClassInfo(String name, String[] args){
        this(name,"",args);
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parentName;
    }

    public String[] getArgs() {
        return args;
    }


}
