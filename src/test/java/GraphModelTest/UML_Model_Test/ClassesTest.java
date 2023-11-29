package GraphModelTest.UML_Model_Test;

import org.junit.Assert;
import org.junit.Test;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

public class ClassesTest {
    @Test
    public void testOneClass(){
        String[] attr = new String[]{"int valeur1","String nom"};
        try {
            Classes classe1 = new Classes("classe1", attr);
            classe1.addMethod("public void addMethod()");
            System.out.println(classe1.toString());
        } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
