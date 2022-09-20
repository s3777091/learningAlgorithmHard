import java.util.Stack;

public class W8P4_AllPermutation {
  static String[] elements;
  static int n;  // n is the size of the elements array

  public static void main(String[] args) {
    elements = new String[4];
    elements[0] = "A";
    elements[1] = "B";
    elements[2] = "C";
    elements[3] = "D";
    n = elements.length;
    Stack<String> test = new Stack<>();
    boolean[] used = new boolean[n];
    buildPermutation(test, used, 0);
  }

  // Use all possible values for the position idx
  // to create all permutations
  static void buildPermutation(Stack<String> current, boolean[] used, int idx) {
    if (idx == n) {
      // finish the last element, display the permutation
      display(current);
      return;
    }
    for (int i = 0; i < n; i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      current.push(elements[i]);
      buildPermutation(current, used, idx + 1);
      used[i] = false;
      current.pop();
    }
  }

  static void display(Stack<String> permu) {
    StringBuilder sb = new StringBuilder();
    for (String s : permu) {
      sb.append(s);
    }
    System.out.println(sb);
  }
}
