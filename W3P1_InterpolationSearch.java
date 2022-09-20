import java.util.Random;

public class W3P1_InterpolationSearch {
  public static void main(String[] args) {
    // create an array of uniformly distributed values
    int size = 1000000;
    Random rnd = new Random();
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      // 10% difference from the perfect uniform distribution
      arr[i] = i * 50 + rnd.nextInt(5);
    }

    // search using both techniques
    search(arr, 500);
    binarySearch(arr, 500);
    
  }

  // values in array are uniformly distributed
  // return the index of the matched element
  // return -1 if there is no matched element
  public static int search(int[] array, int value) {
    int left = 0;
    int right = array.length - 1;
    if (array[left] > value || array[right] < value) {
      return -1;
    }
    int loopCount = 0;
    while (left < right) {
      loopCount++;
      // value range: max - min
      int vRange = array[right] - array[left];
      // index range: right - left (+ 1 or not is not important)
      int iRange = right - left;

      int idx = left + (value - array[left]) * iRange / vRange;
      if (array[idx] == value) {
        System.out.println("Interpolation Search Loops: " + loopCount);
        return idx;
      }
      if (array[idx] < value) {
        left = idx + 1;
      } else {
        right = idx - 1;
      }
    }
    System.out.println("Interpolation Search Loops: " + loopCount);
    if (array[left] == value) {
      return left;
    }
    return -1;
  }

  // the traditional binary search
  public static int binarySearch(int[] array, int value) {
    int left = 0;
    int right = array.length - 1;
    if (array[left] > value || array[right] < value) {
      return -1;
    }
    int loopCount = 0;
    while (left < right) {
      loopCount++;
      int idx = (left + right) / 2;

      if (array[idx] == value) {
        System.out.println("Binary Search loops: " + loopCount);
        return idx;
      }
      if (array[idx] < value) {
        left = idx + 1;
      } else {
        right = idx - 1;
      }
    }
    System.out.println("Binary Search loops: " + loopCount);
    if (array[left] == value) {
      return left;
    }
    return -1;
  }
}
