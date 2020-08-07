package Auxiliary;

public class Arrays {
    public static int findMax(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            if(arr[i] > max)
                max = arr[i];
        return max;
    }
    public static int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++)
            if(arr[i] < min)
                min = arr[i];
        return min;
    }
    public static int[] findMinsIndexes(int[] arr) {
        int min = Integer.MAX_VALUE;
        int i, mins_counter = 0;
        int[] indexes;
        for (i = 0; i < arr.length; i++)
            if(arr[i] < min){
                if(min == arr[i])
                    mins_counter++;
                min = arr[i];
            }
        indexes = new int[mins_counter];
        int j;
        for (j = 0, mins_counter = 0; j < arr.length; j++) {
            if(arr[j] < min){
                if(min == arr[i]){
                    indexes[mins_counter] = arr[i];
                    mins_counter++;
                }
                min = arr[i];
            }
        }
        return indexes;
    }
    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if(i < arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.println(arr[i]);

    }

    /*public static int findMaxsIndexes(int[] arr) {
        int max = Integer.MIN_VALUE;
        int i;
        for (i = 0; i < arr.length; i++)
            if(arr[i] > max)
                max = arr[i];
        return i;
    }*/
}
