package Six_two;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    int[][] adjMatrix;
    int numOfVertex;
    int numOfEdge;

    public Graph(File file){
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String[] t = bf.readLine().split(" ");
            numOfVertex = Integer.parseInt(t[0], 10);
            numOfEdge = Integer.parseInt(t[1], 10);
            adjMatrix = new int[numOfVertex][numOfVertex];
            dataInit(bf);
            System.out.println(Arrays.deepToString(adjMatrix));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataInit(BufferedReader bf) {
        try {
            while (bf.ready()) {
                String[] t = bf.readLine().split(" ");
                int line = Integer.parseInt(t[0], 10) - 1;
                int column = Integer.parseInt(t[1], 10) - 1;
                adjMatrix[line][column] = 1;
                adjMatrix[column][line] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void graphColor(){
        boolean[] colored = new boolean[numOfVertex];
        int[][] workMatrix = Arrays.stream(adjMatrix).map(int[]::clone).toArray(int[][]::new);
        ArrayList<Integer> color = new ArrayList<>();

        for(int i = 0; i<workMatrix.length; i++) {
            if (!colored[i]) {
                color.add(i);
                colored[i] = true;
                for (int j = 0; j < workMatrix[i].length; j++) {
                    if (workMatrix[i][j] == 0 && !colored[j] && i != j) {
                        color.add(j);
                        workMatrix[i] = binarySum(workMatrix[i], workMatrix[j]);
                        colored[j] = true;
                    }
                }
                System.out.println(color);
                color.clear();
            }
        }
    }

    private int[] binarySum(int[] a, int[] b){
        int[] c = new int[a.length];
        for(int i = 0; i<a.length; i++)
            c[i] = a[i] | b[i];
        return c;
    }

}
