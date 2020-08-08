package Three;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {
    private Graph_3 g;
    private LinkedList<Integer> q;

    public BFS(Graph_3 g, int start_vertex) {
        this.g = g;
        q = new LinkedList<>();
        bypass(g.vertices[start_vertex]);
    }

    public void bypass(Graph_3.Vertex start_v) {
        int[][] adjM = g.getAdjMatrix().clone();
        int bfs_n = 1;
        start_v.setBfs_number(bfs_n);
        q.add(start_v.getNumber());
        printVertexData(start_v.getNumber(),bfs_n,q);
        bfs_n++;
        for(int head = q.peek(); q.peek() != null; head = q.peek()) {
            for (int i = 0; i < g.getV_num(); i++){
                if (adjM[head][i] != 0 && g.vertices[i].getBfs_number() == 0) {
                    q.add(i);
                    g.vertices[i].setBfs_number(bfs_n);
                    printVertexData(g.vertices[i].getNumber(), bfs_n,q);
                    bfs_n++;
                }
            }
            q.removeFirst();
            if(q.element() != null)
                printVertexData(null,null,q);
            else
                printVertexData(null,null,null);
        }
    }

    private void printVertexData(Integer v_number, Integer bfs_n, LinkedList<Integer> q) {
        if (bfs_n == 1) {
            System.out.println("BFS data table:");
            System.out.println("X--------------------------------------X");
            System.out.println("|  Vertex  |  BFS-№  |  Queue content  |");
            System.out.println("X--------------------------------------X");
        }
            System.out.printf("    %2d    |    %2d    |", v_number, bfs_n);
            System.out.println(" " + q);
            System.out.println("----------------------------------------");
    }

}
