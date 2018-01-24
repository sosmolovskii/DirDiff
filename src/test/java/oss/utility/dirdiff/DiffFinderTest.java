package oss.utility.dirdiff;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@RunWith(Theories.class)
public class DiffFinderTest extends Assert {

    @Rule
    public TemporaryFolder initialFolder = new TemporaryFolder(new File("./out/test"));

    @Rule
    public TemporaryFolder folderForCompare = new TemporaryFolder(new File("./out/test"));

    @Rule
    public TemporaryFolder folderForDiff = new TemporaryFolder(new File("./out/test"));

    @DataPoints
    public static List<List<List<String>>> testDirContent() {

        return List.of(
                List.of(List.of(), List.of(), List.of()),
                List.of(List.of("1"), List.of(), List.of("1"))

        );
    }


    @Theory
    public void test(List<List<String>> dirsContent) {

        prepareDirs(dirsContent);

        DirDiff.compare(initialFolder.getRoot(), folderForCompare.getRoot(), folderForDiff.getRoot());

        checkDiff(dirsContent.get(2));

    }

    private void prepareDirs(List<List<String>> dirsContent) {
        createFiles(initialFolder, dirsContent.get(0));
        createFiles(folderForCompare, dirsContent.get(1));
    }

    private void checkDiff(List<String> diffFileNames) {

        Set<String> files = new HashSet<>();
        if (nonNull(folderForDiff) && nonNull(folderForDiff.getRoot()) && nonNull(folderForDiff.getRoot().list())) {
            files.addAll(Set.of(folderForDiff.getRoot().list()));
        }

        diffFileNames.forEach((String fileName) -> {
            assertTrue("There are not file:" + fileName + " in diffDir", files.contains(fileName));
            files.remove(fileName);
        });

        assertTrue("There are explicit diff files: " + files.toString(), files.isEmpty());
    }

    private void createFiles(TemporaryFolder tempFolder, List<String> dirsContent) {
        dirsContent.forEach(fileName -> {
            try {
                tempFolder.newFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
