package test;

import java.io.File;

import reader.CategoryReader;
import remover.CategoryRemover;

public class TestCategoryRemover {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader(new File("test" + File.separator + "testdata"));
        CategoryRemover remover = new CategoryRemover();

        File[] folderList = reader.getFilesPath();

        System.out.println("Before deletion: ");
        for (File file : folderList) {
            System.out.println(file);
        }

        remover.remove(folderList[1]);

        System.out.println("After deletion: ");
        folderList = reader.getFilesPath();
        for (File file : folderList) {
            System.out.println(file);
        }
    }
}
