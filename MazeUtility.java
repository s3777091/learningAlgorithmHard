public class MazeUtility {
  public static void displayMaze(char[][] maze){
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        System.out.print(maze[i][j]);
      }
      System.out.println();
    }
  }

  public static int countEnd(char[][] maze){
    int count = 0;
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        if (maze[i][j] == 'E'){
          count++;
        }
      }
    }
    return count;
  }

  public static boolean solvable(char[][] maze) {
    int rows = maze.length;
    int cols = maze[0].length;

    // find start location
    int startRow, startCol;
    startRow = startCol = 0;

    outFor:
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (maze[i][j] == 'S') {
          startRow = i;
          startCol = j;
          break outFor;
        }
      }
    }

    // maintain visisted cells
    boolean[][] visisted = new boolean[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        visisted[i][j] = false;
      }
    }

    boolean reachable = traverse(startRow, startCol, maze, visisted);

    return reachable;
  }

  private static boolean traverse(int r, int c, char[][] maze, boolean[][] visited) {
    if (maze[r][c] == 'E') {
      return true;
    }
    visited[r][c] = true;

    // try to reach the adjacent cells
    boolean res = false;

    int[] rowDirections = {0, 0, -1, 1};
    int[] colDirections = {1, -1, 0, 0};

    for (int i = 0; i < rowDirections.length; i++) {
      int newRow = r + rowDirections[i];
      int newCol = c + colDirections[i];
      if (newRow >= 0 && newRow < maze.length &&
          newCol >= 0 && newCol < maze[0].length &&
          maze[newRow][newCol] != '*' && 
          !visited[newRow][newCol]) {
        res = res || traverse(newRow, newCol, maze, visited);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    // not solvable
    char[][] maze1 = {
      {'.', '.', '.', '.'},
      {'*', 'S', '*', '*'},
      {'.', '*', '.', 'E'},
    };

    displayMaze(maze1);
    System.out.println(countEnd(maze1));
    System.out.println(solvable(maze1));

    // solvable
    char[][] maze2 = {
      {'S', '.', '*', '.'},
      {'*', '.', '*', '*'},
      {'E', '.', '*', 'E'},
    };

    displayMaze(maze2);
    System.out.println(countEnd(maze2));
    System.out.println(solvable(maze2));
  }
}
