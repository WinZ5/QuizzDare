package test;

import java.io.File;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;

public class TestQuestionReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader(new File("data" + File.separator + "quiz"));
        QuestionReader quiz = new QuestionReader();
        
        File[] folderList = reader .getFilesPath();

        for (File folder : folderList) {
            quiz.readFolder(folder);
        }

        for (Question question : quiz.getQuestion()) {
            System.out.println(question.getQuestion() + question.getChoices() + question.getAnswer());
        }
    }
}
