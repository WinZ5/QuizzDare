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
import remover.CategoryRemover;
import remover.QuestionRemover;
import util.Util;
import writer.CategoryWriter;
import writer.QuestionWriter;
import writer.ScoreWriter;

public class Edit {
    private static File folderPath = new File("data" + File.separator + "quiz");
    private static File scorePath = new File("data" + File.separator + "leaderboard");

    private static CategoryReader categoryReader = new CategoryReader();
    private static QuestionReader questionReader = new QuestionReader();
    private static CategoryWriter categoryWriter = new CategoryWriter(folderPath);
    private static ScoreWriter scoreWriter = new ScoreWriter(scorePath);
    private static CategoryRemover categoryRemover = new CategoryRemover();
    private static QuestionRemover questionRemover = new QuestionRemover();
    private static Scanner input = new Scanner(System.in);

    private static ArrayList<File> categoryList = categoryReader.readFolder(folderPath);
    private static File category;

    public static void newCategory() throws IOException {
        System.out.print("Category name: ");
        String name = input.next();

        category = new File("data" + File.separator + "quiz" + File.separator + name + ".csv");

        if (categoryWriter.createCategory(name)) {
            scoreWriter.newFile(scorePath, name);
            while (true) {
                System.out.print("Add queston? (yes/no): ");
                String choice = input.next();

                if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
                    addQuestion();
                    while (true) {
                        System.out.println("Add more question? (yes/no): ");
                        String choice2 = input.next();

                        if (choice2.toLowerCase().equals("yes") || choice2.toLowerCase().equals("y")) {
                            addQuestion();
                        } else if (choice2.toLowerCase().equals("no") || choice2.toLowerCase().equals("n")) {
                            break;
                        } else {
                            System.out.println("Error: Invalid Input");
                        }
                    }
                    break;
                } else if (choice.toLowerCase().equals("no") || choice.toLowerCase().equals("n")) {
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }
            }
        }
    }

    public static void addQuestion() throws IOException {
        QuestionWriter questionWriter = new QuestionWriter(category);

        Util.clear();
        System.out.print("Question: ");
        String question = input.next();

        System.out.print("Choice: ");
        ArrayList<String> choices = new ArrayList<>();
        choices.add(input.next());
        while (true) {
            System.out.print("Add more choice? (yes/no): ");
            String choice = input.next();

            if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
                System.out.print("Choice: ");
                choices.add(input.next());
            } else if (choice.toLowerCase().equals("no") || choice.toLowerCase().equals("n")) {
                break;
            } else {
                System.out.println("Error: Invalid Input");
            }
        }

        char answer;
        while (true) {
            System.out.print("Answer: ");
            char userAnswer = input.next().charAt(0);

            if ((int) userAnswer < ('a' + choices.size())
                    && (int) userAnswer >= 97) {
                answer = userAnswer;
                break;
            } else {
                System.out.println("Error: Choice doesn't exist.");
            }
        }

        questionWriter.append(question, choices, answer);
        System.out.println("Question Added.");
    }

    public static void editCategory() throws FileNotFoundException, IOException {
        ArrayList<Question> questionList = questionReader.readFile(category);
        Util.clear();
        System.out.println("Question list");
        System.out.println();
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println((i + 1) + ": " + questionList.get(i).getQuestion());
        }
        System.out.println();
        System.out.println("What operation do you want to do?");
        System.out.println();
        System.out.println("1. Add question");
        System.out.println("2. Remove question");
        System.out.println();
        while (true) {
            System.out.print("Choose: ");

            try {
                int operation = input.nextInt();

                if (operation == 1) {
                    addQuestion();
                    break;
                } else if (operation == 2) {
                    while (true) {
                        System.out.print("Question to remove: ");
                        try {
                            int index = input.nextInt();

                            if ((index > 0) && (index <= questionList.size())) {
                                questionRemover.remove(category, questionList.get(index - 1));
                                System.out.println("Quesion Removed.");
                                break;
                            } else {
                                System.out.println("Error: Question doesn't exist.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Invalid Input");
                        }
                    }
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }

    public static void startEditCategory() throws FileNotFoundException, IOException {
        while (true) {
            System.out.print("Choose category to edit: ");

            try {

                int operation = input.nextInt();

                if ((operation <= categoryList.size()) && (operation > 0)) {
                    category = categoryList.get(operation - 1);
                    editCategory();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }

    public static void deleteCateogry() {

        while (true) {
            System.out.print("Choose category to delete: ");

            try {

                int operation = input.nextInt();

                if ((operation <= categoryList.size()) && (operation > 0)) {
                    categoryRemover.remove(categoryList.get(operation - 1));
                    File scoreFile = new File("data" + File.separator + "leaderboard" + File.separator + categoryList.get(operation - 1).getName());
                    scoreFile.delete();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }

    public static void startEdit() throws IOException {
        Util.clear();
        System.out.println("Categories list");
        System.out.println();
        for (int i = 1; i <= categoryList.size(); i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName().replaceFirst("[.][^.]+$", ""));
        }
        System.out.println();

        System.out.println("Which operation do you want to do?");
        System.out.println();
        System.out.println("1. New Category");
        System.out.println("2. Edit Category");
        System.out.println("3. Delete Category");
        System.out.println();
        while (true) {
            System.out.print("Choose: ");

            try {
                int operation = input.nextInt();

                if (operation == 1) {
                    newCategory();
                    break;
                } else if (operation == 2) {
                    startEditCategory();
                    break;
                } else if (operation == 3) {
                    deleteCateogry();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
            }
        }
    }
}
