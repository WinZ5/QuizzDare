package reader;

import java.io.File;
import java.util.ArrayList;

public class CategoryReader {
    private ArrayList<File> FolderList = new ArrayList<>();

    public CategoryReader() { }

    public void readFolder(File directory) {
        // Create new array with path of all files in the directory
        File[] filesPath = directory.listFiles();

        for (File file : filesPath) {
            FolderList.add(file);
        }
    }

    public void clearFolderList() {
        FolderList.clear();
    }

    public ArrayList<File> getFiles() {
        return FolderList;
    }
}
