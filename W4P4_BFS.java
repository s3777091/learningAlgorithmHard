import java.util.HashSet;
import java.util.ArrayList;

public class W4P4_BFS {
  public static void main(String[] args) {
    bfs(19);
  }

  public static void bfs(int N) {
    HashSet<State> visited = new HashSet<State>();
    State firstState = new State(0, 0, "Initial state");
    visited.add(firstState);
    boolean found = false;  // found a path
    ArrayQueue<StateNode> queue = new ArrayQueue<StateNode>();
    queue.enQueue(new StateNode(firstState, null));

    while (!queue.isEmpty()) {
      StateNode node = queue.peekFront();
      queue.deQueue();
      State current = node.state;
      
      if (current.x == N || current.y == N) {
        printSteps(node);
        found = true;
        break;
      }
      for (State nextState : current.generateStates()) {
        if (!visited.contains(nextState)) {
          visited.add(nextState);
          queue.enQueue(new StateNode(nextState, node));
        }
      }      
    }
    if (!found) {
      System.out.printf("Cannot get %d litters of water from %d and %d jugs\n", N, State.X, State.Y);
    }
  }

  static void printSteps(StateNode node) {
    StringBuilder res = new StringBuilder();
    // Build the steps backward
    while (node != null) {
      State state = node.state;
      res.insert(0, state.x + " " + state.y + ": " + state.desc +"\n");
      node = node.parent;
    }
    System.out.println(res.toString());
  }
}

class State {
  // maximum amount
  static int X = 16;
  static int Y = 23;

  // current amount
  int x, y;

  // how to reach this state (optional)
  String desc;

  public State(int xx, int yy) {
    new State(xx, yy, "");
  }

  public State(int xx, int yy, String s) {
    x = xx;
    y = yy;
    desc = s;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof State)) return false;
    State s2 = (State)o;
    return (x == s2.x && y == s2.y);
  }

  public ArrayList<State> generateStates() {
    ArrayList<State> states = new ArrayList<State>();
    // fill first jug
    if (x != X) states.add(new State(X, y, "Fill first jug"));
    // fill second jug
    if (y != Y) states.add(new State(x, Y, "Fill second jug"));
    // pour out first jug
    if (x != 0) states.add(new State(0, y, "Pour out first jug"));
    // pour out second jug
    if (y != 0) states.add(new State(x, 0, "Pour out second jug"));
    // pour out from first to second jug
    if (x > 0 && y != Y) states.add(new State(Math.max(0, x + y - Y), Math.min(Y, x + y), "Pour from first jug to second jug"));
    // pour out from scond to first jug
    if (x != X && y > 0) states.add(new State(Math.min(X, x + y), Math.max(0, x + y - X), "Pour from second jug to first jug"));
    return states;
  }
}

class StateNode {
  State state;
  StateNode parent;

  public StateNode(State s, StateNode p) {
    state = s;
    parent = p;
  }
}
