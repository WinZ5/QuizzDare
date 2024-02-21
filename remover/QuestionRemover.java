package remover;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import quiz.Question;
import reader.QuestionReader;
import writer.QuestionWriter;

public class QuestionRemover {
    public QuestionRemover() { }

    public void remove(File targetCategory, Question targetQuestion) throws IOException {
        File tempFile = new File("temporary.csv");

        QuestionReader questionReader = new QuestionReader();
        QuestionWriter questionWriter = new QuestionWriter(tempFile);

        ArrayList<Question> questionList = questionReader.readFile(targetCategory);
        ArrayList<Question> tempQuestionList = new ArrayList<>();
        FileWriter writer = new FileWriter(tempFile, true);
        BufferedWriter buffer = new BufferedWriter(writer);

        for (Question question : questionList) {
            if (!(question.getQuestion().equals(targetQuestion.getQuestion()))) {
                tempQuestionList.add(question);
            }
        }

        buffer.write("Question, a, b, c, d, answer");
        buffer.close();
        
        for (Question question : tempQuestionList) {
            questionWriter.append(question.getQuestion(), question.getChoices(), question.getAnswer());
        } 

        targetCategory.delete();
        tempFile.renameTo(targetCategory);
    }
}
