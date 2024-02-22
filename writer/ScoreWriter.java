package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import quiz.User;
import reader.ScoreReader;

public class ScoreWriter {
    private File path;

    public ScoreWriter(File path) {
        this.path = path;
    }

    public void newFile(File path, String name) throws IOException {
        File filePath = new File(path + File.separator + name + ".csv");

        if (filePath.createNewFile()) {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("Username, Score");
            buffer.close();
        } else {
            System.out.println("Error: File already exist");
        }
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

    public void update(String username, int score) throws IOException {
        File tempFile = new File("temparary.csv");

        ScoreReader scoreReader = new ScoreReader();
        ScoreWriter scoreWriter = new ScoreWriter(tempFile);

        ArrayList<User> usersList = scoreReader.readFile(this.path);
        ArrayList<User> tempUsersList = new ArrayList<>();
        FileWriter writer = new FileWriter(tempFile, true);
        BufferedWriter buffer = new BufferedWriter(writer);

        for (User user : usersList) {
            if (!(user.getName().equals(username))) {
                tempUsersList.add(user);
            } else {
                tempUsersList.add(new User(username, score));
            }
        }

        buffer.write("Username, Score");
        buffer.close();

        for (User user : tempUsersList) {
            scoreWriter.append(user.getName(), user.getScore());
        }

        this.path.delete();
        tempFile.renameTo(this.path);
    }
}
