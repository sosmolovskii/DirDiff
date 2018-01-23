package oss.utility.dirdiff;

import java.io.File;
import java.util.Set;

public class DirDiff {

    public static void compare(File init, File toCompare, File diff) {

        Set<File> initFiles = (init.listFiles() != null) ? Set.of(init.listFiles()) : Set.of();
        Set<File> filesToCompare = (toCompare.listFiles() != null) ? Set.of(toCompare.listFiles()) : Set.of();

        if (initFiles.isEmpty()) {
            if (filesToCompare.isEmpty()) {
                return;
            }
        }

    }

}
