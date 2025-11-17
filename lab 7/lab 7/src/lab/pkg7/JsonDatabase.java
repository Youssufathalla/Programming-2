package lab.pkg7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JsonDatabase {

    private static final String USERS_FILE = "users.json";
    private static final String COURSES_FILE = "courses.json";

    public static void saveUsers(StudentManager sm, InstructorManager im) {
        JSONArray usersArr = new JSONArray();

        for (Record r : sm.read()) {
            Student s = (Student) r;
            JSONObject obj = new JSONObject();
            obj.put("type", "student");
            obj.put("userId", s.getUserId());
            obj.put("username", s.getUsername());
            obj.put("email", s.getEmail());
            obj.put("passwordHash", s.getPasswordHash());
            obj.put("enrolledCourses", new JSONArray(s.getEnrolledCourses()));
            usersArr.put(obj);
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
            usersArr.put(obj);
        }

        try (FileWriter fw = new FileWriter(USERS_FILE)) {
            fw.write(usersArr.toString());
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

                    if (obj.has("enrolledCourses")) {
                        JSONArray ec = obj.getJSONArray("enrolledCourses");
                        for (int j = 0; j < ec.length(); j++) {
                            int cid = ec.getInt(j);
                            s.getEnrolledCourses().add(cid);
                        }
                    }

                    students.add(s);
                } else if (type.equals("instructor")) {
                    int id = obj.getInt("userId");
                    String username = obj.getString("username");
                    String email = obj.getString("email");
                    String pw = obj.getString("passwordHash");
                    Instructor inst = new Instructor(id, username, email, pw);

                    if (obj.has("createdCourses")) {
                        JSONArray cc = obj.getJSONArray("createdCourses");
                        for (int j = 0; j < cc.length(); j++) {
                            int cid = cc.getInt(j);
                            inst.getCreatedCourses().add(cid);
                        }
                    }

                    instructors.add(inst);
                }
            }

            sm.save(students);
            im.save(instructors);

        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }

    public static void saveCourses(CourseManager cm) {
        JSONArray coursesArr = new JSONArray();

        for (Record r : cm.read()) {
            Course c = (Course) r;
            JSONObject obj = new JSONObject();

            obj.put("courseId", c.getCourseId());
            obj.put("title", c.getTitle());
            obj.put("description", c.getDescription());
            obj.put("instructorId", c.getInstructorId());

            JSONArray lessonsArr = new JSONArray();
            if (c.getLessons() != null) {
                for (Lesson l : c.getLessons()) {
                    JSONObject lobj = new JSONObject();
                    lobj.put("lessonId", l.getLessonId());
                    lobj.put("title", l.getTitle());
                    lobj.put("content", l.getContent());
                    lobj.put("completed", l.isCompleted());
                    lessonsArr.put(lobj);
                }
            }
            obj.put("lessons", lessonsArr);

            JSONArray enrolled = new JSONArray();
            if (c.getEnrolledStudents() != null) {
                for (Integer sid : c.getEnrolledStudents()) {
                    enrolled.put(sid);
                }
            }
            obj.put("enrolledStudents", enrolled);

            coursesArr.put(obj);
        }

        try (FileWriter fw = new FileWriter(COURSES_FILE)) {
            fw.write(coursesArr.toString());
        } catch (Exception e) {
        }
    }

    public static void loadCourses(CourseManager cm) {
        cm.save(new ArrayList<>());

        try {
            FileReader fr = new FileReader(COURSES_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) sb.append((char) ch);
            fr.close();

            JSONArray arr = new JSONArray(sb.toString());
            ArrayList<Record> courses = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                int cid = obj.getInt("courseId");
                String title = obj.getString("title");
                String desc = obj.getString("description");
                int instructorId = obj.getInt("instructorId");

                Course c = new Course(cid, title, desc, instructorId);

                if (obj.has("lessons")) {
                    JSONArray lessonsArr = obj.getJSONArray("lessons");
                    for (int j = 0; j < lessonsArr.length(); j++) {
                        JSONObject lobj = lessonsArr.getJSONObject(j);
                        int lid = lobj.getInt("lessonId");
                        String ltitle = lobj.getString("title");
                        String lcontent = lobj.getString("content");
                        boolean lcompleted = lobj.optBoolean("completed", false);

                        Lesson lesson = new Lesson(lid, ltitle, lcontent, lcompleted);
                        c.addLesson(lesson);
                    }
                }

                if (obj.has("enrolledStudents")) {
                    JSONArray enrollArr = obj.getJSONArray("enrolledStudents");
                    for (int j = 0; j < enrollArr.length(); j++) {
                        int sid = enrollArr.getInt(j);
                        c.addStudent(sid);
                    }
                }

                courses.add(c);
            }

            cm.save(courses);

        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }
}
