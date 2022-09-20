import java.util.ArrayList;

public class Test2MovieOrdering {
  public static void main(String[] args) {
    Test2Movie a = new Test2Movie("Squid Game", "Thriller", 7.6, 120);
    Test2Movie b = new Test2Movie("Spider-Man", "Action", 8.5, 110);
    Test2Movie c = new Test2Movie("The Matrix Resurrections", "Action", 6.2, 140);
    Test2MovieOrdering mo = new Test2MovieOrdering();
    mo.addMovie(a);
    mo.addMovie(b);
    mo.addMovie(c);
    System.out.println(mo.currentJoyfulness()); // return 230
    System.out.println(mo.maxJoyfulness()); // return 370
  }

  static final int MAX_SIZE = 9;
  Test2Movie[] movies;
  int size = 0;
  int maxJoyful = 0;

  public Test2MovieOrdering() {
    movies = new Test2Movie[MAX_SIZE];
  }

  public void addMovie(Test2Movie m) {
    movies[size] = m;
    size++;
  }

  private int calcJoyfulness(ArrayList<Test2Movie> m) {
    int res = 0;
    Test2Movie previous = null;
    for (int i = 0; i < m.size(); i++) {
      Test2Movie current = m.get(i);
      if (i == 0) {
        // first movie
        res += current.duration;
      } else {
        if (!previous.genre.equals(current.genre) &&
             previous.rating < current.rating) {
          res += current.duration;
        }
      }
      previous = current;
    }
    return res;
  }

  public int currentJoyfulness() {
    ArrayList<Test2Movie> m = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      m.add(movies[i]);
    }
    return calcJoyfulness(m);
  }

  public int maxJoyfulness() {
    maxJoyful = 0;  // reset
    buildPermutation(new ArrayList<Test2Movie>(), new boolean[size], 0);
    return maxJoyful;
  }

  private void buildPermutation(ArrayList<Test2Movie> current, boolean[] used, int idx) {
    if (idx == size) {
      // finish the last element, calculate joyfullness and update max
      maxJoyful = Math.max(maxJoyful, calcJoyfulness(current));
      return;
    }
    for (int i = 0; i < size; i++) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      current.add(movies[i]);
      buildPermutation(current, used, idx + 1);
      used[i] = false;
      current.remove(movies[i]);
    }
  }
}

class Test2Movie {
  String title;
  String genre;
  double rating;
  int duration;

  public Test2Movie(String t, String g, double r, int d) {
    title = t;
    genre = g;
    rating = r;
    duration = d;
  }
}