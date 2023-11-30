package GraphModelTest.UML_Model_Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphModel.UML_Model.PackageClass;

public class PackageTest {
    @Test
    public void testPackage(){
        PackageClass p1 = new PackageClass("UML_MODEL");
        assertEquals("UML_MODEL\n********\n", p1.toString());
    }

    @Test
    public void testPackage2(){
        PackageClass p2 = new PackageClass("UML_MODEL");
        String[] attr = new String[]{"int toto","int tata"};
        try {
            p2.addValueByKey(new Classes("classe1", attr), "classe1");
            p2.addValueByKey(new Classes("classe2", attr), "classe2");

        } catch (AlreadyExistingStringException | NoValidVisibilityException e) {
            e.printStackTrace();
            Assert.fail();
        }
        assertEquals("UML_MODEL\n********\npublic class classe2{\n\tint toto;\n\tint tata;\n\t}\n-----------------\npublic class classe1{\n\tint toto;\n\tint tata;\n\t}", p2.toString());
    }    
}
