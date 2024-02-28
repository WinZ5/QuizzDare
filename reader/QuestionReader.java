package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import quiz.Question;

public class QuestionReader {

    public QuestionReader() {
    };

    public ArrayList<Question> readFile(File path) throws FileNotFoundException {
        // Create new ArrayList to save Question.
        ArrayList<Question> questionsList = new ArrayList<>();

        Scanner input = new Scanner(path);

        input.nextLine();

        // Using StringTokenizer to read each line and create new Question object of
        // each question in file and it's to questionsList.
        while (input.hasNextLine()) {
            String dataLine = input.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(dataLine.trim(), ",");

            // Get the amount of all token
            int allTokens = tokenizer.countTokens();
            String question = tokenizer.nextToken().trim();
            ArrayList<String> choice = new ArrayList<>();

            // -1 from Question and another one so that the last token for answer doesn't get read,
            int remainToken = allTokens - 2;
            for (int i = 0; i < remainToken; i++) {
                choice.add(tokenizer.nextToken().trim());
            }
            char answer = tokenizer.nextToken().trim().charAt(0);

            questionsList.add(new Question(question, choice, answer));
        }

        input.close();

        return questionsList;
    }
}
