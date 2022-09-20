public class W10P2_TopoSort {
  public static void main(String[] args) {
    int[] res = topoSort(new int[][]{
      {0, 0, 0, 0},
      {1, 0, 1, 0},
      {0, 0, 0, 1},
      {1, 0, 0, 0}
    });
    print(res);
  }

  static int[] topoSort(int[][] courses) {
    int n = courses.length;
    int[] res = new int[n];
    int loc = 0;
    while (loc < n) {
      boolean found = false;
      for (int r = 0; r < n; r++) {
        // check if all elements of the row r contains zero
        if (allZero(courses, r)) {
          // clear all dependencies of other courses to this course
          for (int r2 = 0; r2 < n; r2++) {
            if (r2 == r) {
              continue;
            }
            if (courses[r2][r] == 1) {
              courses[r2][r] = 0;
            }
          }
          res[loc] = r;
          loc++;
          courses[r][0] = -1;  // mark the row r so that it will not be used again
          found = true;
          break;
        }
      }
      if (!found) {
        System.out.println("Courses are mutually dependent");
        return new int[]{};
      }
    }
    return res;
  }

  static boolean allZero(int[][] mat, int r) {
    for (int i = 0; i < mat.length; i++) {
      if (mat[r][i] != 0) {
        return false;
      }
    }
    return true;
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
