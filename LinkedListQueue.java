// LinkedList implementation of queue
public class LinkedListQueue<T> {
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

  public LinkedListQueue() {
    size = 0;
    head = null;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean enQueue(T item) {
    Node<T> n = new Node<T>(item);
    n.next = head;
    head = n;
    size++;
    return true;
  }

  public boolean deQqueue() {
    if (isEmpty()) {
      return false;
    }
    if (size == 1) {
      head = null;
      size = 0;
      return true;
    }
    Node<T> prev = head;
    Node<T> n = prev.next;
    while (n.next != null) {
      prev = n;
      n = n.next;
    }
    prev.next = null;
    size--;
    return true;
  }

  public T peekFront() {
    if (isEmpty()) {
      return null;
    }
    Node<T> n = head;
    while (n.next != null) {
      n = n.next;
    }
    return n.data;
  }

  public static void main(String[] args) {
    LinkedListQueue<String> queue = new LinkedListQueue<String>();
    queue.enQueue("hello");
    queue.enQueue("world");
    queue.enQueue("from");
    queue.enQueue("RMIT");
    while (!queue.isEmpty()) {
      System.out.println(queue.peekFront());
      queue.deQqueue();
    }
  }
}
