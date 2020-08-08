package Three;


import java.util.LinkedList;

public class DFS {
    private Graph_3 g;
    private LinkedList<Integer> stack;

    public DFS(Graph_3 g) {
        this.g = g;
        stack = new LinkedList<>();
    }

    public void bypass(int start_vertex) {
        Graph_3.Vertex focus_v = g.vertices[start_vertex-1];
        int[][] adjM = g.getAdjMatrix().clone();
        int dfs_n = 1;
        focus_v.setDfs_number(dfs_n);
        stack.add(focus_v.getNumber());
        printVertexData(focus_v.getNumber(),dfs_n, stack);
        while (stack.peekLast() != null) {
            for(int i = 0; i < g.getV_num(); i++){
                if (adjM[focus_v.getNumber() - 1][i] != 0 && g.vertices[i].getDfs_number() == 0) {
                    dfs_n++;
                    stack.add(i+1);
                    focus_v = g.vertices[i];
                    focus_v.setDfs_number(dfs_n);
                    printVertexData(focus_v.getNumber(), dfs_n, stack);
                    i = 0;
                }
            }
            if (stack.peekLast() != null) {
                stack.removeLast();
                printVertexData(null, null, stack);
                if (stack.peekLast() != null) focus_v = g.vertices[stack.peekLast() - 1];
            }
        }
    }

    private void printVertexData(Integer v_number, Integer dfs_n, LinkedList<Integer> s) {
        if (dfs_n != null && dfs_n == 1) {
            System.out.println("DFS data table:");
            System.out.println("X--------------------------------------X");
            System.out.println("|  Vertex  |  DFS-№  |  Stack content  |");
            System.out.println("X--------------------------------------X");
        }
        System.out.printf("   %4d    |   %4d  |", v_number, dfs_n);
        System.out.println(" " + s);
        System.out.println("----------------------------------------");
    }

}
