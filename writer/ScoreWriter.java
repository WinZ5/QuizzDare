package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreWriter {
    private File path;

    public ScoreWriter(File path) {
        this.path = path;
    }

    public void append(String username, int score) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.newLine();
        buffer.write(username + ", ");
        // Without "" to change score from int to String score become error character when append
        buffer.write(score + "");
        buffer.close();
    }
}
