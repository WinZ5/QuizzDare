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

    public void append(String username, int score) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.newLine();
        buffer.write(username + ", ");
        // Without "" to change score from int to String score become error character when append
        buffer.write(score + "");
        buffer.close();
    }

    public void update(User targetuser, int score) throws IOException {
        File tempFile = new File("temparary.csv");

        ScoreReader scoreReader = new ScoreReader();
        ScoreWriter scoreWriter = new ScoreWriter(tempFile);

        ArrayList<User> usersList = scoreReader.readFile(this.path);
        ArrayList<User> tempUsersList = new ArrayList<>();
        FileWriter writer = new FileWriter(tempFile, true);
        BufferedWriter buffer = new BufferedWriter(writer);

        for (User user : usersList) {
            if (!(user.getName().equals(targetuser.getName()))) {
                tempUsersList.add(user);
            } else {
                tempUsersList.add(new User(targetuser.getName(), score));
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
