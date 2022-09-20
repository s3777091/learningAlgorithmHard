public class W8P2_BruteForceStringMatching {
  public static void main(String[] args) {
    System.out.println(bruceforceStringMatching("ABC", "B"));  // 1
    System.out.println(bruceforceStringMatching("ABC", "D"));  // -1
    System.out.println(bruceforceStringMatching("Hello", "He"));  // 0
    System.out.println(bruceforceStringMatching("Hello", "lo"));  // 3
    System.out.println(bruceforceStringMatching("Hello", "hello"));  // -1
    System.out.println(bruceforceStringMatching("AABBCC", "ABC"));  // -1
    System.out.println(bruceforceStringMatching("AABBCC", "AABBCCD"));  // -1
  }

  public static int bruceforceStringMatching(String search, String pattern) {
    int sLength = search.length();
    int pLength = pattern.length();
    for (int start = 0; start < sLength - pLength + 1; start++) {
      int count = 0;
      while (count < pLength) {
        if (search.charAt(start + count) != pattern.charAt(count)) {
          break;
        }
        count++;
      }
      if (count == pLength) {
        return start;
      }
    }
    return -1;
  }
}
