package GraphModelTest.GraphFileManagerTest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.GraphModel.GraphFileManager.ClassFileRetriever;
import com.example.GraphModel.GraphFileManager.NotGoodFormatException;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

public class ClassFileRetrieverTest {
    @Test
    public void fileRetrieveTest(){
        ClassFileRetriever cfr = new ClassFileRetriever();
        try {
            Classes class1 = cfr.retrieveClass("./src/main/java/com/example/GraphModel/UML_Model/Attribute.java");
            System.out.println(class1.toString());
        } catch (IOException | NotGoodFormatException | NoValidVisibilityException | AlreadyExistingStringException e) {
            Assert.fail();
            e.printStackTrace();
        }   
    }
}
