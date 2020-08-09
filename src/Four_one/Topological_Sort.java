package Four_one;

import java.util.LinkedList;

public class Topological_Sort {
    private Graph_4_1 g;
    private LinkedList<Integer> stack;
    int[][] adjM;
    int ts_n, dfs_n = 1;


    public Topological_Sort(Graph_4_1 g) {
        this.g = g;
        ts_n = g.getV_num();
        adjM = g.getAdjMatrix().clone();
        stack = new LinkedList<>();
    }

    public void sort() {
        int[][] adjM = g.getAdjMatrix().clone();
        for (int i = 0; i < g.getV_num(); i++) {
            if(g.vertices[i].getTs_number() == 0)
                dfsBypass(g.vertices[i]);
        }
    }

    public void dfsBypass(Graph_4_1.Vertex start_vertex) {
        Graph_4_1.Vertex focus_v = start_vertex;
        int[][] adjM = g.getAdjMatrix().clone();
        focus_v.setDfs_number(dfs_n);
        stack.add(focus_v.getNumber());
        for(int i = 0; i < g.getV_num(); i++) {
            if (adjM[focus_v.getNumber() - 1][i] != 0 && g.vertices[i].getDfs_number() == 0) {
                focus_v = g.vertices[i];
                dfs_n++;
                dfsBypass(focus_v);
            }
        }
         if (stack.peekLast() != null) {
            focus_v.setTs_number(ts_n);
            printVertexData(focus_v);
            ts_n--;
            stack.removeLast();
            if (stack.peekLast() != null) focus_v = g.vertices[stack.peekLast() - 1];
        }
    }

    private void printVertexData(Graph_4_1.Vertex v) {
        if (ts_n == g.getV_num()) {
            System.out.println("    Topological sort data :      ");
            System.out.println("X-----------------------------X");
            System.out.println("| Vertex | Topological sort № |");
            System.out.println("X-----------------------------X");

        }
        System.out.printf("|   %2d   |         %2d         |\n", v.getNumber(), v.getTs_number());
        System.out.println("|-----------------------------|");

    }
}
