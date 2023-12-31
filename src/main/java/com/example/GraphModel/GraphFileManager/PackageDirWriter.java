package com.example.GraphModel.GraphFileManager;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.PackageClass;



public class PackageDirWriter {
    PackageClass myPackage;


    public PackageDirWriter(PackageClass p){
        this.myPackage = p;
    }

    /**
     * @description create directory with name and path arguments as String
     * @param dirName
     * @param dirPath
     * @throws IOException
     */
    public static void dirCreator(String dirName, String dirPath) throws IOException{
        if(!dirPath.endsWith("/")){
            dirPath+="/";
        }
        File dir = new File(dirPath+dirName);
        if(!dir.exists()){
            Files.createDirectory(Paths.get(dirPath+dirName));
        }
    }

    /**
     * @description create package directory and write all class in it
     * @param dirPath
     * @throws IOException
     */
    public void writePackage(String dirPath) throws IOException{
        PackageDirWriter.dirCreator(this.myPackage.getName(), dirPath);
        for(Classes c : this.myPackage.getClasses()){
            ClassWriter newClassWriter = new ClassWriter(c);
            newClassWriter.writeFile(dirPath+"/"+this.myPackage.getName()+"/");
        }
    }

}
