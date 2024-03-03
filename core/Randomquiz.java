package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;
import util.Util;

public class Randomquiz {
    private static CategoryReader categoryReader = new CategoryReader();
    private static QuestionReader questionReader = new QuestionReader();
    private static Scanner input = new Scanner(System.in);

    private static File folderPath = new File("data" + File.separator + "quiz");
    private static ArrayList<File> categoryList = categoryReader.readFolder(folderPath);
    private static ArrayList<Question> questionList = new ArrayList<>();
    private static boolean complete;
    private static int score = 0;

    /**
     * Method to answer question.
     * 
     * @param question
     * @param answer
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void answerQuestion(Question question)
            throws FileNotFoundException, IOException {

        while (true) {
            System.out.print("Answer: ");
            String answer = input.next();

            if ((int) answer.charAt(0) < ('a' + question.getChoices().size())
                    && (int) answer.charAt(0) >= 97) {
                // If user answer is correct score++ if user answer wrong invoke endRandomQuiz.
                if (answer.charAt(0) == question.getAnswer()) {
                    score++;
                    break;
                } else {
                    endRandomQuiz();
                    break;
                }
            } else {
                System.out.println("Error: Invalid answer");
                input.nextLine();
            }
        }
    }

    /**
     * Method to end quiz and print the leaderborad.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void endRandomQuiz() throws FileNotFoundException, IOException {
        Util.clear();
        System.out.println("Score: " + score);
        System.out.println();
        Leaderboard.showLeaderboard(new File("data" + File.separator + "randomquiz" + File.separator + "score.csv"),
                score);
        System.out.println();
        Leaderboard.saveConsent(score);
        complete = true;
    }

    /**
     * Main method for Random Quiz feature.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void startRandomQuiz() throws FileNotFoundException, IOException {
        // Read question from all category and save into questionList
        for (File category : categoryList) {
            ArrayList<Question> questions = questionReader.readFile(category);
            for (Question question : questions) {
                questionList.add(question);
            }
        }
        complete = false;
        score = 0;

        // Suffle questionList
        Util.shuffle(questionList);

        while (!complete) {
            for (Question question : questionList) {
                if (complete) {
                    break;
                }

                Util.clear();
                System.out.println(question.getQuestion());
                System.out.println();
                for (int i = 0; i < question.getChoices().size(); i++) {
                    char label = (char) ('a' + i);
                    System.out.printf("%s) %s%n", label, question.getChoices().get(i));
                }
                System.out.println();

                answerQuestion(question);
            }
        }
    }
}
