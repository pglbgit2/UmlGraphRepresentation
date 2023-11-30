package com.example.GraphModel.UML_Model;

public class PackageClass extends UniqPacketByName<Classes> implements Nameable {
    private String name;

    public PackageClass(String name){
        this.name = name;
    }

    public String toString(){
        return this.name+"\n********\n"+this.toStringWithSeparator("\n-----------------\n", false);
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
