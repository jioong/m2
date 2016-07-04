package com.github.jioong.java8.part01.chap01;

import java.io.File;
import java.io.FileFilter;

public class ListFile {

    public File[] getHiddenFiles() {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        return hiddenFiles;
    }

    public File[] getHiddenFilesLambda() {
        File[] hiddenFiles = new File(".").listFiles(pathname -> {
            return pathname.isHidden();
        });

        return hiddenFiles;
    }

    public File[] getHiddenFilesMethodRef() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        return hiddenFiles;
    }
}
