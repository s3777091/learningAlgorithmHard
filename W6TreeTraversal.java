public class W6TreeTraversal {
  public static void main(String[] args) {
    // The following binary tree will be created
    // Hello has children: World and Java
    // World has children: Real and Metaverse
    // Java has children: Oracle and OpenJDK
    BinaryTreeNode<W6Person> root = new BinaryTreeNode<W6Person>(new W6Person("Hello"));
    root.left = new BinaryTreeNode<W6Person>(new W6Person("World"));
    root.right = new BinaryTreeNode<W6Person>(new W6Person("Java"));
    root.left.left = new BinaryTreeNode<W6Person>(new W6Person("Real"));
    root.left.right = new BinaryTreeNode<W6Person>(new W6Person("Metaverse"));
    root.right.left = new BinaryTreeNode<W6Person>(new W6Person("Oracle"));
    root.right.right = new BinaryTreeNode<W6Person>(new W6Person("OpenJDK"));
    System.out.println("=====Pre-order traversal=====");
    preorderTraversal(root);
    System.out.println("\n=============================");
    System.out.println("=====Post-order traversal=====");
    postorderTraversal(root);
    System.out.println("\n=============================");
    System.out.println("=====In-order traversal=====");
    inorderTraversal(root);
    System.out.println("\n=============================");
  }

  static void preorderTraversal(BinaryTreeNode<W6Person> root) {
    // base case
    if (root == null) {
      return;
    }
    // process root
    System.out.print(root.data + " - ");

    // process left-subtree
    preorderTraversal(root.left);

    // process right-substree
    preorderTraversal(root.right);
  }

  static void postorderTraversal(BinaryTreeNode<W6Person> root) {
    // base case
    if (root == null) {
      return;
    }
    // process left-subtree
    postorderTraversal(root.left);

    // process right-substree
    postorderTraversal(root.right);

    // process root
    System.out.print(root.data + " - ");
  }

  static void inorderTraversal(BinaryTreeNode<W6Person> root) {
    // base case
    if (root == null) {
      return;
    }
    // process left-subtree
    inorderTraversal(root.left);

    // process root
    System.out.print(root.data + " - ");

    // process right-substree
    inorderTraversal(root.right);
  }
}


class BinaryTreeNode<T> {
  T data;
  BinaryTreeNode<T> left;
  BinaryTreeNode<T> right;

  public BinaryTreeNode(T data) {
    this.data = data;
    left = right = null;
  }

  @Override
  public String toString() {
    return "Node data: " + data.toString();
  }
}

class W6Person {
  String name;

  public W6Person(String n) {
    name = n;
  }

  @Override
  public String toString() {
    return name;
  }
}