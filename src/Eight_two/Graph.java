package Eight_two;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Graph {
    int n_num, e_num;
    Edge[] edges;
    public Graph(String f_path) {
        readGraph(f_path);
    }

    private void readGraph(String f_path) {
        BufferedReader br = null;
        int[][] adjMatrix;
        int[] skeletonTree;
        try{
            br = new BufferedReader(new FileReader(f_path));
            String[] v_e_w_nums = br.readLine().split(" ");
            n_num = Integer.parseInt(v_e_w_nums[0]);
            e_num = Integer.parseInt(v_e_w_nums[1]);
            adjMatrix = new int[n_num][n_num];
            edges = new Edge[e_num];
            int start, end, weight;
            Edge min_w_edge = new Edge(0,0, Integer.MAX_VALUE);
            int e_counter = 0;
            while (br.ready()) {
                v_e_w_nums = br.readLine().split(" ");
                start = Integer.parseInt(v_e_w_nums[0]);
                end = Integer.parseInt(v_e_w_nums[1]);
                weight = Integer.parseInt(v_e_w_nums[2]);
                adjMatrix[start-1][end-1] = 1;
                adjMatrix[end-1][start-1] = 1;
                edges[e_counter].start = start;
                edges[e_counter].end = end;
                edges[e_counter].weight = weight;
                if(min_w_edge.weight > edges[e_counter].weight)
                    min_w_edge = edges[e_counter];
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }



}
