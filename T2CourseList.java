import java.util.ArrayList;

public class T2CourseList {
  public static void main(String[] args) {
    Course c1 = new Course("Programming 1", "C123");
    Course c2 = new Course("Web Programming", "C456");
    Course c3 = new Course("Data Structures", "C789");
    Course c4 = new Course("Database Application", "C000");
    T2CourseList list = new T2CourseList();
    list.addCourse(c1);
    list.addCourse(c2);
    list.addCourse(c3);
    list.addCourse(c4);
    list.addPrerequisite(c2, c1);  // make Programming 1 a prerequisite of Web Programming
    list.addPrerequisite(c3, c1);  // make Programming 1 a prerequisite of Data Structures
    list.addPrerequisite(c4, c2);  // make Web Programming a prerequisite of Database Application
    System.out.println(list.takeFirst(c1));  // true
    System.out.println(list.takeFirst(c3));  // false
    System.out.println(list.coursesTaken()); // return "Programming 1, Web Programming, Data Structures, Database Application"
  }

  ArrayList<Course> courses;

  public T2CourseList() {
    courses = new ArrayList<Course>();
  }

  public void addCourse(Course c) {
    courses.add(c);
  }

  public void addPrerequisite(Course c, Course pre) {
    c.addPreq(pre);
  }

  public boolean takeFirst(Course c) {
    return (c.preq.size() == 0);
  }

  public String coursesTaken() {
    // Return the courses' names concatenated by commas
    int n = courses.size();
    ArrayList<Course> res = new ArrayList<Course>();
    boolean[] added = new boolean[n];

    // Do until we have extracted n courses
    while (res.size() < n) {
      for (int i = 0; i < n; i ++) {
        if (added[i]) continue;
        Course c = courses.get(i);
        boolean found = false;
        for (int j = 0; j < n; j++) {
          if (added[j]) continue;
          if (i == j) continue;
          Course pre = courses.get(j);
          if (c.preq.contains(pre)) {
            found = true;
            break;
          }
        }
        if (found) {
          continue;
        }
        res.add(c);
        added[i] = true;
        for (int j = 0; j < n; j++) {
          Course t = courses.get(j);
          if (t.preq.contains(c)) {
            t.preq.remove(c);
          }
        }
      }
    }
    String result = "";
    for (int i = 0; i < n; i ++) {
      result += ", " + res.get(i).name;
    }
    return result.substring(2);
  }
}

class Course {
  String name;
  String code;
  ArrayList<Course> preq;

  public Course(String n, String c) {
    name = n;
    code = c;
    preq = new ArrayList<Course>();
  }

  public void addPreq(Course requiredCourse) {
    preq.add(requiredCourse);
  }
}
