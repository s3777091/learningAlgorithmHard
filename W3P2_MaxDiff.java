public class W3P2_MaxDiff {
  public static void main(String[] args) {
    int[] test1 = {1, 2, 6, 9, 10, 20};
    // lowDay = 0 and highDay = 5
    searchLowHigh(test1);
    
    int[] test2 = {20, 15, 12, 10, 8, 7};
    // lowDay = 0 and highDay = 0
    searchLowHigh(test2);

    int[] test3 = {14, 12, 70, 15, 95, 65, 22, 99, 8};
    // lowDay = 1 and highDay = 7
    searchLowHigh(test3);
  }

  // Return the index of lowDay and highDay
  public static int[] searchLowHigh(int[] array) {
    int[] res = new int[2];
    int lowDay, highDay, min, minIdx, maxDiff;
    lowDay = highDay = maxDiff = minIdx = 0;
    min = Integer.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
      if (array[i] - array[minIdx] > maxDiff) {
        lowDay = minIdx;
        highDay = i;
        maxDiff = array[i] - array[minIdx];
      }
      if (array[i] < min) {
        min = array[i];
        minIdx = i;
      }
    }
    res[0] = lowDay;
    res[1] = highDay;
    System.out.printf("lowDay = %d and highDay = %d\n", lowDay, highDay);
    return res;
  }
}
