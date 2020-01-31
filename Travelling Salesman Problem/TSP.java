import java.util.*;
import java.io.*;
import java.math.BigInteger;

class city {
    private int x, y, number;

    public city(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }
}

class distance {
    public static double euclidianDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double costFunction(LinkedList<city> arr) {
        double sum = 0.0;
        for (int i = 0; i < arr.size() - 1; i++) {
            city temp = arr.get(i);
            city temp1 = arr.get(i + 1);
            sum += distance.euclidianDistance(temp.getX(), temp1.getX(), temp.getY(), temp1.getY());
        }
        return sum;
    }
}

class TSP {

    private LinkedList<city> bestState;

    public static int count = 0;

    public TSP(ArrayList<city> arr) {
        bestState = new LinkedList<city>();
        this.initialState(arr);
    }

    public LinkedList<city> getBest() {
        return bestState;
    }

    public void initialState(ArrayList<city> array) {
        for (int i = 0; i < array.size(); i++) {

            bestState.add(array.get(i));
        }
    }

    public LinkedList<city> getNewState() {
        int a = (int) (Math.random() * bestState.size());
        int tp = (int) (Math.random() * bestState.size());
        while (a == tp) {
            tp = (int) (Math.random() * bestState.size());
        }
        int b = tp;
        LinkedList<city> newState = (LinkedList<city>) bestState.clone();

        city temp = newState.get(a);
        newState.set(a, newState.get(b));
        newState.set(b, temp);
        return newState;
    }

    public void simulatedAnnealing(double T, double b, BigInteger maxIteration) {
        BigInteger max = maxIteration;
        double track = 0.0;
        while (T > 0) {
            LinkedList<city> newState = getNewState();
            double costNew = distance.costFunction(newState);
            double costBest = distance.costFunction(bestState);
            double delE = (costNew - costBest);
            if (max.equals(BigInteger.valueOf(0))) {
                return;
            }
            if (delE <= 0) {
                // print(newState);
                // System.out.println();
                System.out.println(costBest + "\n");
                count++;
                bestState = newState;
                T = b * T;
                max = max.add(BigInteger.valueOf(-1));
            } else {
                double probability = 1 / (1 + Math.pow(2.71828, (-1 * delE) / T));
                double rand = Math.random();
                // System.out.println(probability+" " + rand);
                if (probability == track || probability >= 0.99) {
                    max = max.add(BigInteger.valueOf(-1));
                } else {
                    track = probability;
                    max = maxIteration;
                }
                if (rand > probability) {
                    // print(newState);
                    // System.out.println();
                    System.out.println(costBest + "\n");
                    count++;
                    bestState = newState;
                    T = b * T;
                    max = max.add(BigInteger.valueOf(-1));
                }
            }
        }
    }

    public void print(LinkedList<city> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getNumber());
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<city> example = new ArrayList<city>();

        File file = new File("dataset.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            example.add(new city(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[0])));
        }

        TSP tsp = new TSP(example);

        System.out.println(distance.costFunction(tsp.getBest()));

        tsp.print(tsp.getBest());
        System.out.println();

        tsp.simulatedAnnealing(1000000000, 0.2, new BigInteger("10000000000000000"));

        tsp.print(tsp.getBest());


        System.out.println();
        System.out.println(distance.costFunction(tsp.getBest()));
        br.close();

    }
}
