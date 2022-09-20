import java.util.Stack;

public class W8P3_AllSubset {
  static String[] elements;
  static int n;  // n is the size of the elements array

  public static void main(String[] args) {
    elements = new String[3];
    elements[0] = "A";
    elements[1] = "B";
    elements[2] = "C";
    // elements[3] = "D";
    n = elements.length;
    Stack<String> test = new Stack<>();
    buildSet(test, 0);
  }

  // From the set current, we can either add/not add element at index idx
  static void buildSet(Stack<String> current, int idx) {
    if (idx == n) {
      // finish the last element, display the set
      display(current);
      return;
    }
    // do not add the element at idx
    buildSet(current, idx + 1);

     // add the element at idx
     current.push(elements[idx]);
     buildSet(current, idx + 1);
     // restore the current set
     current.pop();
  }

  static void display(Stack<String> set) {
    StringBuilder sb = new StringBuilder("[");
    boolean first = true;
    for (String s : set) {
      if (first) {
        sb.append(s);
        first = false;
      } else {
        sb.append(", " + s);
      }      
    }
    sb.append("]");
    System.out.println(sb);
  }
}
