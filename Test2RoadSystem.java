import java.util.ArrayList;
import java.util.Arrays;

public class Test2RoadSystem {
  public static void main(String[] args) {
    Test2Lab l1 = new Test2Lab("Advanced AI", 0.0, 0.0);
    Test2Lab l2 = new Test2Lab("Cyber Security", 10.0, 0);
    Test2Lab l3 = new Test2Lab("IoT", 0, 10);
    Test2RoadSystem rs = new Test2RoadSystem();
    rs.addLab(l1);
    rs.addLab(l2);
    rs.addLab(l3);
    System.out.println(rs.simpleLength());  // return 24.142
    System.out.println(rs.optimalLength());  // return 20
  }

  ArrayList<Test2Lab> labs;

  public Test2RoadSystem() {
    labs = new ArrayList<>();
  }

  public void addLab(Test2Lab l) {
    labs.add(l);
  }

  public double simpleLength() {
    double res = 0;
    for (int i = 0; i < labs.size() - 1; i++) {
      res += length(labs.get(i), labs.get(i + 1));
    }
    return res;
  }

  public double optimalLength() {
    // Create a 2D matrix to reuse the sample code
    double[][] matrix = new double[labs.size()][labs.size()];
    for (int i = 0; i < labs.size(); i++) {
      for (int j = 0; j < labs.size(); j++) {
        matrix[i][j] = length(labs.get(i), labs.get(j));
      }
    }
    return minimumSpanningTree(matrix);
  }

  private double minimumSpanningTree(double[][] nodes) {
    // adaptation of Prim's algorithm
    int n = nodes.length;
    double length = 0;

    // use this array to mark nodes have been added already
    // you can use 2 sets: added and not-added to improve performance
    boolean[] added = new boolean[n];

    // distance between the tree being built with not-added nodes
    double[] distances = new double[n];
    Arrays.fill(distances, Double.MAX_VALUE);

    // insert node zero (any node is OK) first, so set its distance to zero
    distances[0] = 0;
    
    // stop when the number of added nodes = n
    int nodeCount = 0;
    while (nodeCount < n) {
      double shortest = Double.MAX_VALUE;
      int shortestNode = -1;

      // determine the shortest distance node to the tree
      // you can use a priority queue here to improve the search
      for (int i = 0; i < n; i++) {
        if (added[i]) {
          continue;
        }
        if (shortest > distances[i]) {
          shortest = distances[i];
          shortestNode = i;
        }
      }

      // add the shortest node to the tree
      added[shortestNode] = true;
      length += distances[shortestNode];
      nodeCount++;

      // update other distances to the tree, if any
      for (int i = 0; i < n; i++) {
        if (added[i]) {
          // this node has been added before
          continue;
        }
        if (distances[i] > nodes[shortestNode][i]) {
          distances[i] = nodes[shortestNode][i];
        }
      }
    }
    return length;
  }

  private double length(Test2Lab l1, Test2Lab l2) {
    return Math.sqrt((l1.x - l2.x) * (l1.x - l2.x) + (l1.y - l2.y) * (l1.y - l2.y));
  }
}

class Test2Lab {
  String name;
  double x;
  double y;

  public Test2Lab(String n, double x, double y) {
    name = n;
    this.x = x;
    this.y = y;
  }
}