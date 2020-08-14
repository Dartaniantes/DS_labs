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

    public static void nullify(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    public static int[] findMinsIndexes(int[] arr) {
        int min = Integer.MAX_VALUE;
        int i, mins_counter = 0;
        int[] indxs = new int[arr.length];
        for (i = 0; i < arr.length; i++)
            if(arr[i] < min){
                Arrays.nullify(indxs);
                mins_counter = 0;
                min = arr[i];
                indxs[mins_counter] = i;
                mins_counter++;
            } else if (arr[i] == min) {
                indxs[mins_counter] = i;
                mins_counter++;
            }

        int[] indexes = new int[mins_counter];
        for (int j = 0; j < mins_counter; j++) {
            indexes[j] = indxs[j];
        }
        return indexes;
    }

    public static boolean notOnlyZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] != 0)
                return true;
        return false;
    }

    public static int[] iterateEach(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] + 1;
        }
        return result;
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
