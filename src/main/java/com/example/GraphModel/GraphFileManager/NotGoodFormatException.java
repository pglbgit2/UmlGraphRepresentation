package com.example.GraphModel.GraphFileManager;

public class NotGoodFormatException extends Exception{
    String expect;
    String actual;
    /**
     * @example filename: SomeClass.jav throw Exception: NotGoodFormatException expect SomeClass.java
     * @param expectedFormat
     * @param actualFormat
     */
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
