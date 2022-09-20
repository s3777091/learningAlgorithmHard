import java.util.HashSet;

public class W3P4_ListRemoveLoop {
  public static void main(String[] args) {
    Node head = new Node(1);
    Node n2 = new Node(2);
    head.next = n2;
    Node n3 = new Node(3);
    n2.next = n3;
    Node n4 = new Node(4);
    n3.next = n4;
    Node n5 = new Node(5);
    n4.next = n5;

    // This assignment creates a loop
    n5.next = n3;

    // Visit the list 100 steps or reach the end of it
    Node visitor = head;
    int count = 0;
    while (visitor != null && count < 100) {
      System.out.printf("Current node value = %d and counter = %d\n", visitor.value, count);
      visitor = visitor.next;
      count++;
    }

    // Remove the loop
    visitor = head;
   
    // Use one of two lines below to remove loop
    removeLoopWithSet(visitor);
    // removeLoop2Pointers(visitor);

    // Visit the list 100 steps or reach the end of it
    visitor = head;
    count = 0;
    while (visitor != null && count < 100) {
      System.out.printf("Current node value = %d and counter = %d\n", visitor.value, count);
      visitor = visitor.next;
      count++;
    }
  }

  // is there a loop inside a list?
  public static boolean containsLoop(Node head) {
    Node fast, slow;
    slow = head;
    fast = slow.next;
    while (slow != null) {
      if (fast == slow) {
        return true;
      }
      if (slow.next == null) {
        return false;
      } else {
        slow = slow.next;
      }
      if (fast.next == null || fast.next.next == null) {
        return false;
      } else {
        fast = fast.next.next;
      }
    }
    return false;
  }

  // Remove loop by using a set
  public static void removeLoop2Pointers(Node head) {
    if (!containsLoop(head)) {
      return;
    }
    Node fast, slow;
    fast = slow = head;
    // when fast == slow => they are at the same node in the loop
    while (true) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        break;
      }
    }
    // stop fast, advance slow to count how many nodes in the loop
    int count = 0;
    do {
      slow = slow.next;
      count++;
    } while (slow != fast);
    // now, position fast <count> positions after slow
    // and advance them with the same speed, they will meet at the first intersected node
    // if we go back one step (i.e. by compare 2 next values instead of the nodes)
    // then, fast is located at the last node
    fast = slow = head;
    for (int i = 0; i < count; i++) {
      fast = fast.next;
    }
    while (slow.next != fast.next) {
      slow = slow.next;
      fast = fast.next;
    }
    // remove the link
    fast.next = null;
  }

  // Remove loop by using a set
  public static void removeLoopWithSet(Node head) {
    if (!containsLoop(head)) {
      return;
    }
    HashSet<Node> visited = new HashSet<>();
    visited.add(head);
    while (!visited.contains(head.next)) {
      visited.add(head.next);
      head = head.next;
    }
    head.next = null;
  }

  // create a basic Node
  static class Node {
    int value;
    Node next;

    public Node(int v) {
      value = v;
      next = null;
    }

    // override hashCode() and equals() to compare objects
    @Override
    public int hashCode() {
      return 1;
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Node)) return false;
      return value == ((Node)other).value;
    }
  }
}
