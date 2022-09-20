import java.util.Arrays;

public class W11P2_ShortestPath {
  public static void main(String[] args) {
    int[][] distances = {
      {0, 3, 2, -1},
      {3, 0, -1, 5},
      {2, -1, 0, 9},
      {-1, 5, 9, 0}
    };
    System.out.println(shortestPath(distances, 0, 3));
  }

  static int shortestPath(int[][] nodes, int src, int dest) {
    // adaptation of Dijkstra's shortest path algorithm
    int n = nodes.length;
    
    int[] distance = new int[n];
    // distance[i] stores the minimum distance from src to i

    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[src] = 0;  // zero distance from the src to itself

    boolean[] visited = new boolean[n];
    visited[src] = true;

    int current = src;
    while (true) {
      // update the shortest distance through current node to all un-visited nodes
      for (int i = 0; i < n; i++) {
        if (visited[i]) {
          continue;
        }
        if (nodes[current][i] > 0) {
          // current and i are connected
          if (distance[i] > distance[current] + nodes[current][i]) {
            // going through current make the distance shorter
            distance[i] = distance[current] + nodes[current][i];
          }
        }
      }
      // use the shortest distance node as the new current
      // you can use a priority queue here to improve the search
      int shortest = Integer.MAX_VALUE;
      for (int i = 0; i < n; i++) {
        if (visited[i]) {
          continue;
        }
        if (shortest > distance[i]) {
          shortest = distance[i];
          current = i;
        }
      }
      if (current == dest) {
        // we reach the destination
        return distance[dest];
      }
      if (shortest == Integer.MAX_VALUE) {
        // we cannot go further
        // you can raise an exception and/or display an error message here instead
        return Integer.MAX_VALUE;
      }
      // continue the next round
      visited[current] = true;
    }
  }
}
