package GraphModelTest.UML_Model_Test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.GraphModel.UML_Model.Attribute;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

import org.junit.Assert;

public class AttributeTest {
    @Test
    public void attributeTest(){
        try{
            Attribute attribute1 = new Attribute("attribute1", "Attribute","private");
            assertEquals(attribute1.toString(),"private Attribute attribute1");
        }
        catch(NoValidVisibilityException nVVE){
            Assert.fail();
        }
    }   
}
