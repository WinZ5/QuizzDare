import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import core.Quiz;
import util.Util;

public class QuizDare {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

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
        System.out.print("Choose: ");

        try {
            int operation = input.nextInt();

            switch (operation) {
                case 1:
                    Quiz.startQuizMenu();
                    break;

                case 2:
                    System.out.println("Random Quiz");
                    break;

                case 3:
                    System.out.println("Leaderboard");
                    break;

                case 4:
                    System.out.println("Edit Quiz");
                    break;
            
                default:
                    System.out.println("Error: Invalid Input");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid Input");
        }

        input.close();
    }
}