import java.io.File;
import java.util.Scanner;

import quiz.Question;
import reader.FolderReader;
import reader.QuizReader;
import ui.Screen;

public class QuizDare {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FolderReader QuizFolder = new FolderReader(new File("data" + File.separator + "quiz"));

        File[] Categories = QuizFolder.getFilesPath();

        for (int i = 0; i < Categories.length; i++) {
            System.out.println(i + 1 + ": " + Categories[i].getName().replaceFirst("[.][^.]+$", ""));
        }

        System.out.print("Choose: ");
        int choice = input.nextInt();
        while (true) {
            if (choice <= Categories.length) {
                Screen.clear();
                QuizReader quiz = new QuizReader(Categories[choice - 1]);
                int score = 0;

                for (Question question : quiz.getQuestion()) {
                    System.out.println(question.getQuestion());

                    for (int i = 0; i < question.getChoices().size(); i++) {
                        char label = (char) ('a' + i);
                        System.out.printf("%s) %s%n", label, question.getChoices().get(i));
                    }

                    while (true) {
                        System.out.print("Answer: ");
                        String answer = input.next();

                        // 'a' + problem.getChoices().size() will return the ascii of the last choice's
                        // label + 1
                        if ((int) answer.charAt(0) < ('a' + question.getChoices().size())
                                && (int) answer.charAt(0) >= 97) {
                            if (question.checkAnswer(answer)) {
                                score++;
                                Screen.clear();
                                break;
                            } else {
                                Screen.clear();
                                break;
                            }
                        } else {
                            System.out.println("Error: Invalid Input");
                        }
                    }
                }
                Screen.clear();
                System.out.printf("You got %d/%d%n", score, quiz.getQuestion().size()); 
                break;
            } else {
                System.out.println("Error: Invalid choice");
            }
        }

        input.close();
    }
}