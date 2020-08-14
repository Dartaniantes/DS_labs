package Auxiliary;

import Two.Graph_2;

import java.util.PriorityQueue;
import java.util.Queue;

public class Debugging {
    public static void main(String[] args) {
        Graph_2 g = new Graph_2("src/Two/input.txt", false);
        Matrix a, b;
        a = g.getAdjMatrix().cloneMatrix();
        b = g.getAdjMatrix().cloneMatrix();
        System.out.println("Adj matrix: ");
        a.show();
        for (int i = 0; i < g.getV_num(); i++) {
            System.out.println("A involuted to pow " + i);
            a.involute(i);
            a.show();
            a = g.getAdjMatrix().cloneMatrix();
        }

    }

}
