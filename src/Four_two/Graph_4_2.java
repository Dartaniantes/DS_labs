package Four_two;

import Auxiliary.Matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph_4_2 {
    private int v_num, e_num, end_time = 0;
    private int[][] adjMatrix;
    private int[][] transposedAdjM;
    private Vertex[] vertices;
    LinkedList<Integer> stack;
    private Vertex[] vertexes_sorted_by_endtime;
    public Graph_4_2(String path) {
        readGraph(path);
        stack = new LinkedList<>();
        transposedAdjM = Matrix.transpose(adjMatrix);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input graph file path --> ");
        Graph_4_2 g = new Graph_4_2(sc.nextLine());
        g.SCC();
    }

    private void sortVerticesByEndtime(Vertex[] vertices) {
        Vertex[] result = vertices.clone();
        int min = Integer.MIN_VALUE;
        Vertex temp;
        for (int i = 0; i < v_num; i++) {
            for (int j = 0; j < v_num-i-1; j++) {
                if (result[j].dfs_d > result[j + 1].dfs_d) {
                    temp = result[j];
                    result[j] = result[j + 1];
                    result[j+1] = temp;
                }
            }
        }
        this.vertexes_sorted_by_endtime = result;
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
                }
            }
            fillUpVertices();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillUpVertices() {
        for (int i = 0; i < v_num; i++) {
            vertices[i] = new Vertex(i+1);
        }
    }

    public void SCC() {
        dfsLoop();
        System.out.println("Strongly connected components data:");
        dfsLoopFinal();
    }

    private void dfsLoopFinal() {
        int sccCounter = 0;
        nullifyDFSNumbers();
        for (int i = v_num-1; i >= 0; i--) {
            if(vertexes_sorted_by_endtime[i].dfs_n == 0) {
                sccCounter++;
                System.out.print("Strongly connected component # " + sccCounter + " : ");
                dfsBypassFinal(vertexes_sorted_by_endtime[i].num);
            }
        }
    }

    private void nullifyDFSNumbers() {
        for (int i = 0; i < v_num; i++) {
            vertices[i].dfs_n = 0;
        }
    }

    private void dfsLoop() {
        for (int i = 1; i <= v_num; i++) {
            if(vertices[i-1].dfs_n == 0)
                dfsBypass(i);
        }
        sortVerticesByEndtime(vertices);
    }

    public void dfsBypassFinal(int start_vertex) {
        Vertex focus_v = vertices[start_vertex-1];
        int[][] adjM = transposedAdjM.clone();
        int dfs_n = 1;
        focus_v.dfs_n = dfs_n;
        stack.add(focus_v.num);
        while (stack.peekLast() != null) {
            for(int i = 0; i < v_num; i++){
                if (adjM[focus_v.num - 1][i] != 0 && vertices[i].dfs_n == 0) {
                    dfs_n++;
                    stack.add(i+1);
                    focus_v = vertices[i];
                    focus_v.dfs_n = dfs_n;
                    i = 0;
                }
            }
            if (stack.peekLast() != null) {
                System.out.print("| " + focus_v.num + " ");
                stack.removeLast();
                if (stack.peekLast() != null) focus_v = vertices[stack.peekLast() - 1];
            }
        }
        System.out.println("|");
    }

    public void dfsBypass(int start_vertex) {
        Vertex focus_v = vertices[start_vertex-1];
        int[][] adjM = adjMatrix.clone();
        int dfs_n = 1;
        focus_v.dfs_n = dfs_n;
        stack.add(focus_v.num);
        while (stack.peekLast() != null) {
            for(int i = 0; i < v_num; i++){
                if (adjM[focus_v.num - 1][i] != 0 && vertices[i].dfs_n == 0) {
                    dfs_n++;
                    stack.add(i+1);
                    focus_v = vertices[i];
                    focus_v.dfs_n = dfs_n;
                    i = 0;
                }
            }
            if (stack.peekLast() != null) {
                dfs_n++;
                focus_v.dfs_d = dfs_n;
                stack.removeLast();
                if (stack.peekLast() != null) focus_v = vertices[stack.peekLast() - 1];
            }
        }
    }

    public class Vertex {
        private int num;
        private int dfs_n = 0;
        private int dfs_d = 0;

        public Vertex(int number) {
            this.num = number;
        }

        public Vertex cloneNum() {
            Vertex result = new Vertex(num);
            return result;
        }
        public Vertex cloneNumDFSD() {
            Vertex result = new Vertex(num);
            result.dfs_d = dfs_d;
            return result;
        }

    }
}
