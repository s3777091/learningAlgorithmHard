// Array implementation of queue
public class ArrayQueue<T> {
  private int size;
  private static int MAX_SIZE = 100;
  private T[] items;

  public ArrayQueue() {
    size = 0;
    items = (T[])new Object[MAX_SIZE];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean enQueue(T item) {
    // make sure the queue still have empty slot
    if (size < MAX_SIZE) {
      items[size] = item;
      size++;
      return true;
    }
    return false;
  }

  public boolean deQueue() {
    // make sure the queue is not empty
    if (isEmpty()) {
      return false;
    }
    // shift left
    for (int i = 0; i < size - 1; i++) {
      items[i] = items[i + 1];
    }
    size--;
    items[size] = null;  // is this assignment necessary?
    return true;
  }

  public T peekFront() {
    // make sure the queue is not empty
    if (isEmpty()) {
      return null;
    }
    return items[0];
  }

  public static void main(String[] args) {
    ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
    queue.enQueue(20);
    queue.enQueue(33);
    System.out.println(queue.peekFront());
    queue.deQueue();
    queue.enQueue(7);
    System.out.println(queue.peekFront());
    queue.deQueue();
    System.out.println(queue.peekFront());
    queue.deQueue();
    System.out.println(queue.peekFront());
    queue.deQueue();
  }
}

class QueueApplication {
  static class Event {
    int arrival;
    int duration;

    public Event(int a, int d) {
      arrival = a;
      duration = d;
    }
  }

  static void eventSimulation() {
    ArrayQueue<Event> events = new ArrayQueue<Event>();
    events.enQueue(new Event(0, 5));
    events.enQueue(new Event(3, 3));
    events.enQueue(new Event(4, 4));
    events.enQueue(new Event(100, 4));

    int n = events.size();

    int nextAvailableTime = 0;
    int total = 0;

    while (!events.isEmpty()) {
      Event evt = events.peekFront();
      events.deQueue();
      nextAvailableTime = Math.max(nextAvailableTime, evt.arrival);
      total += (nextAvailableTime - evt.arrival);
      nextAvailableTime = nextAvailableTime + evt.duration;
    }

    System.out.printf("Total waiting time %d and average waiting time %.2f\n", total, 1.0 * total / n);
  }

  public static void main(String[] args) {
    eventSimulation();
  }
}