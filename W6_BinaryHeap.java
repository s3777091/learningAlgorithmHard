import java.util.*;

public class W6_BinaryHeap {
  public static void main(String[] args) {
    Heap<W6Integer> heap = new Heap<W6Integer>();
    heap.insert(new W6Integer(10));
    System.out.println(heap);
    heap.insert(new W6Integer(15));
    System.out.println(heap);
    heap.insert(new W6Integer(8));
    System.out.println(heap);
    heap.insert(new W6Integer(9));
    System.out.println(heap);
    heap.insert(new W6Integer(20));
    System.out.println(heap);
    heap.insert(new W6Integer(32));
    System.out.println(heap);

    System.out.println("=====Removing=====");
    while (heap.size() > 0) {
      System.out.println(heap.remove());
    }
  }
}

class Heap<T extends Comparable> {
  private int size;
  private static int MAX_SIZE = 100;
  private T[] items;

  public Heap() {
    size = 0;
    items = (T[])new Comparable[MAX_SIZE];
  }

  public int size() {
    return size;
  }

  // Heapify top-down
  private void heapifyDown(int idx) {
    int root = idx;
    int left = 2 * idx + 1;
    int right = 2 * idx + 2;

    if (left < size && items[left].compareTo(items[root]) > 0) {
      root = left;
    }

    if (right < size && items[right].compareTo(items[root]) > 0) {
      root = right;
    }

    // change is necessary?
    if (root != idx) {
      T tmp = items[idx];
      items[idx] = items[root];
      items[root] = tmp;
      heapifyDown(root);
    }
  }

  // Heapify bottom-up
  private void heapifyUp(int idx) {
    int parent = (idx - 1) /2;
    if (parent >= 0 && items[parent].compareTo(items[idx]) < 0) {
      T tmp = items[idx];
      items[idx] = items[parent];
      items[parent] = tmp;
      heapifyUp(parent);
    }
  }

  public void insert(T data) {
    items[size] = data;
    size++;
    heapifyUp(size - 1);
  }

  public T remove() {
    if (size == 0) {
      return null;
    }
    T tmp = items[0];
    items[0] = items[size - 1];
    size--;
    heapifyDown(0);
    return tmp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(items[i].toString() + " - ");
    }
    return sb.toString();
  }
}

class W6Integer implements Comparable {
  int value;

  public W6Integer(int v) {
    value = v;
  }

  @Override
  public int compareTo(Object o) {
    W6Integer other = (W6Integer)o;
    return value - other.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
