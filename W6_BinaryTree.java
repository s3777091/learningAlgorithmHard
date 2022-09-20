/*
 * Note: I use the prefix W6 to prevent conflicts
 * between classes with the same name
 * and there is nothing special here
 */


public class W6_BinaryTree {
  public static void main(String[] args) {
    // Problem 1
    W6Tree tree = new W6Tree();
    for (int i = 0; i < 10; i++) {
      tree.insertBST((int)(Math.random() * 100));
    }
    tree.printTree();

    // Problem 2
    tree.startPreorder();
    tree.startInorder();
    tree.startPostorder();
    tree.breadthFirst();

    // Problem 3
    System.out.println("Tree height: " + tree.treeHeight());

    // Problem 4
    W6Tree tree2 = new W6Tree();
    for (int i = 0; i < 10; i++) {
      tree2.randomInsert((int)(Math.random() * 100));
    }
    tree2.printTree();
    System.out.println("Is BST, range check: " + tree2.isBST());
    System.out.println("Is BST, in-order traversal check: " + tree2.isBST2());

    // Test again with a real BST
    tree.printTree();
    System.out.println("Is BST, range check: " + tree.isBST());
    System.out.println("Is BST, in-order traversal check: " + tree.isBST2());
  }
}

class W6Node {
  int value;
  W6Node left, right;

  public W6Node(int v) {
    value = v;
    left = right = null;
  }
}

// Note: if you want to create a BST
// ALWAYS call insertBST() when inserting new nodes
// The insertRandom() is used to insert new nodes at random locations
// for Problem 4 (checking whether a tree is BST or not)
// I also add several methods to display a tree
// Do not worry about them

class W6Tree {
  W6Node root;

  // this variable is used in the in-oder traversal to check if a tree is a BS
  int lastValue;
  boolean increasing;

  // For display
  int rows = 10;
  int cols = 80;
  char[][] canvas = new char[rows][cols];

  public W6Tree() {
    root = null;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        canvas[i][j] = ' ';
      }
    }
  }

  // Problem 1
  public void insertBST(int v) {
    W6Node n = new W6Node(v);
    int step = 1;
    // Empty tree?
    if (root == null) {
      root = n;
      System.out.printf("Insert: %d, number of steps: %d\n", v, step);
      return;
    }
    // Find the appropriate location
    W6Node temp = root;
    while (true) {
      // duplication detected, abort
      if (temp.value == v) {
        System.out.printf("Insert: %d, number of steps: %d\n", v, step);
        return;
      }
      step++;
      if (temp.value > v) {
        if (temp.left == null) {
          temp.left = n;
          break;
        } else {
          temp = temp.left;
        }        
      } else {
        if (temp.right == null) {
          temp.right = n;
          break;
        } else {
          temp = temp.right;
        }        
      }
    }
    System.out.printf("Insert: %d, number of steps: %d\n", v, step);
  }

  // Problem 2
  public void startPreorder() {
    System.out.println("Pre-order traversal");
    preorder(root);
  }

  private void preorder(W6Node n) {
    if (n == null) {
      return;
    }
    System.out.println(n.value);
    preorder(n.left);
    preorder(n.right);
  }

  public void startInorder() {
    System.out.println("In-order traversal");
    inorder(root);
  }

  private void inorder(W6Node n) {
    if (n == null) {
      return;
    }
    inorder(n.left);
    System.out.println(n.value);
    inorder(n.right);
  }

  public void startPostorder() {
    System.out.println("Post-order traversal");
    postorder(root);
  }

  private void postorder(W6Node n) {
    if (n == null) {
      return;
    }
    postorder(n.left);    
    postorder(n.right);
    System.out.println(n.value);
  }

  public void breadthFirst() {
    System.out.println("Bread-first traversal");
    // Simulate a queue with an array
    W6Node[] queue = new W6Node[100];
    int head, tail;
    head = tail = 0;
    // endQueue
    queue[tail] = root;
    tail++;
    while (head < tail) {
      // deQueue
      W6Node n = queue[head];
      head++;
      System.out.println(n.value);
      if (n.left != null) {
        // endQueue left child
        queue[tail] = n.left;
        tail++;
      }
      if (n.right != null) {
        // endQueue right child
        queue[tail] = n.right;
        tail++;
      }
    }
  }

  // Problem 3
  public int treeHeight() {
    return nodeHeight(root);
  }

  private int nodeHeight(W6Node n) {
    if (n == null) {
      return 0;
    }
    return Math.max(nodeHeight(n.left), nodeHeight(n.right)) + 1;
  }

  // Problem 4
  public boolean isBST() {
    return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isBSTUtil(W6Node n, int min, int max) {
    if (n == null) {
      return true;
    }
    if (n.value < min || n.value > max) {
      return false;
    }
    // Check the subtrees recursively
    return isBSTUtil(n.left, min, n.value - 1) && isBSTUtil(n.right, n.value + 1, max);
  }

  public boolean isBST2() {
    // Another approach, using the in-oder traversal
    lastValue = Integer.MIN_VALUE;
    increasing = true;
    isBSTInorder(root);
    return increasing;
  }

  private void isBSTInorder(W6Node n) {
    if (n == null || !increasing) {
      return;
    }
    isBSTInorder(n.left);
    if (lastValue >= n.value) {
      increasing = false;
      return;
    } else {
      lastValue = n.value;
    }
    isBSTInorder(n.right);
  }
  
  // This is a helper method for Problem 4
  public void randomInsert(int v) {
    W6Node n = new W6Node(v);
    if (root == null) {
      root = n;
      return;
    }
    W6Node temp = root;
    while (true) {
      // Choose left or right randomly
      if (Math.random() < 0.5) {
        if (temp.left == null) {
          temp.left = n;
          return;
        }
        temp = temp.left;
      } else {
        if (temp.right == null) {
          temp.right = n;
          return;
        }
        temp = temp.right;
      }
    }
  }

  public void printTree() {
    fillCanvas(root, 0, 0, cols);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(canvas[i][j]);
      }
      // Two blank lines is better than one, right?
      System.out.println();
      System.out.println();
    }
  }

  private void fillCanvas(W6Node n, int r, int col_start, int col_width) {
    if (n == null) {
      return;
    }
    char[] v = String.valueOf(n.value).toCharArray();
    int start = col_start + col_width / 2 - v.length / 2;
    for (int i = 0; i < v.length; i++) {
      canvas[r][start + i] = v[i];
    }
    fillCanvas(n.left, r + 1, col_start, col_width / 2);
    fillCanvas(n.right, r + 1, col_start + col_width / 2, col_width / 2);
  }
}