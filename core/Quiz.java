package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;
import util.Util;

public class Quiz {
    private static Scanner input = new Scanner(System.in);
    private static File category;
    private static int problemIndex;
    private static char[] userAnswer;
    private static char[] correctAnswer;

    /**
     * Method to print informtaion the given funtion.
     * 
     * @param question
     */
    public static void printQuestion(Question question) {
        Util.clear();
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getChoices().size(); i++) {
            char label = (char) ('a' + i);
            System.out.printf("%s) %s%n", label, question.getChoices().get(i));
        }
    }

    /**
     * Method use to navigate question in quiz.
     * 
     * @param questionList
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void navigateQuiz(ArrayList<Question> questionList) throws FileNotFoundException, IOException {
        Question question = questionList.get(problemIndex);

        printQuestion(question);

        // Print navigation instruction.
        try {
            if (problemIndex == 0) {
                System.out.println("You can answer \"next\" to go to next question.");
            }

            if (!(problemIndex == (questionList.size() - 1)) && !(problemIndex == 0)) {
                System.out.println(
                        "You can answer \"prev\" to go to previous question or use \"next\" to go to next question.");
            }

            if (problemIndex == (questionList.size() - 1)) {
                System.out.println("You can answer \"prev\" to go to previous question.");
            }

            answerQuestion(questionList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: This category has no question.");
        }
    }

    /**
     * Method to that review useranswer and save it's in userAnswer.
     * 
     * @param questionList
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void answerQuestion(ArrayList<Question> questionList)
            throws FileNotFoundException, IOException {
        while (true) {
            System.out.println();
            System.out.print("Answer: ");
            String answer = input.next();

            if (answer.toLowerCase().equals("prev")) {
                if (problemIndex == 0) {
                    System.out.println("Error: This is the first question");
                } else {
                    problemIndex--;
                    navigateQuiz(questionList);
                }
            } else if (answer.toLowerCase().equals("next")) {
                if (!(problemIndex == (questionList.size() - 1))) {
                    problemIndex++;
                    navigateQuiz(questionList);
                }
            } else {
                // Black Magic use to determine wheter user answer is valid.
                // Don't let European during 1560–1630 see this I don't want to get brun alive.
                if ((int) answer.charAt(0) < ('a' + questionList.get(problemIndex).getChoices().size())
                        && (int) answer.charAt(0) >= 97) {
                    userAnswer[problemIndex] = answer.charAt(0);

                    if (problemIndex < (questionList.size() - 1)) {
                        problemIndex++;
                        navigateQuiz(questionList);
                        break;
                    } else {
                        endQuiz(questionList);
                        break;
                    }
                } else {
                    System.out.println("Error: Invalid answer");
                }
            }
        }
    }

    /**
     * Method to end the quiz and print user score with the leaderboard.
     * 
     * @param questionList
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void endQuiz(ArrayList<Question> questionList) throws FileNotFoundException, IOException {
        int score = checkAnswer(questionList);
        int maxscore = questionList.size();

        Util.clear();
        System.out.printf("%6s Score: %d / %d%n", " ", score, maxscore);
        System.out.println();
        Leaderboard.showLeaderboard(
                new File("data" + File.separator + "leaderboard" + File.separator + category.getName()), 5);
        System.out.println();
        Leaderboard.saveConsent(score);
    }

    /**
     * Method to check userAnser and return user score.
     * 
     * @param questionList
     * @return score
     */
    public static int checkAnswer(ArrayList<Question> questionList) {
        int score = 0;
        int maxscore = questionList.size();

        for (int i = 0; i < maxscore; i++) {
            if (userAnswer[i] == correctAnswer[i]) {
                score++;
            }
        }

        return score;
    }

    /**
     * Method to start quiz.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void startQuiz() throws FileNotFoundException, IOException {
        QuestionReader questionReader = new QuestionReader();
        ArrayList<Question> questionList = questionReader.readFile(category);
        userAnswer = new char[questionList.size()];
        correctAnswer = new char[questionList.size()];

        // Save the correct answer into correctAnswer
        for (int i = 0; i < questionList.size(); i++) {
            correctAnswer[i] = questionList.get(i).getAnswer();
        }

        problemIndex = 0;

        navigateQuiz(questionList);
    }

    /**
     * Main method of Quiz feature.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void startQuizMenu() throws FileNotFoundException, IOException {
        CategoryReader categoryReader = new CategoryReader();
        ArrayList<File> categoryList = categoryReader.readFolder(new File("data" + File.separator + "quiz"));

        Util.clear();
        // Print all categories aviable.
        System.out.println("Choose category of your quiz.");
        System.out.println();
        for (int i = 1; i <= categoryList.size(); i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName().replaceFirst("[.][^.]+$", ""));
        }
        System.out.println();

        while (true) {
            System.out.print("Choose: ");

            try {

                int operation = input.nextInt();

                if (!(operation <= categoryList.size()) && (operation > 0)) {
                    System.out.println("Error: Invalid Input");
                } else {
                    category = categoryList.get(operation - 1);
                    startQuiz();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.next();
            }
        }
    }
}