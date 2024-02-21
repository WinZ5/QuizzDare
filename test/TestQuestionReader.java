package test;

import java.io.File;
import java.util.ArrayList;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;

public class TestQuestionReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        reader.readFolder(new File("data" + File.separator + "quiz"));
        QuestionReader quiz = new QuestionReader();
        
        ArrayList<File> folderList = reader.getFiles();

        for (File folder : folderList) {
            quiz.readFolder(folder);
        }

        for (Question question : quiz.getQuestion()) {
            System.out.println(question.getQuestion() + question.getChoices() + question.getAnswer());
        }
    }
}
