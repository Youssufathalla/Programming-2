package lab.pkg7;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<Integer> correctAnswers;

    public Quiz() {
        questions = new ArrayList<>();
        options = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    public void addQuestion(String question, ArrayList<String> opts, int correctIndex) {
        questions.add(question);
        options.add(opts);
        correctAnswers.add(correctIndex);
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<ArrayList<String>> getOptions() {
        return options;
    }

    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }
}
