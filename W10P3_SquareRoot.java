public class W10P3_SquareRoot {
  public static void main(String[] args) {
    squareroot(2);
  }

  static double squareroot(double v) {
    double min = 1;
    double max = v;
    double eps = 0.0000001;
    while (max - min > eps) {
      double mid = (min + max) / 2;
      if (mid * mid > v) {
        max = mid;
      } else {
        min = mid;
      }
    }
    System.out.println(min);
    return min;
  }  
}
