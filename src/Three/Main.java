package Three;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input graph file path --> ");
        Graph_3 g = new Graph_3(sc.nextLine());

        BFS bfs = new BFS(g);
        System.out.print("Enter the № of start vertex for BFS bypass --> ");
        bfs.bypass(sc.nextInt());

        DFS dfs = new DFS(g);
        System.out.print("Enter the № of start vertex for DFS bypass --> ");
        dfs.bypass(sc.nextInt());
    }

}
