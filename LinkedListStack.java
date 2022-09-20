// LinkedList implementation of stack
public class LinkedListStack<T> {
  // this class is used as a container to data 
  static class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private int size;
  private Node<T> head;

  public LinkedListStack() {
    size = 0;
    head = null;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean push(T item) {
    Node<T> n = new Node<T>(item);
    if (!isEmpty()) {
      n.next = head;
    }
    head = n;
    size++;
    return true;
  }

  public boolean pop() {
    // make sure the stack is not empty
    if (isEmpty()) {
      return false;
    }
    // advance head
    head = head.next;
    size--;
    return true;
  }

  public T peek() {
    // make sure the stack is not empty
    if (isEmpty()) {
      return null;
    }
    return head.data;
  }

  public static void main(String[] args) {
    LinkedListStack<String> list = new LinkedListStack<String>();
    list.push("hello");
    list.push("world");
    System.out.println(list.peek());
    list.pop();
    list.push("from RMIT");
    System.out.println(list.peek());
    list.pop();
    System.out.println(list.peek());
    list.pop();
    System.out.println(list.peek());
    list.pop();
  }
}

class StackApplication {
  static String infix2Postfix(String expr) {
    StringBuilder sb = new StringBuilder();
    LinkedListStack<Character> operatorStack = new LinkedListStack<Character>();
    for (int i = 0; i < expr.length(); i++) {
      char c = expr.charAt(i);
      if (c == '(') {
        operatorStack.push(c);
        continue;
      }
      if (c == ')') {
        char op;
        op = operatorStack.peek();
        while (op != '(') {
          sb.append(op);
          operatorStack.pop();
          op = operatorStack.peek();
        }
        operatorStack.pop();
        continue;
      }
      if (c == '*' || c == '/') {
        char op;
        if (!operatorStack.isEmpty()) {
          op = operatorStack.peek();
          while (op == '*' || op == '/') {
            sb.append(op);
            operatorStack.pop();
            if (operatorStack.isEmpty()) {
              break;
            }
            op = operatorStack.peek();
          }
        }
        operatorStack.push(c);
        continue;
      }
      if (c == '+' || c == '-') {
        char op;
        if (!operatorStack.isEmpty()) {
          op = operatorStack.peek();
          while (op != '(') {
            sb.append(op);
            operatorStack.pop();
            if (operatorStack.isEmpty()) {
              break;
            }
            op = operatorStack.peek();
          }
        }
        operatorStack.push(c);
        continue;
      }
      sb.append(c);
    }
    while (!operatorStack.isEmpty()) {
      char op = operatorStack.peek();
      sb.append(op);
      operatorStack.pop();
    }
    return sb.toString();
  }  

  public static void main(String[] args) {
    System.out.println("a+b: " + infix2Postfix("a+b"));
    System.out.println("a+b*c: " + infix2Postfix("a+b*c"));
    System.out.println("a/b+c*d: " + infix2Postfix("a/b+c*d"));
    System.out.println("a*(b+c-(d+e)/f)*g: " + infix2Postfix("a*(b+c-(d+e)/f)*g"));
  }
}
