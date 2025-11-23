/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author youssufathalla
 */
package lab.pkg7;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class CertificateManager {

    private CourseManager cm;
    private StudentManager sm;
    
    private ArrayList<Certificate> certificates;

    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(ArrayList<Certificate> certificates) {
        this.certificates = certificates;
    }

    private static final int PASS_SCORE = 50;
    

    public CertificateManager(CourseManager cm, StudentManager sm) {
        this.cm = cm;
        this.sm = sm;
    }

    public boolean isCourseCompleted(Student s, Course c) {
        ArrayList<Lesson> lessons = c.getLessons();
        if (lessons == null || lessons.isEmpty()) return false;

        for (Lesson lesson : lessons) {
            if (lesson == null) continue;
            int lessonId = lesson.getLessonId();
            Integer score = s.getQuizScore(c.getCourseId(), lessonId);
            if (score == null || score < PASS_SCORE) {
                return false;
            }
        }
        return true;
    }

    public Certificate issueCertificateIfEligible(Student s, Course c) {
        if (s.hasCertificateForCourse(c.getCourseId())) {
            return null;
        }
        if (!isCourseCompleted(s, c)) {
            return null;
        }

        String id = UUID.randomUUID().toString();
        String issueDate = LocalDate.now().toString();

        Certificate cert = new Certificate(
                id,
                s.getUserId(),
                c.getCourseId(),
                issueDate
        );

        s.addCertificate(cert);

        JsonDatabase.saveUsers(sm, Lab7.instructorManager);
        JsonDatabase.saveCourses(cm);

        return cert;
    }
}
