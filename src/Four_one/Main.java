package Four_one;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input graph file path --> ");
        //  default: src\Four_one\input.txt
        Graph_4_1 g = new Graph_4_1(sc.nextLine());

        TopologicalSort t_sort = new TopologicalSort(g);
        System.out.print("Enter the № of start vertex for topological sort --> ");
        t_sort.sort(sc.nextInt());
    }
}
