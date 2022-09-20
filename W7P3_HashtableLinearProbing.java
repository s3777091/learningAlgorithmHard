public class W7P3_HashtableLinearProbing {
  public static void main(String[] args) {
    W7Hashtable2 table = new W7Hashtable2();
    System.out.println("Adding to the Hashtable - Linear probing");
    table.put(new RMITStudent("S123", "IT", "Tri", 82.00));
    table.put(new RMITStudent("S456", "SE", "Tom", 70.50));
    table.put(new RMITStudent("S789", "Business", "Alice", 85.50));
    table.put(new RMITStudent("S213", "Music", "Bob", 79.50));
    table.put(new RMITStudent("S231", "Psychology", "An", 65.50));
    table.put(new RMITStudent("S123", "Aviation", "Teo", 90.00));
    table.put(new RMITStudent("S312", "Security", "Winner", 80.50));
    table.put(new RMITStudent("S312", "Marketing", "Loser", 71.50));

    System.out.println("Retrieving from the Hashtable");
    table.get("S123");
    table.get("S456");
    table.get("S789");
    table.get("S312");
    table.get("S321");
    table.get("S999");
    table.get("S564");
  }

  static class W7Hashtable2 {
    private static final int N = 13;
    private int size;
    private RMITStudent[] items;
  
    public W7Hashtable2() {
      size = 0;
      items = new RMITStudent[N];
    }
  
    // hash function
    private int hash(String s) {
      int res = 0;
      for (char c : s.toCharArray()) {
        res += hashChar(c);
      }
      return res % N;
    }
  
    // hash function for individual character
    private int hashChar(char c) {
      if (c >= '0' && c <= '9') {
        return c - '0' + 26;
      }
      return c - 'A';
    }
  
    public int size() {
      return size;
    }
  
    // add a student to the collection
    // assume the hashtable will never be full
    public boolean put(RMITStudent s) {
      int idx = hash(s.studentId);
      // count the number of collisions
      int count = 0;
      // linear probing
      while (items[idx] != null) {
        // existed?
        if (items[idx].studentId.equals(s.studentId)) {
          System.out.printf("Not-added, collisions: %d\n", count);
          return false;
        }
        count++;
        idx = (idx + 1) % N;
      }
      items[idx] = s;
      size++;
      System.out.printf("Added, collissions: %d\n", count);
      return true;
    }
  
    // check if a student with a given id exists in the collection
    public RMITStudent get(String id) {
      int idx = hash(id);
      // count the number of steps
      int step = 1;
      // linear probing
      while (items[idx] != null) {
        // existed?
        if (items[idx].studentId.equals(id)) {
          System.out.printf("Found, steps: %d\n", step);
          return items[idx];
        }
        step++;
        idx = (idx + 1) % N;
      }
      System.out.printf("Not found, steps: %d\n", step);
      return null;
    }
  }

  static class RMITStudent {
    String studentId;
    String major;
    String fullName;
    double GPA;

    public RMITStudent(String id, String m, String name, double gpa) {
      studentId = id;
      major = m;
      fullName = name;
      GPA = gpa;
    }
  }
}
