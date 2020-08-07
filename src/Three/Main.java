package Three;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter input file path --> ");
//        String path = sc.nextLine();
        Graph_3 g = new Graph_3("src/Three/input.txt");
        System.out.print("Enter the № of start vertex for BFS bypass --> ");
        BFS bfs = new BFS(g, sc.nextInt());
    }
}
