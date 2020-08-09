package Three;

import java.util.LinkedList;

public class BFS {
    private Graph_3 g;
    private LinkedList<Integer> queue;

    public BFS(Graph_3 g) {
        this.g = g;
        queue = new LinkedList<>();
    }

    public void bypass(int start_vertex) {
        Graph_3.Vertex start_v = g.vertices[start_vertex-1];
        int[][] adjM = g.getAdjMatrix().clone();
        int bfs_n = 1;
        start_v.setBfs_number(bfs_n);
        queue.add(start_v.getNumber());
        printVertexData(start_v.getNumber(),bfs_n, queue);
        bfs_n++;
        int head = queue.peek();
        while(queue.peek() != null) {
            for (int i = 0; i < g.getV_num(); i++){
                if (adjM[head-1][i] != 0 && g.vertices[i].getBfs_number() == 0) {
                    queue.add(i+1);
                    g.vertices[i].setBfs_number(bfs_n);
                    printVertexData(g.vertices[i].getNumber(), bfs_n, queue);
                    bfs_n++;
                }
            }
            queue.removeFirst();
            if(queue.peek() != null){
                printVertexData(null,null, queue);
                head = queue.peek();
            }
            else
                printVertexData(null,null,null);
        }
    }

    private void printVertexData(Integer v_number, Integer bfs_n, LinkedList<Integer> q) {
        if (bfs_n != null && bfs_n == 1) {
            System.out.println("BFS data table:");
            System.out.println("X--------------------------------------X");
            System.out.println("|  Vertex  |  BFS-№  |  Queue content  |");
            System.out.println("X--------------------------------------X");
        }
        System.out.printf("   %4d    |   %4d  |", v_number, bfs_n);
        System.out.println(" " + q);
        System.out.println("----------------------------------------");
    }

}
