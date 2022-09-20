import java.util.Arrays;
import java.util.Stack;

public class W11P1_LongestIncreasingSubsequence {
  public static void main(String[] args) {
    int[] test = {5, 2, 3, 9, 6, 7, 8};
    int[] res = longestIncreasingSubsequence(test);
    print(res);
  }

  static int[] longestIncreasingSubsequence(int[] arr) {
    int n = arr.length;

    int[] maxCount = new int[n];
    // maxCount[i] stores the length of the longest increasing subsequence ending at i
    Arrays.fill(maxCount, 1);

    int[] prevElement = new int[n];
    // prevElement[i] stores the index of the previous element of the
    // longest increasing subsequence that ends at element i
    // use this array to trace back to the beginning element
    Arrays.fill(prevElement, -1);

    // calculate the longest increasing subsequence for all ending elements
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j]) {
          // we can expand the subsequence ends at j-th
          // to include the element i-th
          if (maxCount[i] < maxCount[j] + 1) {
            // if the new subsequence is longer than the current one?
            maxCount[i] = maxCount[j] + 1;
            prevElement[i] = j;
          }
        }
      }
    }

    // get the longest subsequence
    // the longest subsequence has to end at one of the element 0 -> (n-1)
    int maxEnding = 0;
    for (int i = 1; i < arr.length; i++) {
      if (maxCount[maxEnding] < maxCount[i]) {
        maxEnding = i;
      }
    }

    // Go backward from maxEnding index to the first element
    Stack<Integer> elements = new Stack<Integer>();
    do {
      // in this implementation, the values of the elements in the
      // longest increasing subsequence are returned
      // you can change the assignment below to return the indices
      // of the elements instead, i.e., elements.push(maxEnding);
      elements.push(arr[maxEnding]);
      maxEnding = prevElement[maxEnding];
    } while (maxEnding != -1);

    // prepare result
    int[] res = new int[elements.size()];
    int idx = 0;
    while (!elements.isEmpty()) {
      res[idx] = elements.pop();
      idx++;
    }
    return res;
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
