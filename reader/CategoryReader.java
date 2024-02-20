package reader;

import java.io.File;

public class CategoryReader {
    private File directory;

    public CategoryReader(File directory) {
        this.directory = directory;
    }

    public File[] getFilesPath() {
        // Create new array with path of all files in the directory
        File[] filesPath = this.directory.listFiles();
        // Return the list of path of all files
        return filesPath;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
