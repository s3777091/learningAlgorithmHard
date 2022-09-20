import java.util.*;

public class W9P1_MergeSort_QuickSort {
  static final int SIZE = (int)1e5;
  static final int RANGE = (int)1e9;

  public static void main(String[] args) {
    int[] test = generate();
    System.out.println("Original array");
    // print(test);
    // mergeSort(test);
    quickSort(test, 0, test.length - 1);
    // System.out.println("After merge sort");
    System.out.println("After quick sort");
    // print(test);
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

  // sort with mergesort
  static void mergeSort(int arr[]) {
    if (arr.length > 1) {
      int n = arr.length;
      int middle = n / 2;

      // Create 2 sub-arrays from arr
      int[] sub1 = copyArr(arr, 0, middle - 1);
      int[] sub2 = copyArr(arr, middle, n - 1);

      // Sort first and second halves
      mergeSort(sub1);
      mergeSort(sub2);

      // Merge sub1 and sub2 into the original array
      merge(sub1, sub2, arr);
    }
  }

  // Create a new array based on the source array and start + end indices
  static int[] copyArr(int[] source, int startIdx, int endIdx) {
    int[] res = new int[endIdx - startIdx + 1];
    for (int i = 0; i < res.length; i++) {
      res[i] = source[startIdx + i];
    }
    return res;
  }

  // Utility function to merge two sub-arrays sub1 and sub2 into the array dest
  static void merge(int[] sub1, int[] sub2, int[] dest) {
    int p1 = 0, p2 = 0, pDest = 0;  // pointers to 3 arrays
    while (p1 < sub1.length && p2 < sub2.length) {
      if (sub1[p1] <= sub2[p2]) {
        dest[pDest] = sub1[p1];
        p1++;
      } else {
        dest[pDest] = sub2[p2];
        p2++;
      }
      pDest++;
    }

    // Copy remaining elements, if any
    while (p1 < sub1.length) {
      dest[pDest++] = sub1[p1++];
    }
    while (p2 < sub2.length) {
      dest[pDest++] = sub2[p2++];
    }
  }

  // sort with quick sort
  static void quickSort(int arr[], int left, int right) {
    if (left < right) {
      int p = partition(arr, left, right);
      quickSort(arr, left, p);
      quickSort(arr, p + 1, right);
    }
  }

  // Hoare partition
  // Return a partition point p
  // Where all elements arr[left -> p] <= all elements arr[p + 1, right]
  // Ref: https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
  static int partition(int arr[], int left, int right) {
    int p = arr[left];  // select the left-most element as pivot
    int front = left;
    int back = right;
    while (true) {
      while (arr[front] < p) {
        front++;
      }
      while (arr[back] > p) {
        back--;
      }
      if (front >= back) {
        return back;
      }
      // swapping
      int t = arr[front];
      arr[front] = arr[back];
      arr[back] = t;
      front++;
      back--;
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
