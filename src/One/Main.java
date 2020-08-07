package One;


public class Main {
    public static void main(String[] args) {
        Graph_1 g = new Graph_1("src/one/input.txt");
        System.out.println("ADj matrix:");
        showMatrix(g.getAdjMatrix());
        System.out.println("Inc matrix:");
        showMatrix(g.getIncMatrix());
        System.out.println(g.getIsolatedVertexes());
        g.showVertexesPowers();
    }

    public static void showMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == matrix[i].length-1)
                    System.out.println(matrix[i][j]);
                else System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }
}
