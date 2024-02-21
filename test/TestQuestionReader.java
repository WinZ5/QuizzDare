package test;

import java.io.File;
import java.util.ArrayList;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;

public class TestQuestionReader {
    public static void main(String[] args) {
        CategoryReader reader = new CategoryReader();
        QuestionReader quizreader = new QuestionReader();
        
        ArrayList<File> folderList = reader.readFolder(new File("data" + File.separator + "quiz"));
        ArrayList<Question> questionsList = new ArrayList<>();

        for (File folder : folderList) {
            for (Question question : quizreader.readFile(folder)) {
                questionsList.add(question);
            }
        }

        for (Question question : questionsList) {
            System.out.println(question.getQuestion() + ", " + question.getChoices() + ", " + question.getAnswer());
        }
    }
}
