package test;

import java.io.File;
import java.util.ArrayList;

import reader.CategoryReader;

public class TestCategoryReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        
        ArrayList<File> folderList = reader.readFolder(new File("test" + File.separator + "testdata"));

        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
