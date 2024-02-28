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

    /**
     * Method to create new category function.
     * 
     * @throws IOException
     */
    public static void newCategory() throws IOException {
        // Ask for category name.
        System.out.print("Category name: ");
        String name = input.next();

        // If new file was create successfully continue.
        if (categoryWriter.createCategory(name)) {

            // Assign File object with path of new file to category attribute.
            category = new File("data" + File.separator + "quiz" + File.separator + name + ".csv");

            // Create new file to save score for the new category.
            scoreWriter.newFile(scorePath, name);

            // Ask wheter user want to add qusetion to category that just craeted or not.
            while (true) {
                System.out.print("Add queston? (yes/no): ");
                String choice = input.next();

                // If user say yes, invoke addQuestion method.
                if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
                    addQuestion();
                    while (true) {
                        System.out.print("Add more question? (yes/no): ");
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

    /**
     * Method add question to category that save within object.
     * 
     * @throws IOException
     */
    public static void addQuestion() throws IOException {
        QuestionWriter questionWriter = new QuestionWriter(category);

        // Clear terminal.
        Util.clear();
        input.nextLine();
        // Ask for Question.
        System.out.print("Question: ");
        String question = input.nextLine();

        // Ask for choices and save it in ArrayList.
        System.out.print("Choice a: ");
        ArrayList<String> choices = new ArrayList<>();
        String choice = input.nextLine();
        choices.add(choice);
        // Keep asking if user want to add more choice or not until they say no.
        int i = 1;
        while (true) {
            System.out.print("Add more choice? (yes/no): ");
            choice = input.nextLine();

            if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
                System.out.print("Choice " + (char) ('a' + i) + ": ");
                choice = input.nextLine();
                choices.add(choice);
                i++;
            } else if (choice.toLowerCase().equals("no") || choice.toLowerCase().equals("n")) {
                break;
            } else {
                System.out.println("Error: Invalid Input");
            }

            if (choices.size() == 26) {
                System.out.println("Limit of choices reached.");
                break;
            }
        }

        // Ask for answer.
        char answer;
        // Make sure that answer is valid.
        while (true) {
            System.out.print("Answer (as choice a, b, c, d, ...): ");
            char userAnswer = input.next().charAt(0);

            if ((int) userAnswer < ('a' + choices.size())
                    && (int) userAnswer >= 97) {
                answer = userAnswer;
                break;
            } else {
                System.out.println("Error: Choice doesn't exist.");
            }
        }

        // Add question data to .csv file.
        questionWriter.append(question, choices, answer);
        System.out.println("Question Added.");
    }

    /**
     * Method to print menu when user choose to edit category.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
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

                // Invoke addQuestion if they choose to add new question.
                if (operation == 1) {
                    addQuestion();
                    break;
                    // Remove question with QuestionRemover if they choose to remove question.
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
                            input.next();
                        }
                    }
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.next();
            }
        }
    }

    /**
     * Method to print all category and let user choose which one to edit.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
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
                input.next();
            }
        }
    }

    /**
     * Method to delete category.
     */
    public static void deleteCategory() {

        // Ask user which category to delete.
        while (true) {
            System.out.print("Choose category to delete: ");

            try {

                int operation = input.nextInt();

                if ((operation <= categoryList.size()) && (operation > 0)) {
                    categoryRemover.remove(categoryList.get(operation - 1));
                    File scoreFile = new File("data" + File.separator + "leaderboard" + File.separator
                            + categoryList.get(operation - 1).getName());
                    scoreFile.delete();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.next();
            }
        }
    }

    /**
     * Main method for edit feature
     * 
     * @throws IOException
     */
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
                    deleteCategory();
                    break;
                } else {
                    System.out.println("Error: Invalid Input");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input");
                input.next();
            }
        }
    }
}
