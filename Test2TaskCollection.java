public class Test2TaskCollection {
  public static void main(String[] args) {
    Test2Task t1 = new Test2Task("GET DI", true);
    Test2Task t2 = new Test2Task("GET HD", false);
    Test2TaskCollection taskCol = new Test2TaskCollection();
    System.out.println(taskCol.calcLocation(t1));  // return 40
    System.out.println(taskCol.calcLocation(t2));  // return 39
    System.out.println(taskCol.calcLocation(new Test2Task("GET D I", false)));  // return 40
    System.out.println(taskCol.addTask(t1));  // return 40
    System.out.println(taskCol.addTask(new Test2Task("GET D I", false))); // return 41, due to collision at 40-th location
    System.out.println(taskCol.getTask("GET DI").name);  // return Task t1
    System.out.println(taskCol.getTask("G E T D I")); // return null
    System.out.println(taskCol.getTask("GET HD"));  // return null
  }

  static final int SIZE = 2027;
  Test2Task[] tasks;

  public Test2TaskCollection() {
    tasks = new Test2Task[SIZE];
  }

  public int calcLocation(Test2Task t) {
    int sum = 0;
    String name = t.name;
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        continue;
      }
      sum += (name.charAt(i) - 'A');
    }
    return sum % SIZE;
  }

  public int addTask(Test2Task t) {
    int pos = calcLocation(t);
    // collision handling
    while (tasks[pos] != null) {
      pos = (pos + 1) % SIZE;
    }
    tasks[pos] = t;
    return pos;
  }

  public Test2Task getTask(String name) {
    Test2Task dummy = new Test2Task(name, true);
    int pos = calcLocation(dummy);
    while (tasks[pos] != null) {
      if (tasks[pos].name.equals(name)) {
        return tasks[pos];
      }
      pos = (pos + 1) % SIZE;
    }
    return null;
  }
}

class Test2Task {
  String name;
  boolean status;

  public Test2Task(String n, boolean s) {
    name = n;
    status = s;
  }
}