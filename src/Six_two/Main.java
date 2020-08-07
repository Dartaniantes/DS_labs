package Six_two;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/Six_two/input.txt");
        Graph graph = new Graph(file);
        graph.graphColor();

    }

}
