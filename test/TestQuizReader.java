package test;

import java.io.File;

import quiz.Question;
import reader.FolderReader;
import reader.QuizReader;

public class TestQuizReader {
    public static void main(String[] args) {
        FolderReader reader = new FolderReader(new File("data" + File.separator + "quiz"));
        QuizReader quiz = new QuizReader();
        
        File[] folderList = reader .getFilesPath();

        for (File folder : folderList) {
            quiz.readFolder(folder);
        }

        for (Question question : quiz.getQuestion()) {
            System.out.println(question.getQuestion() + question.getChoices() + question.getAnswer());
        }
    }
}
