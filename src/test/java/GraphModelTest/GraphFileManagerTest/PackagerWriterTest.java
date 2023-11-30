package GraphModelTest.GraphFileManagerTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.GraphModel.GraphFileManager.PackageDirWriter;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;

public class PackagerWriterTest {

    // code copied from baeldung
    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
    @Test
    public void testPackageWrite(){
        PackageClass p1 = new PackageClass("packageTest");
        try {
            Classes c1 = new Classes("classeTest",new String[]{"int test", "String toto"});
            p1.addValueByKey(c1, "classeTest");
            PackageDirWriter pdw = new PackageDirWriter(p1);
            pdw.writePackage("./");
            File f1 = new File("./packageTest/classeTest.java");
            String content = ClassWriterTest.getContent(f1);
            assertEquals(c1.toString(), content);
        } catch (AlreadyExistingStringException | NoValidVisibilityException | IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
