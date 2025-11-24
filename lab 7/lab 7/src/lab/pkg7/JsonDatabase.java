package lab.pkg7;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonDatabase {

    private static final String USERS_FILE = "users.json";
    private static final String COURSES_FILE = "courses.json";

    public static void saveUsers(StudentManager sm, InstructorManager im) {
        JSONArray arr = new JSONArray();

        for (Record r : sm.read()) {
            Student s = (Student) r;
            JSONObject obj = new JSONObject();
            obj.put("type", "student");
            obj.put("userId", s.getUserId());
            obj.put("username", s.getUsername());
            obj.put("email", s.getEmail());
            obj.put("passwordHash", s.getPasswordHash());
            obj.put("enrolledCourses", new JSONArray(s.getEnrolledCourses()));
            obj.put("progress", s.getProgress());

            JSONObject qs = new JSONObject();
            for (Integer cid : s.getQuizScores().keySet()) {
                JSONObject inner = new JSONObject();
                for (Integer lid : s.getQuizScores().get(cid).keySet()) {
                    inner.put(String.valueOf(lid), s.getQuizScores().get(cid).get(lid));
                }
                qs.put(String.valueOf(cid), inner);
            }
            obj.put("quizScores", qs);

            JSONArray certArr = new JSONArray();
            if (s.getCertificates() != null) {
                for (Certificate cert : s.getCertificates()) {
                    JSONObject cObj = new JSONObject();
                    cObj.put("certificateId", cert.getCertificateId());
                    cObj.put("studentId", cert.getStudentId());
                    cObj.put("courseId", cert.getCourseId());
                    cObj.put("issueDate", cert.getIssueDate());
                    cObj.put("message", "Congratulations! You have successfully completed the course.");
                    certArr.put(cObj);
                }
            }
            obj.put("certificates", certArr);

            arr.put(obj);
        }

        for (Record r : im.read()) {
            Instructor inst = (Instructor) r;
            JSONObject obj = new JSONObject();
            obj.put("type", "instructor");
            obj.put("userId", inst.getUserId());
            obj.put("username", inst.getUsername());
            obj.put("email", inst.getEmail());
            obj.put("passwordHash", inst.getPasswordHash());
            obj.put("createdCourses", new JSONArray(inst.getCreatedCourses()));
            arr.put(obj);
        }

        try (FileWriter fw = new FileWriter(USERS_FILE)) {
            fw.write(arr.toString());
        } catch (Exception e) {
        }
    }

    public static void loadUsers(StudentManager sm, InstructorManager im) {
        sm.save(new ArrayList<>());
        im.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(USERS_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) {
                sb.append((char) ch);
            }
            fr.close();

            JSONArray arr = new JSONArray(sb.toString());
            ArrayList<Record> students = new ArrayList<>();
            ArrayList<Record> instructors = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String type = obj.getString("type");

                if (type.equals("student")) {
                    int id = obj.getInt("userId");
                    String username = obj.getString("username");
                    String email = obj.getString("email");
                    String pw = obj.getString("passwordHash");
                    Student s = new Student(id, username, email, pw);

                    JSONArray ec = obj.optJSONArray("enrolledCourses");
                    if (ec != null) {
                        for (int j = 0; j < ec.length(); j++) {
                            s.enrollCourse(ec.getInt(j));
                        }
                    }

                    s.setProgress(obj.optDouble("progress", 0.0));

                    JSONObject qsObj = obj.optJSONObject("quizScores");
                    if (qsObj != null) {
                        Field f = Student.class.getDeclaredField("quizScores");
                        f.setAccessible(true);

                        @SuppressWarnings("unchecked")
                        HashMap<Integer, HashMap<Integer, Integer>> realQS
                                = (HashMap<Integer, HashMap<Integer, Integer>>) f.get(s);

                        for (String courseKey : qsObj.keySet()) {
                            int cid = Integer.parseInt(courseKey);
                            JSONObject inner = qsObj.getJSONObject(courseKey);

                            HashMap<Integer, Integer> lessonMap = new HashMap<>();
                            for (String lessonKey : inner.keySet()) {
                                lessonMap.put(Integer.parseInt(lessonKey), inner.getInt(lessonKey));
                            }
                            realQS.put(cid, lessonMap);
                        }
                    }

                    JSONArray certArr = obj.optJSONArray("certificates");
                    if (certArr != null) {
                        for (int j = 0; j < certArr.length(); j++) {
                            JSONObject cObj = certArr.getJSONObject(j);
                            Certificate cert = new Certificate(
                                    cObj.getString("certificateId"),
                                    cObj.getInt("studentId"),
                                    cObj.getInt("courseId"),
                                    cObj.getString("issueDate")
                            );
                            s.getCertificates().add(cert);
                        }
                    }

                    students.add(s);
                }

                if (type.equals("instructor")) {
                    int id = obj.getInt("userId");
                    String username = obj.getString("username");
                    String email = obj.getString("email");
                    String pw = obj.getString("passwordHash");
                    Instructor inst = new Instructor(id, username, email, pw);

                    JSONArray cc = obj.optJSONArray("createdCourses");
                    if (cc != null) {
                        for (int j = 0; j < cc.length(); j++) {
                            inst.getCreatedCourses().add(cc.getInt(j));
                        }
                    }

                    instructors.add(inst);
                }
            }

            sm.save(students);
            im.save(instructors);

        } catch (Exception e) {
        }
    }

    public static void loadAdmins(AdminManager am) {
        am.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(USERS_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) {
                sb.append((char) ch);
            }
            fr.close();

            JSONArray arr = new JSONArray(sb.toString());
            ArrayList<Record> admins = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if (obj.optString("type", "").equals("admin")) {
                    admin a = new admin(
                            obj.getInt("userId"),
                            obj.getString("username"),
                            obj.getString("email"),
                            obj.getString("passwordHash")
                    );
                    admins.add(a);
                }
            }

            am.save(admins);

        } catch (Exception e) {
        }
    }

    public static void saveCourses(CourseManager cm) {
        JSONArray arr = new JSONArray();

        for (Record r : cm.read()) {
            Course c = (Course) r;
            JSONObject obj = new JSONObject();

            obj.put("courseId", c.getCourseId());
            obj.put("title", c.getTitle());
            obj.put("description", c.getDescription());
            obj.put("instructorId", c.getInstructorId());
            obj.put("approval", c.getApproval());

            JSONArray lessonsArr = new JSONArray();
            for (Lesson l : c.getLessons()) {
                JSONObject lobj = new JSONObject();
                lobj.put("lessonId", l.getLessonId());
                lobj.put("title", l.getTitle());
                lobj.put("content", l.getContent());
                lobj.put("completed", l.isCompleted());
                lobj.put("quizAvg", l.getQuizAvg());
                lobj.put("completionPercentage", l.getCompletionPercentage());

                if (l.getQuiz() != null) {
                    lobj.put("quizCompleted", l.getQuiz().isQuizCompleted());
                }

                JSONArray quizArr = new JSONArray();
                Quiz q = l.getQuiz();
                if (q != null) {
                    for (int i2 = 0; i2 < q.getQuestions().size(); i2++) {
                        JSONObject qObj = new JSONObject();
                        qObj.put("question", q.getQuestions().get(i2));
                        qObj.put("correct", q.getCorrectAnswers().get(i2));
                        qObj.put("options", new JSONArray(q.getOptions().get(i2)));
                        quizArr.put(qObj);
                    }
                }

                lobj.put("quiz", quizArr);
                lessonsArr.put(lobj);
            }

            obj.put("lessons", lessonsArr);
            obj.put("enrolledStudents", new JSONArray(c.getEnrolledStudents()));
            arr.put(obj);
        }

        try (FileWriter fw = new FileWriter(COURSES_FILE)) {
            fw.write(arr.toString());
        } catch (Exception e) {
        }
    }

    public static void loadCourses(CourseManager cm) {
        cm.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(COURSES_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) {
                sb.append((char) ch);
            }
            fr.close();

            JSONArray arr = new JSONArray(sb.toString());
            ArrayList<Record> list = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                Course c = new Course(
                        obj.getInt("courseId"),
                        obj.getString("title"),
                        obj.getString("description"),
                        obj.getInt("instructorId")
                );
                c.setApproval(obj.optString("approval", ""));

                JSONArray lessonsArr = obj.getJSONArray("lessons");
                for (int j = 0; j < lessonsArr.length(); j++) {
                    JSONObject lobj = lessonsArr.getJSONObject(j);

                    Lesson lesson = new Lesson(
                            lobj.getInt("lessonId"),
                            lobj.getString("title"),
                            lobj.getString("content"),
                            lobj.getBoolean("completed"),
                            lobj.optDouble("quizAvg", 0.0),
                            lobj.optDouble("completionPercentage", 0.0)
                    );

                    if (lobj.has("quiz")) {
                        JSONArray quizArr = lobj.getJSONArray("quiz");
                        Quiz q = new Quiz();

                        for (int k = 0; k < quizArr.length(); k++) {
                            JSONObject qObj = quizArr.getJSONObject(k);

                            String qText = qObj.getString("question");
                            int correct = qObj.getInt("correct");

                            JSONArray optArr = qObj.getJSONArray("options");
                            ArrayList<String> opts = new ArrayList<>();
                            for (int t = 0; t < optArr.length(); t++) {
                                opts.add(optArr.getString(t));
                            }

                            q.addQuestion(qText, opts, correct);
                        }

                        boolean quizCompleted = lobj.optBoolean("quizCompleted", false);
                        q.setQuizCompleted(quizCompleted);

                        lesson.setQuiz(q);
                    }

                    c.getLessons().add(lesson);
                }

                JSONArray enrollArr = obj.getJSONArray("enrolledStudents");
                for (int j = 0; j < enrollArr.length(); j++) {
                    c.getEnrolledStudents().add(enrollArr.getInt(j));
                }

                list.add(c);
            }

            cm.save(list);

        } catch (Exception e) {
        }
    }
}
