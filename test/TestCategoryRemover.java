package test;

import java.io.File;
import java.util.ArrayList;

import reader.CategoryReader;
import remover.CategoryRemover;

public class TestCategoryRemover {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        
        reader.readFolder(new File("test" + File.separator + "testdata"));

        CategoryRemover remover = new CategoryRemover();

        ArrayList<File> folderList = reader.getFiles();

        System.out.println("Before deletion: ");
        for (File file : folderList) {
            System.out.println(file);
        }

        remover.remove(folderList.get(1));

        System.out.println("After deletion: ");
        reader.clearFolderList();
        reader.readFolder(new File("test" + File.separator + "testdata"));
        folderList = reader.getFiles();
        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
