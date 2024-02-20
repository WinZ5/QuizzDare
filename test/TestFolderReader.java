package test;

import java.io.File;

import reader.FolderReader;

public class TestFolderReader {
    public static void main(String[] args) {
        FolderReader reader = new FolderReader(new File("data" + File.separator + "quiz"));

        File[] folderList = reader.getFilesPath();

        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
