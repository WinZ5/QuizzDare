package remover;

import java.io.File;

public class CategoryRemover {
    public CategoryRemover() { }

    // Delete given file.
    public void remove(File path) {
        if (path.delete()) {
            System.out.println("Category Deleted.");
        }
    }
}
