package test;

import java.io.File;

import reader.CategoryReader;

public class TestCategoryReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader(new File("data" + File.separator + "quiz"));

        File[] folderList = reader.getFilesPath();

        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
