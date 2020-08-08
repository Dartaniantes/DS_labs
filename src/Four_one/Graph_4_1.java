package Four_one;

import Three.Graph_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph_4_1 {
    private int[][] adjMatrix;
    private int v_num, e_num;
    public Graph_4_1.Vertex[] vertices;

    public Graph_4_1(String path) {
        readGraph(path);
    }

    private void readGraph(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] v_e_nums = br.readLine().split(" ");
            v_num = Integer.parseInt(v_e_nums[0]);
            e_num = Integer.parseInt(v_e_nums[1]);
            adjMatrix = new int[v_num][v_num];
            vertices = new Graph_4_1.Vertex[v_num];
            int start, end;
            while (br.ready()) {
                String[] u_v_vertexes = br.readLine().split(" ");
                if(u_v_vertexes.length == 2){
                    start = Integer.parseInt(u_v_vertexes[0]);
                    end = Integer.parseInt(u_v_vertexes[1]);
                    adjMatrix[start-1][end-1] = 1;
                }
            }
            for (int i = 0; i < v_num; i++) {
                vertices[i] = new Graph_4_1.Vertex(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public int getV_num() {
        return v_num;
    }


    public class Vertex{
        private int number;
        private int dfs_number = 0;
        private int ts_number = 0;

        public int getTs_number() {
            return ts_number;
        }

        public void setTs_number(int ts_number) {
            this.ts_number = ts_number;
        }

        private Vertex(int num) {
            this.number = num;
        }

        public int getDfs_number() {
            return dfs_number;
        }
        public void setDfs_number(int dfs_number) {
            this.dfs_number = dfs_number;
        }
        public int getNumber() {
            return number;
        }

    }
}
