package Two;

import Auxiliary.Arrays;
import Auxiliary.Matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph_2 {
    int v_num, e_num;
    private Matrix adjMatrix, distMatrix, reachMatrix;
    private int diametr, radius, center[], tiers[][][];
    private boolean ordered = false;
    private String connectivity;

    public Graph_2(String path) {
        readGraph(path);
        initDistanceMatrix();
        initReachabilityMatrix();
        initMetricCharacteristics();
    }



    public Graph_2(String path, boolean ordered) {
        this.ordered = ordered;
        readGraph(path);
        initDistanceMatrix();
        initReachabilityMatrix();
        initConnectivity();
    }

    private void readGraph(String f_path) {
        BufferedReader br = null;
        int[][] adjMatrix;
        try{
            br = new BufferedReader(new FileReader(f_path));
            String[] v_e_nums = br.readLine().split(" ");
            v_num = Integer.parseInt(v_e_nums[0]);
            e_num = Integer.parseInt(v_e_nums[1]);
            adjMatrix = new int[v_num][v_num];
            int start, end;
            int e_counter = 0;
            if(!ordered)
                while (br.ready()) {
                    String[] u_v_vertexes = br.readLine().split(" ");
                    if(u_v_vertexes.length == 2){
                        start = Integer.parseInt(u_v_vertexes[0]);
                        end = Integer.parseInt(u_v_vertexes[1]);
                        adjMatrix[start-1][end-1] = 1;
                        adjMatrix[end-1][start-1] = 1;
                    }
                }
            else
                while (br.ready()){
                    String[] u_v_vertexes = br.readLine().split(" ");
                    if(u_v_vertexes.length == 2){
                        start = Integer.parseInt(u_v_vertexes[0]);
                        end = Integer.parseInt(u_v_vertexes[1]);
                        adjMatrix[start-1][end-1] = 1;
                    }
                }
            this.adjMatrix = new Matrix(adjMatrix);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getV_num() {
        return v_num;
    }

    private void initDistanceMatrix() {
        Matrix d = adjMatrix.cloneMatrix();
        for (int i = 0; i < d.getLength(); i++) {
            for (int j = 0; j < d.getWidth(); j++) {
                if (i != j & d.getElement(i, j) == 0) {
                    d.setElement(i,j, Integer.MAX_VALUE);
                }
            }
        }

        Matrix powed = adjMatrix.cloneMatrix();
        for (int pow = 1; pow < v_num; pow++) {
            powed.involute(pow);
            for (int i = 0; i < d.getLength(); i++)
                for (int j = 0; j < d.getWidth(); j++)
                    if (i != j & powed.getElement(i, j) != 0 & d.getElement(i,j) == Integer.MAX_VALUE)
                        d.setElement(i, j, pow);
            powed = adjMatrix.cloneMatrix();
        }
        this.distMatrix = d;
    }

    private void initReachabilityMatrix() {
        Matrix r = Matrix.identityMatrix(adjMatrix);
        Matrix clonedAdjMatrix = adjMatrix.cloneMatrix();
        for (int n = 1; n < v_num; n++) {
            clonedAdjMatrix.involute(n);
            r = Matrix.booleanOr(r, clonedAdjMatrix);
            clonedAdjMatrix = adjMatrix.cloneMatrix();
        }
        this.reachMatrix = r;
    }

    private void initMetricCharacteristics(){
        initDiametr();
        initRadius();
        initCenter();
        initTiers();
    }
    private void initDiametr(){
        diametr = Arrays.findMax(distMatrix.findRowsMaxs());
    }
    private void initRadius() {
        radius = Arrays.findMin(distMatrix.findRowsMaxs());
    }
    private void initCenter() {
        center = Arrays.iterateEach(Arrays.findMinsIndexes(distMatrix.findRowsMaxs()));
    }
    private void initTiers() {
        int tiers[][][] = new int[v_num][v_num][v_num];
        int tier;
        for (int i = 0; i < distMatrix.getLength(); i++)
            for (int j = 0; j < distMatrix.getWidth(); j++){
                if (i != j) {
                    tier = distMatrix.getElement(i, j) - 1;
                    tiers[i][tier][j] = j + 1;
                }
            }
        this.tiers = tiers;
    }

    public void showTiers() {
        int i, j, k, index;
        for (i = 0; i < tiers.length; i++) {
            index = i+1;
            System.out.println("Vertex #" + index + " : ");
            for (j = 0; j < tiers[i].length; j++) {
                if (Arrays.notOnlyZeros(tiers[i][j])){
                    index = j + 1;
                    System.out.print("    > Tier #" + index + " -->");
                    System.out.print(" | ");
                    for (k = 0; k < tiers[i][j].length; k++) {
                        if(tiers[i][j][k] != 0) {
                            System.out.print(tiers[i][j][k] + " | ");
                        }
                    }
                    System.out.println("\n    ---------------------------");
                }
            }
            System.out.println("X------------------------------X");
        }
    }

    private void initConnectivity() {
        String sc = "stronglyConnected", owc = "oneWayConnected", wc = "weaklyConnected", dc = "disConnected";
        Matrix ones = Matrix.onlyOnesMatrix(adjMatrix);
        if(reachMatrix.equals(ones)){ this.connectivity = sc;}
        else if(reachMatrix.equals(reachMatrix.booleanOr(reachMatrix.transpose())))this.connectivity = owc;
        else if(Matrix.identityMatrix(adjMatrix).booleanOr(adjMatrix).booleanOr(adjMatrix.transpose()).elevate(v_num-1) == Matrix.onlyOnesMatrix(adjMatrix)) this.connectivity = wc;
        this.connectivity = dc;
    }

    public Matrix getDistMatrix() {
        return distMatrix;
    }
    public Matrix getReachMatrix() {
        return reachMatrix;
    }
    public int getDiametr() {
        return diametr;
    }
    public int getRadius() {
        return radius;
    }
    public int[] getCenter() {
        return center;
    }
    public Matrix getAdjMatrix() {
        return adjMatrix;
    }
    public int[][][] getTiers() {
        return tiers;
    }
    public String getConnectivity() {
        return connectivity;
    }
}
