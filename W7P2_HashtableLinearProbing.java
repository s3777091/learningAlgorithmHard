public class W7P2_HashtableLinearProbing {
  public static void main(String[] args) {
    W7HashtableLinearProbing table = new W7HashtableLinearProbing();
    System.out.println(table.put("A"));
    System.out.println(table.put("B"));
    System.out.println(table.put("C"));
    System.out.println(table.put("ABC"));
    System.out.println(table.put("D"));
    System.out.println(table.put("E"));
    System.out.println(table.put("F"));
    System.out.println(table.put("DEF"));
    System.out.println(table.put("CBA"));
    System.out.println(table.put("XYZ"));
    System.out.println(table.put("BAC"));

    System.out.println(table.get("A"));
    System.out.println(table.get("BCA"));
  }
}

class W7HashtableLinearProbing {
  private static final int N = 13;
  private int size;
  private String[] items;

  public W7HashtableLinearProbing() {
    size = 0;
    items = new String[N];
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
    return c - 'A';
  }

  public int size() {
    return size;
  }

  // add a String to the collection
  // assume the hashtable will never be full
  public boolean put(String s) {
    int idx = hash(s);
    // count the number of collisions
    int count = 0;
    // linear probing
    while (items[idx] != null) {
      // existed?
      if (items[idx].equals(s)) {
        System.out.printf("Not added, collissions: %d\n", count);
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

  // check if a String exist in a collection
  public boolean get(String s) {
    int idx = hash(s);
    int step = 1;
    // linear probing
    while (items[idx] != null) {
      // existed?
      if (items[idx].equals(s)) {
        System.out.printf("Found, steps: %d\n", step);
        return true;
      }
      step++;
      idx = (idx + 1) % N;
    }
    System.out.printf("Not found, steps: %d\n", step);
    return false;
  }
}
