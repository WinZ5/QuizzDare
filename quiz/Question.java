package quiz;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> choies = new ArrayList<>();
    private char answer;

    public Question(String question, ArrayList<String> choices, char answer) {
        this.question = question;
        this.choies = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getChoices() {
        return this.choies;
    }

    public char getAnswer() {
        return this.answer;
    }

    public boolean checkAnswer(char answer) {
        if (answer == this.answer) {
            return true;
        } else {
            return false;
        }
    }
}
