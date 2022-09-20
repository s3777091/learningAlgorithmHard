import java.util.Random;
import java.util.Scanner;

public class W9P3_BinarySearch {
	public static void main(String[] args) {
    int range = 1000;
    startGame(range);
  }

	static void startGame(int range) {
    boolean correct = false;
    int guessedNumber = 0;
    int guessCount = 0;
    int low = 0, high = range;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to guessing game - Think of one number from 0 - " + range);
    System.out.println("-------------------------------------------------------------");
    while (!correct) {
      guessedNumber = guessNumber(low, high);
      guessCount++;
      System.out.print("Is this number correct: " + guessedNumber + "? (Y/N): ");
      String answer = scanner.next();
      if (answer.toLowerCase().equals("y")) {
        correct = true;
        break;
      } else {
        System.out.print("Is this number too small or too big? (S/B): ");
        String nextAns = scanner.next();
        if (nextAns.toLowerCase().equals("s")) {
          low = guessedNumber + 1;
        } else {
          high = guessedNumber - 1;
        }
      }
    }
    scanner.close();
    System.out.println("The number is: " + guessedNumber);
    System.out.println("Number of guesses: " + guessCount);
  }
	 
  // Guess a number using binary approach (with some trick to make the program more real)
  static int guessNumber(int low, int high) {
    Random rnd = new Random();
    int signed = rnd.nextInt(2) - 1;  // -1, 0, 1
    int width = (high - low) / 10;  // at most 10% difference
    int diff = 0;
    if (width > 0) {
      diff = signed * rnd.nextInt(width);
    }
    int guessNumber = (low + high) / 2 + diff;
    if (guessNumber < low) return low;
    if (guessNumber > high) return high;
    return guessNumber;
  }
}
