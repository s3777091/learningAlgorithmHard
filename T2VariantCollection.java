public class T2VariantCollection {
  public static void main(String[] args) {
    T2VariantCollection col = new T2VariantCollection();
    col.addVariant(new CovidVariant("Alpha", "210201A"));
    col.addVariant(new CovidVariant("Delta", "210311D"));
    col.addVariant(new CovidVariant("Beta", "210311A"));
    col.addVariant(new CovidVariant("Omicron", "211120D"));
    System.out.println(col.search("210311A").name); // return the Beta variant
    System.out.println(col.previous("211120D").name); // return the Delta variant
  }

  CovidNode root;

  public void addVariant(CovidVariant v) {
    CovidNode n = new CovidNode(v);
    // Empty tree?
    if (root == null) {
      root = n;
      return;
    }
    // Find the appropriate location
    CovidNode temp = root;
    while (true) {
      if (temp.value.isGreater(v)) {
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
  }

  public CovidVariant search(String code) {
    CovidNode temp = root;
    CovidVariant v = new CovidVariant("abc", code);
    while (true) {
      if (temp.value.code.equals(code)) {
        return temp.value;
      }
      if (temp.value.isGreater(v)) {
        if (temp.left == null) {
          return null;
        } else {
          temp = temp.left;
        }
      } else {
        if (temp.right == null) {
          return null;
        } else {
          temp = temp.right;
        }
      }
    }
  }

  public CovidVariant previous(String code) {
    CovidNode temp = root;
    CovidVariant v = new CovidVariant("abc", code);
    boolean found = false;
    CovidNode[] visit = new CovidNode[100];
    int start = 0;
    while (true) {
      visit[start] = temp;
      start++;
      if (temp.value.code.equals(code)) {
        found = true;
        break;
      }
      if (temp.value.isGreater(v)) {
        if (temp.left == null) {
          return null;
        } else {
          temp = temp.left;
        }
      } else {
        if (temp.right == null) {
          return null;
        } else {
          temp = temp.right;
        }
      }
    }
    if (found) {
      // find the previous of temp
      // first case: there is a left child of temp
      if (temp.left != null) {
        temp = temp.left;
        while (temp.right != null) {
          temp = temp.right;
        }
        return temp.value;
      } else {
        start--;
        // visit[start] current node
        // visit[start - 1] parent node
        while (start - 1 >= 0) {
          if (visit[start - 1].right == visit[start]) {
            return visit[start - 1].value;
          }
          start--;
        }
        return null;
      }
    }
    return null;
  }
}

class CovidVariant  {
  String name;
  String code;

  public CovidVariant(String n, String c) {
    name = n;
    code = c;
  }

  boolean isGreater(CovidVariant other) {
    // YYMMDD<Order>
    int d1 = Integer.parseInt(code.substring(0, 6));
    int d2 = Integer.parseInt(other.code.substring(0, 6));
    char c1 = code.charAt(6);
    char c2 = other.code.charAt(6);
    if (d1 != d2) {
      return d1 > d2;
    }
    return c1 > c2;
  }
}

class CovidNode {
  CovidVariant value;
  CovidNode left, right;

  public CovidNode(CovidVariant v) {
    value = v;
    left = right = null;
  }
}