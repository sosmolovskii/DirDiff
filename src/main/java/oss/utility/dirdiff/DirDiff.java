package oss.utility.dirdiff;

import java.io.File;
import java.util.Set;

import static java.util.Objects.nonNull;

public class DirDiff {

    public static void compare(File init, File toCompare, File diff) {

        Set<File> initFiles =
                nonNull(init) && nonNull(init.listFiles())
                        ? Set.of(init.listFiles())
                        : Set.of();
        Set<File> filesToCompare =
                nonNull(toCompare) && nonNull(toCompare.listFiles())
                        ? Set.of(toCompare.listFiles())
                        : Set.of();

        if (initFiles.isEmpty()) {
            if (filesToCompare.isEmpty()) {
                return;
            }
        }

    }

}
