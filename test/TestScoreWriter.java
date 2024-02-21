package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import reader.CategoryReader;
import writer.ScoreWriter;

public class TestScoreWriter {
    public static void main(String[] args) throws IOException {
        CategoryReader categoryReader = new CategoryReader();
        File path = new File("test" + File.separator + "testdata");
        ArrayList<File> folderList = categoryReader.readFolder(path);

        File target = new File("test" + File.separator + "testleaderboard" + File.separator + folderList.get(0).getName());
        
        ScoreWriter scoreWriter = new ScoreWriter(target);

        scoreWriter.append("Jame", 5);
        System.out.println(target);
    }
}
