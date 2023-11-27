package GraphModelTest.UML_Model_Test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.example.GraphModel.UML_Model.Arguments;

import org.junit.Assert;

public class ArgumentTest {
    @Test
    public void argumentTest(){
        try{
            String[] arguments = new String[] {"P int nb", "+ String name", "- ArrayList<Integer> scoreList"};
            Arguments args1 = new Arguments(arguments);
            assertEquals("(protected int nb, public String name, private ArrayList<Integer> scoreList)", args1.toString());
        }
        catch(Exception e){
            Assert.fail();
        }
    }
}
