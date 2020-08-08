package Four_one;

import java.util.LinkedList;

public class TopologicalSort {
    private Graph_4_1 g;
    private LinkedList<Integer> stack;

    public TopologicalSort(Graph_4_1 g) {
        this.g = g;
        stack = new LinkedList<>();
    }

    public void sort(int start_vertex) {
        Graph_4_1.Vertex focus_v = g.vertices[start_vertex-1];
        int[][] adjM = g.getAdjMatrix().clone();
        int ts_n = g.getV_num();
        for (int i = 0; i < g.getV_num(); i++) {
            if(adjM[focus_v.getNumber() - 1][i] != 0 && focus_v.getTs_number() == 0)
                dfsBypass(focus_v, ts_n);

        }
    }

    public void dfsBypass(Graph_4_1.Vertex start_vertex, int ts_n) {
        Graph_4_1.Vertex focus_v = start_vertex;
        int[][] adjM = g.getAdjMatrix().clone();
        int dfs_n = 1;
        focus_v.setDfs_number(dfs_n);
        stack.add(focus_v.getNumber());
        for(int i = 0; i < g.getV_num(); i++) {
            if (adjM[focus_v.getNumber() - 1][i] != 0 && g.vertices[i].getDfs_number() == 0) {
                dfs_n++;
                focus_v = g.vertices[i];
                focus_v.setDfs_number(dfs_n);
                i = 0;
            }
        }
        if (stack.peekLast() != null) {
            g.vertices[stack.peekLast()-1].setTs_number(ts_n);
            printVertexData(dfs_n, focus_v.getNumber(), ts_n);
            stack.removeLast();
            if (stack.peekLast() != null) focus_v = g.vertices[stack.peekLast() - 1];
        }
    }


    private void printVertexData(Integer dfs_n, Integer v_number, Integer ts_n) {
        if (dfs_n == 1) {
            System.out.println("       Topological sort data :      ");
            System.out.println("X-----------------------------------X");
            System.out.println("|  #  | Vertex | Topological sort № |");
            System.out.println("X-----------------------------------X");

        }
        System.out.printf("| %2d  |   %2d   |         %2d         |\n",dfs_n, v_number, ts_n);
        System.out.println("|-----------------------------------|");

    }
}
