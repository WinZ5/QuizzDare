package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import quiz.User;
import reader.ScoreReader;

public class TestScoreReader {
    public static void main(String[] args) throws FileNotFoundException {
        ScoreReader scoreReader = new ScoreReader();

        ArrayList<User> usersList = scoreReader.readFile(new File("test" + File.separator + "testleaderboard" + File.separator + "quiz.csv"));

        for (User user : usersList) {
            System.out.println(user.getName() + ", " + user.getScore());
        }
    }
}
