package reader;

import java.io.File;
import java.util.ArrayList;

public class CategoryReader {

    public CategoryReader() { }

    public ArrayList<File> readFolder(File directory) {
        // Create new array with path of all files in the directory
        File[] filesPath = directory.listFiles();

        ArrayList<File> FolderList = new ArrayList<>();

        for (File file : filesPath) {
            FolderList.add(file);
        }

        return FolderList;
    }
}
