import java.util.*;


class Node{
    ArrayList<Node> childs;
    String data;
    Node parent;
}


class tree{
    public tree(String data)
    {
        root = createNode(data);
    }

    public Node createNode(String data)
    {
        Node node = new Node();
        node.childs = new ArrayList<Node>();
        node.parent = null;
        node.data = data;

        return node;
    }

    

    public Node getRoot()
    {
        return root;
    }


    public void addNode(Node par,Node child)
    {
        par.childs.add(child);
        child.parent = par;
    }
    private Node root;
}



class rabitDfs {
    private ArrayList<Node> stack;
    private static HashMap<String, Node> visited;
    private tree dfsTree;
    private String goal = "bbb_aaa";
    private Node goalNode;

    public rabitDfs(String data) {
        dfsTree = new tree(data);
        visited = new HashMap<String, Node>();
        stack = new ArrayList<Node>();
    }

    public Node getRoot()
    {
        return dfsTree.getRoot();
    }

    public void push(Node node) {
        stack.add(node);
    }

    public Node pop() {
        Node node = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return node;
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
        } else {
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
        }
        return permut;
    }

    public void rabbitProblem(Node root) {
        stack.add(dfsTree.getRoot());
        while (!stack.isEmpty()) {
            Node cur = pop();

            // System.out.println(cur.data);

            if (cur.data.equalsIgnoreCase(goal)) {
                goalNode = cur;
                return;
            } else {
                ArrayList<String> arr = getPermutation(cur.data);
                int i = 0;
                while (i < arr.size()) {
                    // System.out.println(arr.get(i)+" added");
                    if (!visited.containsKey(arr.get(i))) {
                        Node newNode = dfsTree.createNode(arr.get(i));
                        dfsTree.addNode(cur, newNode);
                        push(newNode);
                        visited.put(newNode.data, newNode);
                    }
                    i++;
                }
            }
        }
    }

    public void reverseTraverse() {
        Node temp = goalNode;
        ArrayList<String> print = new ArrayList<String>();
        while (temp != null) {
            print.add(temp.data);
            temp = temp.parent;
        }

        System.out.println("Optimum number of steps to reach the required transitions: "+(print.size()-1));
        System.out.println();
        System.out.println();
        for (int i = print.size() - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.println(print.get(i));
            } else {
                System.out.print(print.get(i) + " -> ");
            }
        }
    }

    public static void main(String[] args)
    {
        long start = System.nanoTime();
        rabitDfs rabbit = new rabitDfs("aaa_bbb");
        rabbit.rabbitProblem(rabbit.getRoot());
        long end = System.nanoTime();

        rabbit.reverseTraverse();

        float total = (float)((end-start)/1000);
        System.out.println("\n\nTotal time consumed by the algorithm to find goal state: "+total+" micro-seconds");
    }
}