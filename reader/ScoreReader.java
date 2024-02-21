package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import quiz.User;

public class ScoreReader {

    public ScoreReader() { }

    public ArrayList<User> readFile(File path) throws FileNotFoundException {
        ArrayList<User> usersList = new ArrayList<>();

        Scanner input = new Scanner(path);

        input.nextLine();

        while (input.hasNextLine()) {
            String dataLine = input.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(dataLine.trim(), ",");

            String username = tokenizer.nextToken().trim();
            int score = Integer.parseInt(tokenizer.nextToken().trim());

            usersList.add(new User(username, score));
        }

        input.close();

        return usersList;
    }
}
