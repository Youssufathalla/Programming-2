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

public class CertificateManager implements Manager {

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
        this.certificates = new ArrayList<>();
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
        this.certificates.add(cert); 
        

        JsonDatabase.saveUsers(sm, Lab7.instructorManager);
        JsonDatabase.saveCourses(cm);

        return cert;
    }
    @Override
    public void save(ArrayList<Record> list) {
        certificates.clear();

        for (Record r : list) {
            if (r instanceof Certificate) {
                certificates.add((Certificate) r);
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> copy = new ArrayList<>();
        for (Certificate c : certificates) {
            copy.add(c);
        }
        return copy;
    }

    @Override
    public void add(Record r) {
        if (r instanceof Certificate) {
            certificates.add((Certificate) r);
        }
    }

    @Override
    public void delete(Record r) {
        if (!(r instanceof Certificate)) return;

        Certificate target = (Certificate) r;

        certificates.removeIf(c -> c.getCertificateId().equals(target.getCertificateId()));
    }

    @Override
    public void update(Record r) {
        if (!(r instanceof Certificate)) return;

        Certificate updated = (Certificate) r;

        for (int i = 0; i < certificates.size(); i++) {
            Certificate c = certificates.get(i);

            if (c.getCertificateId().equals(updated.getCertificateId())) {
                certificates.set(i, updated);
                return;
            }
        }
    }

    @Override
    public Record search(int id) {
    
        String target = String.valueOf(id);

        for (Certificate c : certificates) {
            if (c.getCertificateId().equals(target)) {
                return c;
            }
        }
        return null;
    }

    
}

