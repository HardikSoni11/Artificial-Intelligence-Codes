import java.util.*;

class Node {
    ArrayList<Node> childs;
    String data;
    Node parent;
}

class tree {
    public tree(String data) {
        root = createNode(data);
    }

    public Node createNode(String data) {
        Node node = new Node();
        node.childs = new ArrayList<Node>();
        node.parent = null;
        node.data = data;

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
    private tree dfsTree;
    private String goal = "bbb_aaa";
    private ArrayList<Node> goalNodes;
    private int count = 0;

    public totalSearchSpace(String data) {
        dfsTree = new tree(data);
        visited = new HashMap<String, Node>();
        goalNodes = new ArrayList<Node>();
        // stack = new ArrayList<Node>();
    }

    public Node getRoot() {
        return dfsTree.getRoot();
    }

    public ArrayList<String> getPermutation(String data) {
        char[] arr;
        ArrayList<String> permut = new ArrayList<String>();
        int index = data.indexOf("_");
        // System.out.println(index);

        if (index >= 2 && index < data.length() - 2) {
            // System.out.println("Here index = 4/3");
            char temp = data.charAt(index);

            if (data.charAt(index - 1) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 1];
            arr[index - 1] = temp;
            permut.add(String.valueOf(arr));
            }
            if (data.charAt(index - 2) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 2];
            arr[index - 2] = temp;
            permut.add(String.valueOf(arr));
            }

            if (data.charAt(index + 1) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 1];
            arr[index + 1] = temp;
            permut.add(String.valueOf(arr));
            }

            if (data.charAt(index + 2) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 2];
            arr[index + 2] = temp;
            permut.add(String.valueOf(arr));
            }

        } else if (index == 1) {
            // System.out.println("Here index = 1");
            char temp = data.charAt(index);
            if (data.charAt(index - 1) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 1];
            arr[index - 1] = temp;
            permut.add(String.valueOf(arr));
            }

            if (data.charAt(index + 1) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 1];
            arr[index + 1] = temp;
            permut.add(String.valueOf(arr));
            }

            if (data.charAt(index + 2) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 2];
            arr[index + 2] = temp;
            permut.add(String.valueOf(arr));
            }

        } else if (index == 0) {
            // System.out.println("Here index = 0");
            char temp = data.charAt(index);
            if (data.charAt(index + 1) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 1];
            arr[index + 1] = temp;
            permut.add(String.valueOf(arr));
            }

            if (data.charAt(index + 2) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 2];
            arr[index + 2] = temp;
            permut.add(String.valueOf(arr));
            }
        } else if (index == data.length() - 1) {
            // System.out.println("Here index = 6");
            char temp = data.charAt(index);
            if (data.charAt(index - 1) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 1];
            arr[index - 1] = temp;
            permut.add(String.valueOf(arr));
        }
        if (data.charAt(index - 2) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 2];
            arr[index - 2] = temp;
            permut.add(String.valueOf(arr));
        }
    }else

    {
        // System.out.println("Here index = 5");
        char temp = data.charAt(index);
        if (data.charAt(index - 1) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 1];
            arr[index - 1] = temp;
            permut.add(String.valueOf(arr));
        }
        if (data.charAt(index - 2) == 'a') {
            arr = data.toCharArray();
            arr[index] = arr[index - 2];
            arr[index - 2] = temp;
            permut.add(String.valueOf(arr));
        }

        if (data.charAt(index + 1) == 'b') {
            arr = data.toCharArray();
            arr[index] = arr[index + 1];
            arr[index + 1] = temp;
            permut.add(String.valueOf(arr));
        }
    }return permut;
    }

    public void rabbitProblem(Node root) {
        Node cur = root;
        if(cur.data.equalsIgnoreCase(goal))
        {
            // System.out.println(cur.data);
            goalNodes.add(cur);
            return;
        }
        else
        {
            ArrayList<String> permut = getPermutation(cur.data);
                for(String i:permut)
                {
                    if(i==goal)
                    {
                        System.out.println("Here Too\n");
                        count++;
                        Node newNode = dfsTree.createNode(i);
                        dfsTree.addNode(cur, newNode);
                        goalNodes.add(newNode);
                        continue;
                    }
                    if(!visited.containsKey(i))
                    {
                        count++;
                        Node newNode = dfsTree.createNode(i);
                        dfsTree.addNode(cur, newNode);
                        visited.put(i, newNode);
                    }
                }
        }

        for(Node i:cur.childs)
        {
            rabbitProblem(i);
        }

    }

    public void reverseTraverse() {
        System.out.print("Final path to reach \"bbb_aaa\" are given below.(paths are from child to root on the tree)\n\n");
        for(Node i:goalNodes)
        {
            Node temp = i;
            while(temp!= null)
            {
                System.out.print(temp.data + " <-");
                temp = temp.parent;
            }
            System.out.println("\n\n\n");
        }

        System.out.println("Total Search Space(i.e. Total number of nodes visited to reach to the goal for given problem):"+count);
        return;
    }

    public static void main(String[] args) {
        totalSearchSpace rabbit = new totalSearchSpace("aaa_bbb");
        rabbit.rabbitProblem(rabbit.getRoot());
        rabbit.reverseTraverse();
    }
}
