package com.example.GraphModel.GraphFileManager;

public class NotGoodFormatException extends Exception{
    String expect;
    String actual;
    public NotGoodFormatException(String expectedFormat, String actualFormat){
        this.actual = actualFormat;
        this.expect = expectedFormat;
    }  

    public String getExpected(){
        return this.expect;
    }

    public String getActual(){
        return this.actual;
    }
}
