package GraphModelTest.UML_Model_Test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.example.GraphModel.UML_Model.Arguments;

import org.junit.Assert;

public class ArgumentTest {
    @Test
    public void multiArgumentTest(){
        try{
            String[] arguments = new String[] {"P int nb", "+ String name", "- ArrayList<Integer> scoreList"};
            Arguments args1 = new Arguments(arguments);
            assertEquals("(private ArrayList<Integer> scoreList, protected int nb, public String name)", args1.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void zeroArgumentTest(){
        try{
            String[] arguments = new String[]{};
            Arguments args1 = new Arguments(arguments);
            assertEquals("()", args1.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
