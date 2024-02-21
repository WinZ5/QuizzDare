package test;

import java.io.File;
import java.util.ArrayList;

import reader.CategoryReader;
import remover.CategoryRemover;

public class TestCategoryRemover {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        CategoryRemover remover = new CategoryRemover();

        ArrayList<File> folderList = reader.readFolder(new File("test" + File.separator + "testdata"));
        System.out.println("Before deletion: ");
        for (File file : folderList) {
            System.out.println(file);
        }

        remover.remove(folderList.get(1));

        System.out.println("After deletion: ");
        folderList = reader.readFolder(new File("test" + File.separator + "testdata"));
        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
