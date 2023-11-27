package GraphModelTest.UML_Model_Test;

import static org.junit.Assert.assertEquals;


import org.junit.Assert;
import org.junit.Test;

import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphModel.UML_Model.Typeable;

public class TypeableTest {
    
    @Test
    public void oneTypeTest(){
        try{
            Typeable typeable1 = new Typeable("typeable1", "Typeable");
            assertEquals( "Typeable", typeable1.toString());
        }
        catch(NoValidVisibilityException nVVE){
            Assert.fail();
        }
    }

    @Test
    public void typeWithVisionTest(){
        try{
            Typeable typeable2 = new Typeable("typeable2", "Typeable","private");
            assertEquals("private Typeable", typeable2.toString());
        }
        catch(NoValidVisibilityException nVVE){
            Assert.fail();
        }
    }

    @Test
    public void typeWithVisionCharTest(){
        try{
            Typeable typeable3 = new Typeable("typeable3", "Typeable", "-");
            assertEquals("private Typeable", typeable3.toString());
        }
        catch(NoValidVisibilityException nVVE){
            Assert.fail();
        }
    }


    @Test
    public void typeWithNoValidVision(){
        try{
            Typeable typeable4 = new Typeable("typeable3", "Typeable", "notCorrectVision");
            Assert.fail();
        }
        catch(NoValidVisibilityException nVVE){
            // supposed to happen
        }
    }

}
