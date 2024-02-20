package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import writer.QuestionWriter;

public class TestQuestionWriter {
    public static void main(String[] args) throws IOException {
        QuestionWriter writer = new QuestionWriter(new File("test" + File.separator + "testdata" + File.separator + "quiz.csv"));
        ArrayList<String> choices = new ArrayList<>();
        choices.add("hello");
        choices.add("test");
        choices.add("world");

        writer.append("Test", choices, 'a');
    }
}
