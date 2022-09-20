public class W7_HashtableSeparateChaining {
  public static void main(String[] args) {
    W7Hashtable table = new W7Hashtable();
    System.out.println("Adding to the Hashtable - Separate chaining");
    table.put(new RMITStudent("S1234567", "Tri", 75.50));
    table.put(new RMITStudent("S2234567", "Tom", 80.00));
    table.put(new RMITStudent("S3234567", "Alice", 60.50));
    table.put(new RMITStudent("S4234567", "Bob", 75.50));
    table.put(new RMITStudent("S1134567", "An", 79.00));
    table.put(new RMITStudent("S1234567", "Teo", 82.50));
    table.put(new RMITStudent("S1334567", "Winner", 90.50));
    table.put(new RMITStudent("S1334567", "Loser", 50.50));

    System.out.println("Retrieving from the Hashtable");
    table.get("S1234567");
    table.get("S2234567");
    table.get("S3234567");
    table.get("S4234567");
    table.get("S1134567");
    table.get("S1334567");
    table.get("S9999999");
    table.get("S1111111");
  }

  // I made this class an inner class
  // just to prevent conflicts with other classes
  // having the same name
  static class W7Hashtable {
    private static final int N = 10;  // array size
    private int size;  // number of students
    private StudentNode[] students;  // student storage
  
    public W7Hashtable() {
      size = 0;
      students = new StudentNode[N];
    }
  
    // hash function
    private int hash(String studentId) {
      // Assume student id always has the format
      // Sabcdxyz, where a, b, c, d, x, y, z are 7 digits
      // E.g., S1234567 or S9995444 are 2 valid cases
      // And we use the first digit as the hash value
      return studentId.charAt(1) - '0';
    }
  
    public int size() {
      return size;
    }
  
    // add an RMIT student to the collection
    public boolean put(RMITStudent student) {
      int idx = hash(student.studentId);
      StudentNode node = new StudentNode(student);
      // count how many collisions
      int count = 0;
      // empty slot?
      if (students[idx] == null) {
        students[idx] = node;
        size++;
        System.out.printf("Added, collisions: %d\n", count);
        return true;
      }
      // Collision resolution
      StudentNode parent = null;
      StudentNode t = students[idx];
      while (t != null) {
        if (t.student.studentId.equals(student.studentId)) {
          // duplicated, return fasle
          System.out.printf("Not-added, collisions: %d\n", count);
          return false;
        }
        count++;
        parent = t;
        t = t.next;
      }
      parent.next = node;
      size++;
      System.out.printf("Added, collisions: %d\n", count);
      return true;
    }

    // get an RMIT student from the student id
    public RMITStudent get(String studentId) {
      int idx = hash(studentId);
      // count how many steps are needed
      int step = 1;
      // empty slot?
      if (students[idx] == null) {
        System.out.printf("Not found, steps: %d\n", step);
        return null;
      }
      // possible answer
      StudentNode t = students[idx];
      while (t != null) {
        if (t.student.studentId.equals(studentId)) {
          // found it
          System.out.printf("Found, steps: %d\n", step);
          return t.student;
        }
        step++;
        t = t.next;
      }
      System.out.printf("Not found, steps: %d\n", step);
      return null;
    }
  }

  static class StudentNode {
    RMITStudent student;
    StudentNode next;

    public StudentNode(RMITStudent student) {
      this.student = student;
      next = null;
    }
  }

  static class RMITStudent {
    String studentId;
    String fullName;
    double GPA;

    public RMITStudent(String id, String name, double gpa) {
      studentId = id;
      fullName = name;
      GPA = gpa;
    }
  }
}


