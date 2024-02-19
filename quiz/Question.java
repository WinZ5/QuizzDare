package quiz;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> choies = new ArrayList<>();
    private String answer;

    public Question(String question, ArrayList<String> choices, String answer) {
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

    public String getAnswer() {
        return this.answer;
    }

    public boolean checkAnswer(String answer) {
        if ((answer.toLowerCase()).equals(this.answer.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
