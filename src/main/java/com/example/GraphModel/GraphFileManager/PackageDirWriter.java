package com.example.GraphModel.GraphFileManager;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.PackageClass;



public class PackageDirWriter {
    PackageClass myPackage;


    public PackageDirWriter(PackageClass p){
        this.myPackage = p;
    }

    public static void dirCreator(String dirName, String dirPath) throws IOException{
        Files.createDirectory(Paths.get(dirPath+dirName));
    }

    public void writePackage(String dirPath) throws IOException{
        PackageDirWriter.dirCreator(this.myPackage.getName(), dirPath);
        for(Classes c : this.myPackage.getPackets()){
            ClassWriter newClassWriter = new ClassWriter(c);
            newClassWriter.writeFile(dirPath+"/"+this.myPackage.getName()+"/");
        }
    }

}
