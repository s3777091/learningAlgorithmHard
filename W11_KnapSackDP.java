import java.util.ArrayList;

public class W11_KnapSackDP {
  static int[][] globalV = null;  // for the recursive (top-down) version

  public static void main(String[] args) {
    Item i1 = new Item(3, 25);
    Item i2 = new Item(2, 20);
    Item i3 = new Item(1, 15);
    Item i4 = new Item(4, 40);
    Item i5 = new Item(5, 50);
    System.out.println(knapsack(new Item[] {i1, i2, i3, i4, i5}, 6));
    System.out.println(knapsackRecursive(new Item[] {i1, i2, i3, i4, i5}, 4, 6));
  }
  
  static int knapsack(Item[] items, int capacity) {
    // dynamic programming solution to knapsack problem
    int n = items.length;
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
        if (items[i - 1].weight > j) {
          // this item is too heavy to put in the knapsack of capacity j
          // so, the maximum value should NOT include this item
          V[i][j] = V[i - 1][j];
          continue;
        }
        // it is OK to put this item into the knapsack
        // but we should only do so if we can increase the total value
        if (V[i - 1][j - items[i - 1].weight] + items[i - 1].value > V[i - 1][j]) {
          V[i][j] = V[i - 1][j - items[i - 1].weight] + items[i - 1].value;
          taken[i][j] = true;
        } else {
          V[i][j] = V[i - 1][j];
        }
      }
    }

    // rebuild the solution
    ArrayList<Integer> res = new ArrayList<Integer>();
    int cap = capacity;
    int last = n;
    while (cap > 0 && last > 0) {
      if (taken[last][cap]) {
        // the last-th index item is taken
        res.add(last - 1);
        cap -= items[last - 1].weight;
      }
      last--;
    }

    System.out.println("Items selected:");
    for (int idx : res) {
      System.out.printf("(w: %d, v: %d) ", items[idx].weight, items[idx].value);
    }
    System.out.println("\n--------------");

    return V[n][capacity];
  }

  static int knapsackRecursive(Item[] items, int last, int capacity) {
    int n = items.length;

    // the if statement below is called exactly once
    if (globalV == null) {
      globalV = new int[n + 1][capacity + 1];
      for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= capacity; j++) {
          globalV[i][j] = -1;
          // special value, to mean the cell[][] has not been filled
          // with its correct and final value
        }
      }
    }

    // base case
    if (capacity <= 0 || last < 0) {
      return 0;
    }

    // use the cached value, if it exists
    if (globalV[last + 1][capacity] != -1) {
      return globalV[last + 1][capacity];
    }

    if (items[last].weight > capacity) {
      int optimal = knapsackRecursive(items, last - 1, capacity);
      // store before return
      globalV[last + 1][capacity] = optimal;
      return optimal;
    }

    int optimalWith = knapsackRecursive(items, last - 1, capacity - items[last].weight) + items[last].value;
    int optimalWithout = knapsackRecursive(items, last - 1, capacity);

    globalV[last + 1][capacity] = Math.max(optimalWith, optimalWithout);
    return globalV[last + 1][capacity];    
  }

  static class Item {
    int weight;
    int value;

    public Item(int w, int v) {
      weight = w;
      value = v;
    }
  }
}
