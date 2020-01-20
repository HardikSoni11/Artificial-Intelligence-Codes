import java.util.*;

class Node {
    ArrayList<Node> childs;
    String mcb;
    Node parent;
}

class tree {
    public tree(String mcb) {
        root = createNode(mcb);
    }

    public Node createNode(String mcb) {
        Node node = new Node();
        node.childs = new ArrayList<Node>();
        node.parent = null;
        node.mcb = mcb;

        return node;
    }

    public Node getRoot() {
        return root;
    }

    public void addNode(Node par, Node child) {
        par.childs.add(child);
        child.parent = par;
    }

    private Node root;
}

class totalSearchSpace {
    // private ArrayList<Node> stack;
    private static HashMap<String, Node> visited;
    private tree Tree;
    private String goal = "000";
    private ArrayList<Node> goalNodes;
    private int count = 0;

    public totalSearchSpace(String mcb) {
        Tree = new tree(mcb);
        visited = new HashMap<String, Node>();
        goalNodes = new ArrayList<Node>();
        // stack = new ArrayList<Node>();
    }

    public Node getRoot() {
        return Tree.getRoot();
    }

    public ArrayList<String> getPermutation(String mcb) {
        ArrayList<String> permut = new ArrayList<String>();

        int m = Integer.parseInt(String.valueOf(mcb.charAt(0))), c = Integer.parseInt(String.valueOf(mcb.charAt(1))),
                b = Integer.parseInt(String.valueOf(mcb.charAt(2)));

        if (b == 0) {
            if (m <= 2) {
                if (m < 2)
                    permut.add(String.valueOf(m + 2) + String.valueOf(c) + "1");
                permut.add(String.valueOf(m + 1) + String.valueOf(c) + "1");
                if (c < 2) {
                    permut.add(String.valueOf(m) + String.valueOf(c + 2) + "1");
                    permut.add(String.valueOf(m + 1) + String.valueOf(c + 1) + "1");
                    permut.add(String.valueOf(m) + String.valueOf(c + 1) + "1");
                } else if (c == 2) {
                    permut.add(String.valueOf(m + 1) + String.valueOf(c + 1) + "1");
                    permut.add(String.valueOf(m) + String.valueOf(c + 1) + "1");
                }
            } else {
                if (c < 2) {
                    permut.add(String.valueOf(m) + String.valueOf(c + 2) + "1");
                    permut.add(String.valueOf(m) + String.valueOf(c + 1) + "1");
                } else if (c == 2) {
                    permut.add(String.valueOf(m) + String.valueOf(c + 1) + "1");
                }
            }
        } else {
            if (m >= 1) {
                if (m > 1)
                    permut.add(String.valueOf(m - 2) + String.valueOf(c) + "0");
                permut.add(String.valueOf(m - 1) + String.valueOf(c) + "0");
                if (c > 1) {
                    permut.add(String.valueOf(m) + String.valueOf(c - 2) + "0");
                    permut.add(String.valueOf(m - 1) + String.valueOf(c - 1) + "0");
                    permut.add(String.valueOf(m) + String.valueOf(c - 1) + "0");
                } else if (c == 1) {
                    permut.add(String.valueOf(m - 1) + String.valueOf(c - 1) + "0");
                    permut.add(String.valueOf(m) + String.valueOf(c - 1) + "0");

                }
            }

            else {
                if (c > 1) {
                    permut.add(String.valueOf(m) + String.valueOf(c - 2) + "0");
                    permut.add(String.valueOf(m) + String.valueOf(c - 1) + "0");
                } else if (c == 1) {
                    permut.add(String.valueOf(m) + String.valueOf(c - 1) + "0");
                }
            }
        }
        return permut;
    }

    public boolean check(String mcb) {
        int m = Integer.parseInt(String.valueOf(mcb.charAt(0))), c = Integer.parseInt(String.valueOf(mcb.charAt(1)));
        int m0 = 3 - m, c0 = 3 - c;
        if (m > 0 && m0 > 0 && (m < c || m0 < c0))
            return false;
        return true;
    }

    public void total(Node root) {
        Node cur = root;
        if (cur.mcb.equalsIgnoreCase(goal)) {
            // System.out.println(cur.mcb);
            goalNodes.add(cur);
            return;
        } else {
            ArrayList<String> permut = getPermutation(cur.mcb);
            for (String i : permut) {
                if (i == goal) {
                    count++;
                    Node newNode = Tree.createNode(i);
                    Tree.addNode(cur, newNode);
                    goalNodes.add(newNode);
                    continue;
                }
                if (!visited.containsKey(i)) {
                    if (check(i)) {
                        count++;
                        Node newNode = Tree.createNode(i);
                        Tree.addNode(cur, newNode);
                        visited.put(i, newNode);
                    }
                }
            }
        }

        for (Node i : cur.childs) {
            total(i);
        }

    }

    public void reverseTraverse() {
        System.out.print(
                "Final path to reach \"bbb_aaa\" are given below.(paths are from child to root on the tree)\n\n");
        for (Node i : goalNodes) {
            Node temp = i;
            while (temp != null) {
                System.out.print(temp.mcb + " <-");
                temp = temp.parent;
            }
            System.out.println("\n\n\n");
        }

        System.out.println(
                "Total Search Space(i.e. Total number of nodes visited to reach to the goal for given problem):"
                        + count);
        return;
    }

    public static void main(String[] args) {
        totalSearchSpace missionary = new totalSearchSpace("331");
        missionary.total(missionary.getRoot());
        missionary.reverseTraverse();
    }
}
