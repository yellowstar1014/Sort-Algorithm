import java.util.Random;
public class BSTree {
  private class Node {
    public Node left;
    public Node right;
    public Node parent;
    public int key;
  }
  private Node root = null;

  public static void main(String args[]) {
    BSTree tree = new BSTree();
    Node[] nodes = tree.getRandNodes();
    System.out.println("Keys of all randomed nodes:");
    tree.printKeys(nodes);
    tree.buildBSTree(tree, nodes);
    System.out.println("inOrderTreeWalk:");
    tree.inOrderTreeWalk(tree.root);
    System.out.println();
    System.out.println("RecursiveSearch for node whose key = 6:");
    Node node = tree.treeSearch(tree.root, 6);
    tree.printNode(node);
    System.out.println();
    System.out.println("IterativeSearch for node whose key = 6:");
    node = tree.iterativeTreeSearch(tree.root, 6);
    tree.printNode(node);
    System.out.println();
    System.out.println("Successor of node whose key = " + nodes[5].key);
    node = tree.treeSuccessor(nodes[5]);
    tree.printNode(node);
    System.out.println();
    System.out.println("Predecessor of node whose key = " + nodes[5].key);
    node = tree.treePredecessor(nodes[5]);
    tree.printNode(node);
    System.out.println();
    node = nodes[5];
    System.out.print("Delete node ");
    tree.printNode(node);
    tree.delete(tree, node);
    System.out.println();
    tree.inOrderTreeWalk(tree.root);
    System.out.println();
  }

  public void inOrderTreeWalk(Node x) {
    if (x != null) {
      inOrderTreeWalk(x.left);
      printNodeKey(x);
      inOrderTreeWalk(x.right);
    }
  }

  public Node treeSearch(Node x, int key) {
    if (x == null || key == x.key) {
      return x;
    }
    else if (key < x.key) {
      return treeSearch(x.left, key);
    }
    else {
      return treeSearch(x.right, key);
    }
  }

  public Node iterativeTreeSearch(Node x, int key) {
    while (x != null && key != x.key) {
      if (key < x.key) {
        x = x.left;
      }
      else {
        x = x.right;
      }
    }
    return x;
  }

  public Node treeMinimum(Node x) {
    while (x != null && x.left != null) {
      x = x.left;
    }
    return x;
  }

  public Node treeMaximum(Node x) {
    while (x != null && x.right != null) {
      x = x.right;
    }
    return x;
  }

  public Node treeSuccessor(Node x) {
    if (x.right != null) {
      return treeMinimum(x.right);
    }
    Node y = x.parent;
    while (y != null && x != y.left) {
      x = y;
      y = x.parent;
    }
    return y;
  }

  public Node treePredecessor(Node x) {
    if (x.left != null) {
      return treeMaximum(x.left);
    }
    Node y = x.parent;
    while (y != null && x != y.right) {
      x = y;
      y = x.parent;
    }
    return y;
  }

  public void insert(BSTree t, Node x) {
    Node cnode = t.root;
    Node pnode = null;
    while ( cnode != null) {
      pnode = cnode;
      if (cnode.key > x.key) {
        cnode = cnode.left;
      }
      else {
        cnode = cnode.right;
      }
    }
    x.parent = pnode;
    if (pnode == null) {
      t.root = x;
    }
    else if (x.key < pnode.key) {
      pnode.left = x;
    }
    else {
      pnode.right = x;
    }
  }

  public void delete2 (BSTree t, Node x) {
    if (x.left == null) {
      transplate(t, x, x.right);
    }
    else if (x.right == null) {
      transplate(t, x, x.left);
    }
    else if (x.right.left == null) {
      x.right.left = x.left;
      x.left.parent = x.right;
      x.right.parent = x.parent;
      transplate(t, x, x.right);
    }
    else {
      Node snode = treeMinimum(x.right);
      transplate(t, snode, snode.right);
      transplate(t, snode.right, x.right);
      transplate(t, x, snode);
      snode.left = x. left;
      x.left.parent = snode;
    }

  }

  public void delete (BSTree t, Node x) {
    if (x.left == null) {
      transplate(t, x, x.right);
    }
    else if (x.right == null) {
      transplate(t, x, x.left);
    }
    else {
      Node snode = treeMinimum(x.right);
      if (snode.parent != x) {
        transplate(t, snode, snode.right);
        snode.right = x.right;
        snode.right.parent = snode;
      }
      transplate(t, x, snode);
      snode.left = x.left;
      snode.left.parent = snode;
    }
  }

  public void transplate(BSTree t, Node u, Node v) {
    if (u.parent == null) {
      t.root = v;
    }
    else if (u.parent.left == u) {
      u.parent.left = v;
    }
    else {
      u.parent.right = v;
    }
    if (v != null) {
      v.parent = u.parent;
    }
  }

  public void printNodeKey(Node node) {
    if (node == null) {
      System.out.print("null");
    }
    else {
      System.out.print(node.key + " ");
    }
  }

  public void printNode(Node node) {
    if (node == null) {
      System.out.print("null");
    }
    else {
      System.out.print("key=" + node.key);
      if (node.left == null) {
        System.out.print(" / left key=null");
      }
      else {
        System.out.print(" / left key=" + node.left.key);
      }

      if (node.right == null) {
        System.out.print(" / right key=null");
      }
      else {
        System.out.print(" / right key=" + node.right.key);
      }
    }
  }

  public Node[] getRandNodes() {
    Node nodes[] = new Node[10];
    Random random = new Random();
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node();
      nodes[i].key = random.nextInt(10) + 1;
    }
    return nodes;
  }

  public void printKeys(Node[] nodes) {
    for(int i = 0;i < nodes.length; i++) {
      System.out.print(nodes[i].key + " ");
    }
    System.out.println();
  }

  public void buildBSTree(BSTree t, Node[] nodes) {
    int index = 0;
    while (index < nodes.length) {
      insert(t, nodes[index]);
      index++;
    }
  }
}
