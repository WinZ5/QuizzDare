package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import quiz.Question;
import reader.CategoryReader;
import reader.QuestionReader;
import util.Util;

public class Quiz {
    private static Scanner input = new Scanner(System.in);
    private static int problemIndex;
    private static char[] userAnswer;
    private static char[] correctAnswer;

    public static void printQuestion(Question question) {
        Util.clear();
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getChoices().size(); i++) {
            char label = (char) ('a' + i);
            System.out.printf("%s) %s%n", label, question.getChoices().get(i));
        }
    }

    public static void navigateQuiz(ArrayList<Question> questionList) {
        Question question = questionList.get(problemIndex);

        printQuestion(question);

        if (problemIndex == 0) {
            System.out.println("You can answer \"next\" to go to next question.");
        }

        if (!(problemIndex == (questionList.size() - 1)) && !(problemIndex == 0)) {
            System.out.println("You can answer \"prev\" to go to previous question or use \"next\" to go to next question.");
        }

        if (problemIndex == (questionList.size() - 1)) {
            System.out.println("You can answer \"prev\" to go to previous question.");
        }

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
            answerQuestion(questionList, answer);
        }
    }

    
    public static void answerQuestion(ArrayList<Question> questionList, String answer) {
        while (true) {
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
    
    public static void endQuiz(ArrayList<Question> questionList) {
        int score = checkAnswer(questionList);
        int maxscore = questionList.size();

        Util.clear();
        System.out.println("Score: " + score + " / " + maxscore);
    }

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

    public static void startQuiz(File category) throws FileNotFoundException {
        QuestionReader questionReader = new QuestionReader();
        ArrayList<Question> questionslist = questionReader.readFile(category);
        userAnswer = new char[questionslist.size()];
        correctAnswer = new char[questionslist.size()];

        for (int i = 0; i < questionslist.size(); i++) {
            correctAnswer[i] = questionslist.get(i).getAnswer();
        }

        problemIndex = 0;

        navigateQuiz(questionslist);
    }

    public static void startQuizMenu() throws FileNotFoundException {
        CategoryReader categoryReader = new CategoryReader();
        ArrayList<File> categoryList = categoryReader.readFolder(new File("data" + File.separator + "quiz"));

        Util.clear();
        System.out.println("Category");
        System.out.println();
        for (int i = 1; i <= categoryList.size(); i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName().replaceFirst("[.][^.]+$", ""));
        }
        System.out.println();
        System.out.print("Choose: ");

        while (true) {
            try {
                int operation = input.nextInt();

                if (!(operation <= (categoryList.size() - 1))) {
                    System.out.println("Error: Invalid input");
                    System.out.print("Choose: ");
                    continue;
                } else {
                    startQuiz((categoryList.get(operation - 1)));
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input");
                System.out.print("Choose: ");
            }
        }
    }
}