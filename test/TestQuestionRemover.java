package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;
import remover.QuestionRemover;

public class TestQuestionRemover {
    public static void main(String[] args) throws IOException {
        CategoryReader categoryReader = new CategoryReader();
        QuestionReader questionReader = new QuestionReader();
        QuestionRemover questionRemover = new QuestionRemover();

        ArrayList<File> folderList = categoryReader.readFolder(new File("test" + File.separator + "testdata"));
        ArrayList<Question> questionList = questionReader.readFile(folderList.get(0));

        System.out.println("Before remove: ");
        for (Question question : questionList) {
            System.out.println(question.getQuestion() + ", " + question.getChoices() + ", " + question.getAnswer());
        }

        questionRemover.remove(folderList.get(0), questionList.get(2));

        System.out.println("After remove: ");
        for (Question question : questionReader.readFile(folderList.get(0))) {
            System.out.println(question.getQuestion() + ", " + question.getChoices() + ", " + question.getAnswer());
        }
    }
}
