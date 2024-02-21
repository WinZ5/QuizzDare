package quiz;

public class User {
    private String username;
    private int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getName() {
        return this.username;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
