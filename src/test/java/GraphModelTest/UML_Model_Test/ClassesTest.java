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
            Assert.assertEquals("public class classe1{\n\tint valeur1;\n\tString nom;\n\tpublic void addMethod(){\n\n\t}}", classe1.toString());
        } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSameArgsClass(){
        String[] attr = new String[]{"int valeur1","int valeur1"};
        try {
            Classes classe1 = new Classes("classe1", attr);
            System.out.println(classe1.toString());
        } catch (NoValidVisibilityException e) {
            e.printStackTrace();
            Assert.fail();
        } catch(AlreadyExistingStringException e){
            //System.out.println("Test successfully passed");
            // supposed to happened
        }
    }

    @Test
    public void testSameMethodsClass(){
        String[] attr = new String[]{};
        try {
            Classes classe1 = new Classes("classe1", attr);
            classe1.addMethod("public void addMethod()");
            classe1.addMethod("public void addMethod()");
            System.out.println(classe1.toString());
        } catch (NoValidVisibilityException e) {
            e.printStackTrace();
            Assert.fail();
        } catch(AlreadyExistingStringException e){
            //System.out.println("Test successfully passed");
            // supposed to happened
        }
    }

    @Test
    public void testSameMethodsButWithDifferentArgumentsClass(){
        String[] attr = new String[]{};
        try {
            Classes classe1 = new Classes("classe1", attr);
            classe1.addMethod("public Stuff createStuff(int a, int b)");
            classe1.addMethod("public Stuff createStuff(String toto, String tata)");
            //System.out.println(classe1.toString());
        } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSameMethodsButWithSameArgumentsClass(){
        String[] attr = new String[]{};
        try {
            Classes classe1 = new Classes("classe1", attr);
            classe1.addMethod("public Stuff createStuff(int a, int b)");
            classe1.addMethod("public Stuff createStuff(int a, int b)");
            //System.out.println(classe1.toString());
        } catch (NoValidVisibilityException e) {
            e.printStackTrace();
            Assert.fail();
        } catch( AlreadyExistingStringException e){
            //System.out.println("Test successfully passed");
            // supposed to happened
        }
    }
}
