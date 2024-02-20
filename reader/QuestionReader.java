package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import quiz.Question;

public class QuestionReader {
    private ArrayList<Question> QuestionsList = new ArrayList<>();

    public QuestionReader() {};

    public void readFolder(File path) {
        File f = new File(path.toString());

        try (Scanner input = new Scanner(f)) {
            input.nextLine();

            while (input.hasNextLine()) {
                String dataLine = input.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(dataLine.trim(), ",");

                String question = tokenizer.nextToken().trim();
                ArrayList<String> choice = new ArrayList<>();

                for (int i = 0; i <= tokenizer.countTokens() + 1; i++) {
                    choice.add(tokenizer.nextToken().trim());
                }
                char answer = tokenizer.nextToken().trim().charAt(0);

                this.QuestionsList.add(new Question(question, choice, answer));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

    public ArrayList<Question> getQuestion() {
        return this.QuestionsList;
    }
}
