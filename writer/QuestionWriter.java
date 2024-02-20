package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionWriter {
    private File path;

    public QuestionWriter(File path) {
        this.path = path;
    }

    public void append(String question, ArrayList<String> choices, char answer) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        BufferedWriter buffer = new BufferedWriter(writer);
        
        buffer.newLine();
        buffer.write(question + ", ");
        for (String choice : choices) {
            buffer.write(choice + ", ");
        }
        buffer.write(answer);
        buffer.close();
    }
}
