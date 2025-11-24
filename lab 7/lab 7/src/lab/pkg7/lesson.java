package lab.pkg7;

public class Lesson {

    private int lessonId;
    private String title;
    private String content;
    private boolean completed;
    private Quiz quiz;
    private double quizAvg;
    private double completionPercentage;

    public Lesson(int lessonId, String title, String content, boolean completed,
                  double quizAvg, double completionPercentage) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.completed = completed;
        this.quiz = new Quiz();
        this.quizAvg = quizAvg;
        this.completionPercentage = completionPercentage;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz q) {
        this.quiz = q;
    }

    public double getQuizAvg() {
        return quizAvg;
    }

    public void setQuizAvg(double quizAvg) {
        this.quizAvg = quizAvg;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}