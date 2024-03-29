package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CategoryWriter {
    private File path;

    public CategoryWriter(File path) {
        this.path = path;
    }

    // Method to create category file with given name.
    public boolean createCategory(String name) throws IOException {
        File filePath = new File(path + File.separator + name + ".csv");

        if (filePath.createNewFile()) {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("Question, a, b, c, d, answer");
            buffer.close();
            System.out.println("New category added.");
            return true;
        } else {
            System.out.println("Error: Category alredy exist");
            return false;
        } 
    }
}
