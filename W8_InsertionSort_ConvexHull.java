import java.awt.geom.*;
import java.util.*;

public class W8_InsertionSort_ConvexHull {
  public static void main(String[] args) {
    // insertionSort();
    convexHull();
  }

  static void printArr(int[] arr) {
    StringBuilder sb = new StringBuilder("[");
    boolean first = true;
    for (int n : arr) {
      if (first) {
        sb.append(n);
        first = false;
      } else {
        sb.append(", " + n);
      }      
    }
    sb.append("]");
    System.out.println(sb);
  }

  public static void insertionSort() {
    int[] arr = {8, 2, 1, 9, 7, 3, 4};
    printArr(arr);
    for (int i = 1; i < arr.length; i++) {
      // find the proper place for arr[i]
      int value = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > value) {
        // move arr[j] one step to the right
        arr[j + 1] = arr[j];
        j--;
      }
      // either j < 0 OR arr[j] < value (or both)
      arr[j + 1] = value;
      // display current state of arr
      printArr(arr);
    }
  }

  public static void convexHull() {
    // The first 4 points are the boundary
    double[] px = {0.0, 0.0, 10.0, 10.0, 3.3, 5.4, 6.8, 9.5};
    double[] py = {0.0, 10.0, 10.0, 0.0, 9.1, 2.7, 9.1, 3.7};

    int n = px.length;

    ArrayList<Line2D> convexHull = new ArrayList<>();

    // Search all pairs
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        Line2D line = new Line2D.Double(px[i], py[i], px[j], py[j]);
        // If all other points lie in one side of this line => choose it
        int sign = 0;
        boolean oneSide = true;
        for (int k = 0; k < n; k++) {
          if (k == i || k == j) {
            continue;
          }
          if (sign == 0) {
            // first point, do not care about the sign
            sign = line.relativeCCW(px[k], py[k]);
          } else {
            if (sign * line.relativeCCW(px[k], py[k]) < 0) {
              oneSide = false;
              break;
            }
          }
        }
        if (oneSide) {
          // points i and j are OK
          convexHull.add(line);
        }
      }
    }

    // Display convex hull lines
    for (Line2D line : convexHull) {
      System.out.printf("\nLine coordinates: (%.2f, %.2f) - (%.2f, %.2f)",
        line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
  }
}