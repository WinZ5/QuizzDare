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

    /**
     * Method to save new score or update the existing score.
     * 
     * @param score
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void saveScore(int score) throws FileNotFoundException, IOException {
        ScoreWriter scoreWriter = new ScoreWriter(path);
        ArrayList<User> userList = scoreReader.readFile(path);
        boolean update = false;

        // Ask for username which can be anything.
        while (true) {
            input.nextLine();
            System.out.print("Enter your username (Up to 10 characters): ");
            String username = input.nextLine();

            // Only continue if username less than 10 characters which is maximum.
            if (username.length() <= 10) {
                for (User user : userList) {
                    if (user.getName().equals(username)) {
                        scoreWriter.update(username, score);
                        update = true;
                        break;
                    }
                }

                if (!update) {
                    scoreWriter.append(username, score);
                    System.out.println("Score saved.");
                } else {
                    System.out.println("Score update.");
                }
                break;
            } else {
                System.out.println("Error: Username exceed maximum.");
            }
        }
    }

    /**
     * Method to ask whether user want to save score or not.
     * 
     * @param score
     * @throws FileNotFoundException
     * @throws IOException
     */
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

    /**
     * Method to sort score from the given list from highest to smallest.
     * 
     * @param list
     */
    public static void scoreSorter(ArrayList<User> list) {
        // Using bubble sort.
        boolean swap = false;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getScore() < list.get(j + 1).getScore()) {
                    User temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swap = true;
                }
            }

            if (!swap) {
                break;
            }
        }
    }

    /**
     * Method to show score from the given file for a given amount.
     * 
     * @param target
     * @param amount
     * @throws FileNotFoundException
     */
    public static void showLeaderboard(File target, int amount) throws FileNotFoundException {
        path = target;
        // Read User data from the given path.
        ArrayList<User> leaderboard = scoreReader.readFile(path);
        // Sort score before showing leaderboard.
        scoreSorter(leaderboard);

        System.out.printf("%6s %s %n", " ", "Leaderboard");
        System.out.println();
        System.out.printf("%10s %10s%n", "Username", "Score");
        for (int i = 0; i < amount; i++) {
            if (i > leaderboard.size() - 1) {
                System.out.println("  No more score saved.");
                break;
            }
            System.out.printf("%10s %10d%n", leaderboard.get(i).getName(), leaderboard.get(i).getScore());
        }
    }

    /**
     * Method to ask user how many score they want to show a return that amount.
     * 
     * @return amount
     */
    public static int askAmount() {
        int amount;

        while (true) {
            System.out.print("How many users you want to show: ");
            try {
                amount = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.nextLine();
            }
        }

        Util.clear();
        return amount;
    }

    /**
     * Method to ask user for username and print score of that user from path in
     * attribute.
     * 
     * @throws FileNotFoundException
     */
    public static void searchScore() throws FileNotFoundException {
        ArrayList<User> userList = scoreReader.readFile(path);
        boolean complete = false;

        while (!complete) {
            System.out.print("Enter username (Case-Sensitive): ");
            String username = input.next();

            // If user not found print error message and ask for username again.
            for (User user : userList) {
                if (user.getName().equals(username)) {
                    Util.clear();
                    System.out.printf("%10s %10s%n", "Username", "Score");
                    System.out.printf("%10s %10d%n", user.getName(), user.getScore());
                    complete = true;
                }
            }

            if (!complete) {
                System.out.println("Error: User not found");
            }
        }
    }

    /**
     * Method to show menu after user have selected category.
     * 
     * @throws FileNotFoundException
     */
    public static void categoryMenu() throws FileNotFoundException {
        Util.clear();
        System.out.println("How do you want to view the score.");
        System.out.println();
        System.out.println("1. Show score");
        System.out.println("2. Search for user");
        System.out.println();
        while (true) {
            System.out.print("Choose: ");

            try {
                int operation = input.nextInt();

                if (operation == 1) {
                    int amount = askAmount();
                    showLeaderboard(path, amount);
                    break;
                } else if (operation == 2) {
                    searchScore();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.nextLine();
            }
        }
    }

    /**
     * Main method for leaderboard function.
     * 
     * @throws FileNotFoundException
     */
    public static void leaderboardMenu() throws FileNotFoundException {
        CategoryReader categoryReader = new CategoryReader();
        ArrayList<File> categoryList = categoryReader.readFolder(new File("data" + File.separator + "leaderboard"));

        Util.clear();
        // Ask user what category they want to see leaderboard of include randomquiz.
        System.out.println("Leaderboard");
        System.out.println();
        System.out.println("Which category you want to view.");
        System.out.println();
        for (int i = 1; i <= categoryList.size(); i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName().replaceFirst("[.][^.]+$", ""));
        }
        // Print RandomQuiz to the list afte all categoris have been printed.
        System.out.println((categoryList.size() + 1) + ": RandomQuiz");
        System.out.println();

        while (true) {
            System.out.print("Choose: ");

            try {
                int operation = input.nextInt();

                if (!(operation <= (categoryList.size() + 1)) && (operation > 0)) {
                    System.out.println("Error: Invalid Input");
                } else if (operation == (categoryList.size() + 1)) {
                    path = new File("data" + File.separator + "randomquiz" + File.separator + "score.csv");
                    categoryMenu();
                    break;
                } else {
                    path = categoryList.get(operation - 1);
                    categoryMenu();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.nextLine();
            }
        }
    }
}
