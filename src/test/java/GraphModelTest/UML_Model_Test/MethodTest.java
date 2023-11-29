package GraphModelTest.UML_Model_Test;

import org.junit.Test;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Method;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class MethodTest {
       // definition: ReturnType MethodName(Arguments) / public ReturnType MethodName(Arguments) / public static ReturnType MethodName(Arguments)

        @Test
        public void testDef1(){
            try {
                Method m1 = Method.getMethodFromDefinition("int countElements(Object[] tab)");
                assertEquals("countElements", m1.getName());
                assertEquals("int", m1.getType());
                assertEquals("(Object[] tab)", m1.getStringAttributes());
                assertEquals("int countElements(Object[] tab){\n\n\t}", m1.toString());
            } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
                Assert.fail();             
            }
        }

        @Test
        public void testDef2(){
            try {
                Method m2 = Method.getMethodFromDefinition("public String subString(int index1, int index2)");
                assertEquals("public", m2.getVisibility());
                assertEquals("subString", m2.getName());
                assertEquals("String", m2.getType());
                assertEquals("(int index1, int index2)", m2.getStringAttributes());//order depend of hash table 
                assertEquals("public String subString(int index1, int index2){\n\n\t}", m2.toString()); 
            } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
                Assert.fail();             
            }
        }

        @Test
        public void testMultiFail(){
            try {
                Method m3 = Method.getMethodFromDefinition("public String subString(int index1, int index1)");
               
            } catch (NoValidVisibilityException e) {
                Assert.fail();             
            } catch(AlreadyExistingStringException e){
                //System.out.println("no same name and type");
                //supposed to happen
            }
        }
}
