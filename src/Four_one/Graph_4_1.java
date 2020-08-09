package Four_one;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph_4_1 {
    private int[][] adjMatrix;
    private int v_num, e_num;
    public Vertex[] vertices;
    public Vertex[] v_sorted;
    LinkedList<Integer> stack;
    private int dfs_n = 1, ts_n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input graph file path --> ");
        Graph_4_1 g = new Graph_4_1(sc.nextLine());
        g.topologicalSort();
    }

    public Graph_4_1(String path) {
        readGraph(path);
        ts_n = v_num;
        v_sorted = new Vertex[v_num];
        stack = new LinkedList<>();
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
                vertices[i] = new Vertex(i+1);
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

    public void topologicalSort() {
        for (int i = 0; i < v_num; i++) {
            if (vertices[i].getTs_number() == 0) {
                dfsBypass(i+1);
            }
        }
        makeSortedGraph();
        printTopologicallySortedGraph();
    }

    public void dfsBypass(int start_v) {
        Vertex focus_v = vertices[start_v - 1];
        focus_v.dfs_number = dfs_n;
        stack.add(focus_v.number);
        while (stack.peekLast() != null){
            for (int i = 0; i < v_num; i++) {
                if (adjMatrix[focus_v.number - 1][i] != 0 & vertices[i].dfs_number == 0) {
                    dfs_n++;
                    focus_v = vertices[i];
                    focus_v.dfs_number = dfs_n;
                    stack.add(i+1);
                    i = 0;
                }
            }
            if (stack.peekLast() != null) {
                focus_v.ts_number = ts_n;
                ts_n--;
                stack.removeLast();
                if(stack.peekLast() != null) focus_v = vertices[stack.peekLast() - 1];
            }
        }
    }

    private void makeSortedGraph() {
        for (int i = 0; i < v_num; i++) {
            v_sorted[vertices[i].ts_number - 1] = vertices[i];
        }
    }

    private void printTopologicallySortedGraph() {
        for (int i = 0; i < v_num; i++) {
            if (v_sorted[i].ts_number == 1) {
                System.out.println("     Topological sort data:");
                System.out.println("X---------------------------------X");
                System.out.println("|  Topological sort №  |  Vertex  |");
                System.out.println("X---------------------------------X");
            }
            System.out.printf("|         %2d           |    %2d    |\n", v_sorted[i].ts_number, v_sorted[i].number);
            System.out.println("|---------------------------------|");
        }
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
