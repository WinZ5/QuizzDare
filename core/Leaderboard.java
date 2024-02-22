package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import quiz.User;
import reader.CategoryReader;
import reader.ScoreReader;
import util.Util;
import writer.ScoreWriter;

public class Leaderboard {
    private static Scanner input = new Scanner(System.in);
    private static ScoreReader scoreReader = new ScoreReader();
    private static File path;

    public static void saveScore(int score) throws FileNotFoundException, IOException {
        ScoreWriter scoreWriter = new ScoreWriter(path);
        ArrayList<User> userList = scoreReader.readFile(path);

        Util.clear();
        System.out.print("Enter your username (Up to 10 characters): ");
        String username = input.next();

        while (true) {
            for (User user : userList) {
                if (user.getName().equals(username)) {
                    scoreWriter.update(username, score);
                    break;
                }
                break;
            }

            scoreWriter.append(username, score);
            break;
        }
    }

    public static void saveConsent(int score) throws FileNotFoundException, IOException {
        while (true) {
            System.out.print("Save your score? (yes/no): ");
            String consent = input.next();

            if (consent.toLowerCase().equals("yes") || consent.toLowerCase().equals("y")) {
                saveScore(score);
                break;
            } else if (!(consent.toLowerCase().equals("no") || consent.toLowerCase().equals("n"))) {
                System.out.println("Error: Invalid Input");
            } else {
                break;
            }
        }
    }

    public static void showLeaderboard(File target, int amount) throws FileNotFoundException {
        path = target;
        ArrayList<User> leaderboard = scoreReader.readFile(path);
        Util.scoreSorter(leaderboard);

        System.out.printf("%6s %s %n", " ", "Leaderboard");
        System.out.printf("%10s %10s%n", "Username", "Score");
        for (int i = 0; i < amount; i++) {
            if (i > leaderboard.size() - 1) {
                System.out.println("  No more score saved.");
                break;
            }
            System.out.printf("%10s %10d%n", leaderboard.get(i).getName(), leaderboard.get(i).getScore());
        }
    }

    public static int askAmount() {
        int amount;

        Util.clear();
        while (true) {
            System.out.print("How many users you want to show: ");
            try {
                amount = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }

        Util.clear();
        return amount;
    }

    public static void scoreByCategory() throws FileNotFoundException {
        CategoryReader categoryReader = new CategoryReader();
        ArrayList<File> categoryList = categoryReader.readFolder(new File("data" + File.separator + "leaderboard"));

        Util.clear();
        System.out.println("Category");
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
                    int amount = askAmount();
                    showLeaderboard(categoryList.get(operation - 1), amount);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }

    public static void leaderboardInputMenu() throws FileNotFoundException {
        try {
            System.out.print("Choose: ");
            int operation = input.nextInt();

            switch (operation) {
                case 1:
                    scoreByCategory();
                    break;

                case 2:
                    System.out.println("seacrh");
                    break;

                default:
                    System.out.println("Error: Invalid Input");
                    leaderboardInputMenu();
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid Input");
            leaderboardInputMenu();
        }
    }

    public static void leaderboardMenu() throws FileNotFoundException {
        Util.clear();
        System.out.println("Leaderboard");
        System.out.println();
        System.out.println("1. View score by category.");
        System.out.println("2. Search for user.");
        System.out.println();
        leaderboardInputMenu();
    }
}
