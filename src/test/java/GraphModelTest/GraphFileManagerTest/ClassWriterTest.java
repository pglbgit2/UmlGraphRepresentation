package GraphModelTest.GraphFileManagerTest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.example.GraphModel.GraphFileManager.ClassWriter;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

public class ClassWriterTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
 
    public static String getContent(File tempFile) throws IOException{
        FileReader fr1 = new FileReader(tempFile);
        BufferedReader bf = new BufferedReader(fr1);
        String content = "";
        String line = "";
        while(true){
            line = bf.readLine();
            if(line == null){
                break;
            }
            content += line+"\n";
        }
        content = content.substring(0,content.length()-1);
        bf.close();
        return content;
    }

    @Test
    public void testWriteClassInTempFolder() throws IOException {
        File tempFile = testFolder.newFile("test.java");
        //File tempFolder = testFolder.newFolder("TempDir");
        try {
            Classes c1 = new Classes("test", new String[]{"int var1","int var2"});
            c1.addMethod("public int testMethod(int toto, String name)");
            ClassWriter cw = new ClassWriter(c1);
            cw.writeFileWithFile(tempFile);
            String content = getContent(tempFile);
            assertEquals(c1.toString(), content);
            tempFile.delete();
        } catch (NoValidVisibilityException | AlreadyExistingStringException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
