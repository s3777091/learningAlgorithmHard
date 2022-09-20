import java.util.Random;

public class W8P1_SelectionSort_BubbleSort {
  static final int SIZE = 10;
  static final int RANGE = 100;

  public static void main(String[] args) {
    // int[] test1 = generate();
    int[] test1 = {5, 1, 2, 3, 4};
    // selectionSort(test1);
    // int[] test2 = generate();
    // int[] test2 = {1, 3, 6, 7, 9, 10, 12};
    bubbleSort(test1);
  }
  
  // generate a random array
  public static int[] generate() {
    Random rnd = new Random();
    int[] res = new int[SIZE];
    for (int i = 0; i < SIZE; i++) {
      res[i] = rnd.nextInt(RANGE);
    }
    return res;
  }

  public static void bubbleSort(int[] arr) {
    System.out.println("Bubble Sort: ");
    print(arr);
    // idx: index of the last element in each iteration
    for (int idx = arr.length - 1; idx > 0; idx--) {
      boolean swap = false;
      for (int idx2 = 0; idx2 < idx; idx2++) {
        // out of order?
        if (arr[idx2] > arr[idx2 + 1]) {
          int temp = arr[idx2];
          arr[idx2] = arr[idx2 + 1];
          arr[idx2 + 1] = temp;
          swap = true;
        }
      }
      // Display current state
      print(arr);
      if (!swap) {
        break;
      }
    }
  }

  public static void selectionSort(int[] arr) {
    System.out.println("Selection Sort: ");
    print(arr);
    for (int idx = 0; idx < arr.length - 1; idx++) {
      // Find the minimum value from idx till the end
      int minIdx = idx;
      for (int idx2 = idx + 1; idx2 < arr.length; idx2++) {
        if (arr[idx2] < arr[minIdx]) {
          minIdx = idx2;
        }
      }
      // Swap the element at idx with the remaining minimum
      int temp = arr[idx];
      arr[idx] = arr[minIdx];
      arr[minIdx] = temp;
      // Display current state
      print(arr);
    }
  }

  private static void print(int[] arr) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (int n : arr) {
      if (!first) {
        sb.append(", " + n);
      } else {
        sb.append(n);
        first = false;
      }
    }
    System.out.println(sb);
  }
}
