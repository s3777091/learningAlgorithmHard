import java.util.Arrays;

public class W11P3_MinimumSpanningTree {
  public static void main(String[] args) {
    int[][] castles = {
      {0, 1, 2, 8},
      {1, 0, 3, 5},
      {2, 3, 0, 4},
      {8, 5, 4, 0}
    };
    System.out.println(minimumSpanningTree(castles));
  }

  static int minimumSpanningTree(int[][] nodes) {
    // adaptation of Prim's algorithm
    int n = nodes.length;
    int length = 0;

    // use this array to mark nodes have been added already
    // you can use 2 sets: added and not-added to improve performance
    boolean[] added = new boolean[n];

    // distance between the tree being built with not-added nodes
    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);

    // insert node zero (any node is OK) first, so set its distance to zero
    distances[0] = 0;
    
    // stop when the number of added nodes = n
    int nodeCount = 0;
    while (nodeCount < n) {
      int shortest = Integer.MAX_VALUE;
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

      if (shortest == Integer.MAX_VALUE) {
        // we cannot go further - the grap is not connected
        // you can raise an exception and/or display an error message here instead
        return Integer.MAX_VALUE;
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
        if (nodes[shortestNode][i] <= 0) {
          // this node has no direct connection with shortestNode
          continue;
        }
        if (distances[i] > nodes[shortestNode][i]) {
          distances[i] = nodes[shortestNode][i];
        }
      }
    }
    return length;
  }
}
