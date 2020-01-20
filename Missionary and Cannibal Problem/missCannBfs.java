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

class missCannBfs {
    private ArrayList<Node> queue;
    private static HashMap<String, Node> visited;
    private tree bfsTree;
    private String goal = "000";
    private Node goalNode;

    public missCannBfs(String mcb)
    {
        queue = new ArrayList<Node>();
        visited = new HashMap<String,Node>();
        bfsTree = new tree(mcb);
    }

    public void enqueue(Node node) {
        queue.add(node);
    }

    public Node dequeue() {
        Node node = queue.get(0);
        queue.remove(0);
        // System.out.println(node.mcb+" Queue");
        return node;
    }

    public ArrayList<String> getPermutation(String mcb) {
        ArrayList<String> permut = new ArrayList<String>();

        int m = Integer.parseInt(String.valueOf(mcb.charAt(0))), c = Integer.parseInt(String.valueOf(mcb.charAt(1))),
                b = Integer.parseInt(String.valueOf(mcb.charAt(2)));
        
        if(b == 0)
        {
            if(m<=2)
            {
                if(m<2) permut.add(String.valueOf(m+2)+String.valueOf(c)+"1");
                permut.add(String.valueOf(m+1)+String.valueOf(c)+"1");
                if(c<2)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c+2)+"1");
                    permut.add(String.valueOf(m+1)+String.valueOf(c+1)+"1");
                    permut.add(String.valueOf(m)+String.valueOf(c+1)+"1");
                }
                else if(c==2)
                {
                    permut.add(String.valueOf(m+1)+String.valueOf(c+1)+"1");
                    permut.add(String.valueOf(m)+String.valueOf(c+1)+"1");
                }
            }
            else{
                if(c<2)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c+2)+"1");
                    permut.add(String.valueOf(m)+String.valueOf(c+1)+"1");
                }
                else if(c==2)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c+1)+"1");
                }
            }
        }
        else{
            if(m>=1)
            {
                if(m>1) permut.add(String.valueOf(m-2)+String.valueOf(c)+"0");
                permut.add(String.valueOf(m-1)+String.valueOf(c)+"0");
                if(c>1)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c-2)+"0");
                    permut.add(String.valueOf(m-1)+String.valueOf(c-1)+"0");
                    permut.add(String.valueOf(m)+String.valueOf(c-1)+"0");
                }
                else if(c==1)
                {
                    permut.add(String.valueOf(m-1)+String.valueOf(c-1)+"0");
                    permut.add(String.valueOf(m)+String.valueOf(c-1)+"0");

                }
            }
            
            else{
                if(c>1)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c-2)+"0");
                    permut.add(String.valueOf(m)+String.valueOf(c-1)+"0");
                }
                else if(c == 1)
                {
                    permut.add(String.valueOf(m)+String.valueOf(c-1)+"0");
                }
            }
        }
        return permut;
    }

    public boolean check(String mcb)
    {
        int m = Integer.parseInt(String.valueOf(mcb.charAt(0))), c = Integer.parseInt(String.valueOf(mcb.charAt(1)));
        int m0 = 3-m,c0 = 3-c;
        if(m>0 && m0>0 && (m<c || m0<c0)) return false;
        return true;
    }

    public void mcBfs()
    {
        queue.add(bfsTree.getRoot());
        while(!queue.isEmpty())
        {
            Node cur = dequeue();
            // System.out.println(cur.mcb);
            if(cur.mcb.equals(goal))
            {
                goalNode = cur;
                return;
            }

            ArrayList<String> permut = getPermutation(cur.mcb);
            for(String str : permut)
            {
                // System.out.println(str + " permut");
                if(!visited.containsKey(str))
                {
                    if(check(str))
                    {
                        Node newNode = bfsTree.createNode(str);
                        bfsTree.addNode(cur, newNode);
                        enqueue(newNode);
                        visited.put(str, newNode);
                    }
                }
            }

        }
    }

    public void backTrack() {
        Node temp = goalNode;
        ArrayList<String> print = new ArrayList<String>();
        while (temp != null) {
            print.add(temp.mcb);
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
        missCannBfs aa = new missCannBfs("331");
        aa.mcBfs();
        long end = System.nanoTime();
        aa.backTrack();

        float total = (float)((end-start)/1000);
        System.out.println("\n\nTotal time consumed by the algorithm to find goal state: "+total+" micro-seconds");
    }
}
