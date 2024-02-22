package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import quiz.User;
import reader.CategoryReader;
import reader.ScoreReader;
import writer.ScoreWriter;

public class TestScoreWriter {
    public static void main(String[] args) throws IOException {
        CategoryReader categoryReader = new CategoryReader();
        File path = new File("test" + File.separator + "testdata");
        ArrayList<File> folderList = categoryReader.readFolder(path);

        File target = new File("test" + File.separator + "testleaderboard" + File.separator + folderList.get(0).getName());
        
        ScoreWriter scoreWriter = new ScoreWriter(target);
        ScoreReader scoreReader = new ScoreReader();

        ArrayList<User> usersList =  scoreReader.readFile(target);

        scoreWriter.newFile(new File("test" + File.separator + "testleaderboard" + File.separator), "test");  

        System.out.println("Before append: ");
        for (User user : usersList) {
            System.out.println(user.getName() + ", " + user.getScore());
        }
        scoreWriter.append("Jame", 5);
        usersList = scoreReader.readFile(target);
        System.out.println("After append: ");
        for (User user : usersList) {
            System.out.println(user.getName() + ", " + user.getScore());
        }
        scoreWriter.update(usersList.get(0).getName(), 0);
        usersList = scoreReader.readFile(target);
        System.out.println("After update: ");
        for (User user : usersList) {
            System.out.println(user.getName() + ", " + user.getScore());
        }
    }
}
