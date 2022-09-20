// Insertion sort

public class W10P1_BinarySearchInsertionSort {
  public static void main(String[] args) {
    int[] test = {5, 4, 3, 2, 1};
    print(test);
    insertionSort(test);
  }

  static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int v = arr[i];
      // find the correct location of v
      int idx = getLocation(arr, i - 1, v);
      // shift all elements from idx to (i - 1) one step to the right
      for (int j = i - 1; j >= idx; j--) {
        arr[j + 1] = arr[j];
      }
      arr[idx] = v;
      print(arr);
    }
  }

  static int getLocation(int[] arr, int right, int value) {
    int left = 0;
    while (right > left) {
      int mid = (left + right) / 2;
      if (arr[mid] <= value) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    if (arr[left] > value) {
      return left;
    }
    return left + 1;
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
