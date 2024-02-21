package test;

import java.io.File;
import java.util.ArrayList;

import reader.CategoryReader;

public class TestCategoryReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        
        reader.readFolder(new File("test" + File.separator + "testdata"));
        ArrayList<File> folderList = reader.getFiles();

        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
