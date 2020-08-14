package Two;

import Auxiliary.Arrays;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }
    static void start(){
        Graph_2 graph;
        Scanner sc = new Scanner(System.in);
        String path;
//        System.out.print("Enter input graph file path (default : 'src/Two/input.txt') --> ");
//        path = sc.nextLine();
        graph = new Graph_2("src/Two/input.txt", false);
        int input = 0;
        String output_path;
        System.out.println("Hello");
        while(input != -1){
            System.out.print("1. Distance and reachability matrices\n" +
                    "2. Metric characteristics:\n" +
                    "   > Graph's diameter\n" +
                    "   > Graph's radius\n" +
                    "   > Graph's center\n" +
                    "   > Graph's vertexes tiers\n" +
                    "Enter the number of required field (enter '-1' to quit the program) --> ");
            input = sc.nextInt();
            switch (input) {
                case 1: {
                    System.out.print("DISTANCE AND REACHABILITY MATRICES:\n" +
                            "1. Display\n" +
                            "2. Write down to file\n" +
                            "3. Display and write down to file\n" +
                            "Enter the number of desired row (enter '-1' to quit the program) --> ");
                    input = sc.nextInt();
                    switch (input) {
                        case 1: {
                            System.out.println("> Distance matrix : ");
                            graph.getDistMatrix().show();
                            System.out.println("> Reachability matrix : ");
                            graph.getReachMatrix().show();
                            System.out.println();
                            break;
                        }
                        case 2: {
                            System.out.print("Enter output file path for distance matrix-->");
                            output_path = sc.nextLine();
                            graph.getDistMatrix().writeToFile(output_path);
                            System.out.print("Enter output file path to reachability matrix-->");
                            output_path = sc.nextLine();
                            graph.getReachMatrix().writeToFile(output_path);
                            break;
                        }
                        case 3:{
                            graph.getDistMatrix().show();
                            System.out.print("Enter output file path for distance matrix-->");
                            output_path = sc.nextLine();
                            graph.getDistMatrix().writeToFile(output_path);
                            System.out.print("Enter output file path for reachability matrix-->");
                            output_path = sc.nextLine();
                            graph.getReachMatrix().writeToFile(output_path);
                            break;
                        }
                    }
                    break;

                }
                case 2: {
                    System.out.print("METRIC CHARACTERISTICS:\n" +
                            "   > Diameter = " + graph.getDiametr() + "\n" +
                            "   > Radius = " + graph.getRadius() + "\n" +
                            "   > Graph center : ");
                    Arrays.display(graph.getCenter());
                    System.out.println("  > Graph tiers : ");
                    graph.showTiers();
                    System.out.println();
                }
            }
        }
    }
}
