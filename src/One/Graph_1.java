package One;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph_1 {
    int n_num, e_num;
    private int[][] adjMatrix, incMatrix;
    private String isolatedVertexes = "Isolated vertexes are : ";
    private int[] v_powers;

    public Graph_1(String f_path) {
        readGraph(f_path);
    }

    private void readGraph(String f_path) {
        BufferedReader br = null;
        int[][] adjMatrix, incMatrix;
        int[] v_powers;
        try{
            br = new BufferedReader(new FileReader(f_path));
            String[] v_e_nums = br.readLine().split(" ");
            n_num = Integer.parseInt(v_e_nums[0]);
            e_num = Integer.parseInt(v_e_nums[1]);
            adjMatrix = new int[n_num][n_num];
            incMatrix = new int[n_num][e_num];
            v_powers = new int[n_num];
            int start, end;
            int e_counter = 0;
            while (br.ready()) {
                String[] u_v_vertexes = br.readLine().split(" ");
                if(u_v_vertexes.length == 2){
                    start = Integer.parseInt(u_v_vertexes[0]);
                    end = Integer.parseInt(u_v_vertexes[1]);
                    e_counter++;
                    adjMatrix[start-1][end-1] = 1;
                    adjMatrix[end-1][start-1] = 1;
                    incMatrix[start-1][e_counter-1] = 1;
                    incMatrix[end-1][e_counter-1] = 1;
                    v_powers[start-1]++;
                    v_powers[end-1]++;
                } else if (u_v_vertexes.length == 1) {
                    start = Integer.parseInt(u_v_vertexes[0]);
                    v_powers[start-1]++;
                    isolatedVertexes += u_v_vertexes[0] + "...";
                }
            }
            this.adjMatrix = adjMatrix;
            this.incMatrix = incMatrix;
            this.v_powers = v_powers;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showVertexesPowers(){
        System.out.println("Vertexes powers:");
        int k;
        for (int i = 0; i < n_num; i++) {
            k = i+1;
            System.out.println("Power of " + k + " is " + v_powers[i]);
        }
        System.out.println();
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public int[][] getIncMatrix() {
        return incMatrix;
    }

    public String getIsolatedVertexes() {
        isolatedVertexes += "\n";
        return isolatedVertexes;
    }
}
