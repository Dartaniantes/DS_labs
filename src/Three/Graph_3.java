package Three;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph_3 {
    private int[][] adjMatrix;
    private int v_num, e_num;
    public Vertex[] vertices;
    public Graph_3(String path) {
        readGraph(path);
    }

    private void readGraph(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] v_e_nums = br.readLine().split(" ");
            v_num = Integer.parseInt(v_e_nums[0]);
            e_num = Integer.parseInt(v_e_nums[1]);
            adjMatrix = new int[v_num][v_num];
            vertices = new Vertex[v_num];
            int start, end;
            while (br.ready()) {
                String[] u_v_vertexes = br.readLine().split(" ");
                if(u_v_vertexes.length == 2){
                    start = Integer.parseInt(u_v_vertexes[0]);
                    end = Integer.parseInt(u_v_vertexes[1]);
                    adjMatrix[start-1][end-1] = 1;
                    adjMatrix[end-1][start-1] = 1;
                }
            }
            for (int i = 0; i < v_num; i++) {
                vertices[i] = new Vertex(i);
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
        private int bfs_number = 0;
        private int dfs_number = 0;

        public int getDfs_number() {
            return dfs_number;
        }

        public void setDfs_number(int dfs_number) {
            this.dfs_number = dfs_number;
        }

        private Vertex(int num) {
            this.number = num;
        }
        public int getNumber() {
            return number;
        }

        public int getBfs_number() {
            return bfs_number;
        }

        public void setBfs_number(int bfs_number) {
            this.bfs_number = bfs_number;
        }
    }
}
