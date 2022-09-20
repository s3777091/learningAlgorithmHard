public class Test2CovidVariant {
  public static void main(String[] args) {
    Test2CovidVariant v1 = new Test2CovidVariant("DELTA", "HELLOFROMDELTA");
    System.out.println(v1.evenSequence());  // return true
    System.out.println(v1.isSubsequence("HLOFRDLA")); // return true
    Test2CovidVariant v2 = new Test2CovidVariant("BETA", "HELLOFROMBETA");
    System.out.println(v2.evenSequence()); // return false
    System.out.println(v2.isSubsequence("HLOFRDLA")); // return false
    System.out.println(v1.longestCommonSubsequence(v2));  // return 12, the longest common subsequence is "HELLOFROMETA"
  }

  String code;
  String sequence;

  public Test2CovidVariant(String c, String s) {
    code = c;
    sequence = s;
  }

  public boolean evenSequence() {
    return (sequence.length() % 2 == 0);
  }

  public boolean isSubsequence(String s) {
    int start = 0;
    int length = sequence.length();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      while(start < length && sequence.charAt(start) != c) {
        start++;
      }
      if (start == length) {
        return false;
      }
    }
    return true;
  }

  public int longestCommonSubsequence(Test2CovidVariant other) {
    String sequence2 = other.sequence;
    int[][] longest = new int[sequence.length() + 1][sequence2.length() + 1];
    for (int i = 1; i <= sequence.length(); i++) {
      for (int j = 1; j <= sequence2.length(); j++) {
        if (sequence.charAt(i - 1) == sequence2.charAt(j - 1)) {
          longest[i][j] = longest[i - 1][j - 1] + 1;
        } else {
          longest[i][j] = Math.max(longest[i - 1][j], longest[i][j - 1]);
        }
      }
    }
    return longest[sequence.length()][sequence2.length()];
  }
}
