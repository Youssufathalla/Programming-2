/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */
public class Lesson {
    private int lessonId;
    private String title;
    private String content;
    private Boolean completed;

    public Lesson(int lessonId, String title, String content, Boolean completed) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.completed = false;
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
<<<<<<< Updated upstream
=======

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
>>>>>>> Stashed changes
    
}
