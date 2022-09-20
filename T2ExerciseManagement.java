import java.util.*;

public class T2ExerciseManagement {
  public static void main(String[] args) {
    FitnessExercise e1 = new FitnessExercise("Swimming", 100, 30);  // duration is given before fitness
    FitnessExercise e2 = new FitnessExercise("Football", 120, 45);
    FitnessExercise e3 = new FitnessExercise("Table tennis", 150, 60);
    T2ExerciseManagement mgmt = new T2ExerciseManagement();
    mgmt.add(e1);
    mgmt.add(e2);
    mgmt.add(e3);
    ArrayList<FitnessExercise> test1 = new ArrayList<FitnessExercise>();
    test1.add(e1);
    test1.add(e2);
    List<FitnessExercise> res1;
    System.out.println(mgmt.exercises(test1));  // return "Swimming, Football"
    
    res1 = mgmt.optimalExercises(120);  // You can do either Swimming or Football in 120 minutes, but doing Football gives you more fitness, so you should return a list containing Football
    for (int i = 0; i < res1.size(); i++) {
      System.out.println(res1.get(i).name);
    }

    res1 = mgmt.optimalExercises(250);  // Doing Swimming and Table Tennis brings you 90, which is the highest in 250 minutes, so you should return a list containing Swimming and Table Tennis
    for (int i = 0; i < res1.size(); i++) {
      System.out.println(res1.get(i).name);
    }
  }

  ArrayList<FitnessExercise> exs;

  public T2ExerciseManagement() {
    exs = new ArrayList<FitnessExercise>();
  }

  public void add(FitnessExercise ex) {
    exs.add(ex);
  }

  public String exercises(List<FitnessExercise> exercises) {
    String res = "";
    for (int i = 0; i < exs.size(); i++) {
      FitnessExercise e = exs.get(i);
      for (int j = 0; j < exercises.size(); j++) {
        if (e == exercises.get(j)) {
          res += ", " + e.name;
          break;
        }
      }
    }
    return res.substring(2);
  }

  public List<FitnessExercise> optimalExercises(int N) {
    int n = exs.size();
    int capacity = N;
    int[][] V = new int[n + 1][capacity + 1];

    // denote whether an item i of knapsack capacity j is selected or not
    // taken[i][j] == true if item i is selected, false otherwise
    boolean[][] taken = new boolean[n + 1][capacity + 1];

    // initialization
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= capacity; j++) {
        V[i][j] = 0;
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= capacity; j++) {
        if (exs.get(i - 1).duration > j) {
          // this item is too heavy to put in the knapsack of capacity j
          // so, the maximum value should NOT include this item
          V[i][j] = V[i - 1][j];
          continue;
        }
        // it is OK to put this item into the knapsack
        // but we should only do so if we can increase the total value
        if (V[i - 1][j - exs.get(i - 1).duration] + exs.get(i - 1).fitness > V[i - 1][j]) {
          V[i][j] = V[i - 1][j - exs.get(i - 1).duration] + exs.get(i - 1).fitness;
          taken[i][j] = true;
        } else {
          V[i][j] = V[i - 1][j];
        }
      }
    }

    // rebuild the solution
    ArrayList<FitnessExercise> res = new ArrayList<FitnessExercise>();
    int cap = capacity;
    int last = n;
    while (cap > 0 && last > 0) {
      if (taken[last][cap]) {
        // the last-th index item is taken
        res.add(exs.get(last - 1));
        cap -= exs.get(last - 1).duration;
      }
      last--;
    }
    return res;
  }
}

class FitnessExercise {
  String name;
  int duration;
  int fitness;

  public FitnessExercise(String n, int d, int f) {
    name = n;
    duration = d;
    fitness = f;
  }
}