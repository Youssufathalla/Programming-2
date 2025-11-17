package lab.pkg7;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonDatabase {

    private static final String USERS_FILE = "users.json";
    private static final String COURSES_FILE = "courses.json";

    // ==============================
    //  SAVE USERS
    // ==============================
    public static void saveUsers(StudentManager sm, InstructorManager im) {
        JSONArray arr = new JSONArray();

        // STUDENTS
        for (Record r : sm.read()) {
            Student s = (Student) r;
            JSONObject obj = new JSONObject();
            obj.put("type", "student");
            obj.put("userId", s.getUserId());
            obj.put("username", s.getUsername());
            obj.put("email", s.getEmail());
            obj.put("passwordHash", s.getPasswordHash());
            obj.put("enrolledCourses", new JSONArray(s.getEnrolledCourses()));

            arr.put(obj);
        }

        // INSTRUCTORS
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

    // ==============================
    //  LOAD USERS
    // ==============================
    public static void loadUsers(StudentManager sm, InstructorManager im) {
        sm.save(new ArrayList<>());
        im.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(USERS_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) sb.append((char) ch);
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

    // ==============================
    //  SAVE COURSES
    // ==============================
    public static void saveCourses(CourseManager cm) {
        JSONArray arr = new JSONArray();

        for (Record r : cm.read()) {
            Course c = (Course) r;
            JSONObject obj = new JSONObject();

            obj.put("courseId", c.getCourseId());
            obj.put("title", c.getTitle());
            obj.put("description", c.getDescription());
            obj.put("instructorId", c.getInstructorId());

            // LESSONS
            JSONArray lessonsArr = new JSONArray();
            for (Lesson l : c.getLessons()) {
                JSONObject lobj = new JSONObject();
                lobj.put("lessonId", l.getLessonId());
                lobj.put("title", l.getTitle());
                lobj.put("content", l.getContent());
                lobj.put("completed", l.isCompleted());

                lessonsArr.put(lobj);
            }
            obj.put("lessons", lessonsArr);

            // ENROLLED STUDENTS
            obj.put("enrolledStudents", new JSONArray(c.getEnrolledStudents()));

            arr.put(obj);
        }

        try (FileWriter fw = new FileWriter(COURSES_FILE)) {
            fw.write(arr.toString());
        } catch (Exception e) {
        }
    }

    // ==============================
    //  LOAD COURSES
    // ==============================
    public static void loadCourses(CourseManager cm) {
        cm.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(COURSES_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) sb.append((char) ch);
            fr.close();

            JSONArray arr = new JSONArray(sb.toString());
            ArrayList<Record> list = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                int cid = obj.getInt("courseId");
                String title = obj.getString("title");
                String desc = obj.getString("description");
                int instructorId = obj.getInt("instructorId");

                Course c = new Course(cid, title, desc, instructorId);

                // LESSONS
                JSONArray lessonsArr = obj.getJSONArray("lessons");
                for (int j = 0; j < lessonsArr.length(); j++) {
                    JSONObject lobj = lessonsArr.getJSONObject(j);

                    int lid = lobj.getInt("lessonId");
                    String ltitle = lobj.getString("title");
                    String lcontent = lobj.getString("content");
                    boolean completed = lobj.getBoolean("completed");

                    c.getLessons().add(new Lesson(lid, ltitle, lcontent, completed));
                }

                // ENROLLED STUDENTS
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
