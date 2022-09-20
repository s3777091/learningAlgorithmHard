public class W3P3_OldestNewest {
  public static void main(String[] args) {
    int[] test1 = {1, 2, 3, 4, 5, 6, 7};
    // oldest = 0 and newest = 6
    searchOldNew(test1);
    
    int[] test2 = {113, 115, 117, 118, 10, 11, 12, 13, 20, 23, 27, 100, 103, 105, 108};
    // oldest = 4 and newest = 3
    searchOldNew(test2);
  }

  // Display the indices of oldest and newest elements
  public static void searchOldNew(int[] arr) {
    int left, right;
    left = 0;
    right = arr.length - 1;
    while (left < right && arr[left] > arr[right]) {
      int mid = (left + right) / 2;
      if (arr[mid] > arr[mid + 1]) {
        System.out.printf("Oldest index = %d and Newest index = %d\n", mid + 1, mid);
        return;
      }
      if (arr[left] < arr[mid]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    System.out.printf("Oldest index = %d and Newest index = %d\n", left, right);
  }
}
