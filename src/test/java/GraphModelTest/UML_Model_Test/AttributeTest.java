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

    @Test
    public void attributeFromStringTest(){
        try{
            Attribute attribute2 = Attribute.getAttributeFromString("protected Attribute attribute2");
            assertEquals(attribute2.toString(),"protected Attribute attribute2");
        }
        catch(NoValidVisibilityException nVVE){
            Assert.fail();
        }
    }
   
}
