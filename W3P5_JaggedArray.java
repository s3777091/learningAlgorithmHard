public class W3P5_JaggedArray {
  public static void main(String[] args) {
    int[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    createJaggedArray(test1);
    
    int[] test2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    createJaggedArray(test2);

    int[] test3 = {1, 5, 2, 9, 10, 13, 17, 21, 11};
    createJaggedArray(test3);
  }

  public static void createJaggedArray(int[] arr) {
    for (int i = 2; i < arr.length; i++) {
      if ((arr[i] - arr[i-1]) * (arr[i-1] - arr[i-2]) > 0) {
        // 2 positive differeces OR 2 negative differences
        int t = arr[i];
        arr[i] = arr[i-1];
        arr[i-1] = t;
      }
    }
    StringBuilder sb = new StringBuilder("{" + arr[0]);
    for (int i = 1; i < arr.length; i++) {
      sb.append(", " + arr[i]);
    }
    sb.append("}");
    System.out.println(sb.toString());
  }
}
