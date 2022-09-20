import java.util.Arrays;

public class W7P1_CountingSort {
  static final int SIZE = 1000000;  // array size
  static final int RANGE = 1000000;  // value range - from zero

  // generate a random array
  public int[] generate() {
    int[] res = new int[SIZE];
    for (int i = 0; i < SIZE; i++) {
      res[i] = (int)(Math.random() * RANGE);
    }
    return res;
  }

  // our own sorting procedure
  public void countingSort(int[] arr) {
    long startTime = System.currentTimeMillis();
    
    int size = arr.length;
    int[] countArr = new int[RANGE];

    // counting step
    for (int i = 0; i < size; i++) {
      countArr[arr[i]]++;
    }

    // sorting step
    int idx = 0;
    for (int i = 0; i < RANGE; i++) {
      Arrays.fill(arr, idx, idx + countArr[i], i);
      idx += countArr[i];
    }

    long endTime = System.currentTimeMillis();
    long diff = endTime - startTime;
    System.out.printf("Counting sort: %d milli-seconds\n", diff);
  }


  // Java built-in sorting procedure
  public void builtinSort(int[] arr) {
    long startTime = System.currentTimeMillis();
    java.util.Arrays.sort(arr);
    long endTime = System.currentTimeMillis();
    long diff = endTime - startTime;
    System.out.printf("Java built-in sort: %d milli-seconds\n", diff);
  }

  public static void main(String[] args) {
    W7P1_CountingSort problem1 = new W7P1_CountingSort();
    int[] test1 = problem1.generate();
    // create an exact copy of test1 array to make the comparison fair
    int[] test2 = Arrays.copyOf(test1, test1.length);
    problem1.countingSort(test1);
    problem1.builtinSort(test2);
  }
}
