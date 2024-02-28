import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import core.Edit;
import core.Leaderboard;
import core.Quiz;
import core.Randomquiz;
import util.Util;

public class QuizDare {
    public static void inputMenu() throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Choose: ");
            int operation = input.nextInt();

            switch (operation) {
                case 1:
                    Quiz.startQuizMenu();
                    break;

                case 2:
                    Randomquiz.startRandomQuiz();
                    break;

                case 3:
                    Leaderboard.leaderboardMenu();
                    break;

                case 4:
                    Edit.startEdit();
                    break;

                default:
                    System.out.println("Error: Invalid Input");
                    inputMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid Input");
            inputMenu();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);

        while (true) {
            Util.clear();

            System.out.println("   ____        _     _____                 ");
            System.out.println("  / __ \\      (_)   |  __ \\                ");
            System.out.println(" | |  | |_   _ _ ___| |  | | __ _ _ __ ___ ");
            System.out.println(" | |  | | | | | |_  / |  | |/ _` | '__/ _ \\");
            System.out.println(" | |__| | |_| | |/ /| |__| | (_| | | |  __/");
            System.out.println("  \\___\\_\\\\__,_|_/___|_____/ \\__,_|_|  \\___|");
            // System.out.println("QuizDare");
            System.out.println("Welcome to QuizDare to begin input operation number (1 - 4)");
            System.out.println();
            System.out.println("1. Start Quiz");
            System.out.println("2. Random Quiz");
            System.out.println("3. Leaderboard");
            System.out.println("4. Edit Quiz");
            System.out.println();

            inputMenu();
            try {
                System.out.print("exit to quit the program or home to return to home: ");
                String user = input.next();

                if (user.toLowerCase().equals("exit")) {
                    break;
                } else if (user.toLowerCase().equals("home")) {
                    continue;
                } else {
                    System.out.println("Error: Invalid Input");
                }
                input.close();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }
}