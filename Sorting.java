import java.io.*;
import java.util.*;

/*  
Test1 
{9, 2, 4, 7, 3, 7, 10}
Output:
Number of pivot comparisons using first element pivot = 12
Number of pivot comparisons using last element pivot = 17

Test2
{14, 5, 1, 2, 15, 6, 16, 4}
Output:
Number of pivot comparisons using first element pivot = 14
Number of pivot comparisons using last element pivot = 14
*/

public class Sorting {
    public static long countLeft = 0;
    public static long countRight = 0;
    
    public static int[] readFile() throws IOException {
        // Reading the input file
        List<Integer> list= new ArrayList<Integer>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("QuickSort.txt"));
        String line = bufferedReader.readLine();

        while(line != null){
            list.add(Integer.parseInt(line));
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        int [] returnA = new int[list.size()];
        int i = 0;
        for (Integer val: list) {
            returnA[i] = val;
            i++;
        }
        return returnA;
    }
    
    public static void main(String args[]){

        try{
            int [] A1 = readFile();
            QuickSortLeft(A1, 0, A1.length-1);
        } catch ( IOException e){}
        System.out.println("Number of pivot comparisons using first element pivot = " + Sorting.countLeft);

        try{
            int [] A2 = readFile();
            QuickSortRight(A2, 0, A2.length-1);
        } catch ( IOException e){}
        System.out.println("Number of pivot comparisons using last element pivot = " + Sorting.countRight);
    }

    public static int PartitionLeft(int[] A, int low, int high) {
        //int pivotpoint = a[low];
        //Sorting.countLeft += high-low;
        int pivotpoint = low;
        int j = low;
        for (int i = low; i <= high; i++) {
            if (A[pivotpoint] > A[i]) {
                j++;
                int swap = A[i];
                A[i] = A[j];
                A[j] = swap;
            }
        }
        // Swapping the elements
        pivotpoint = j;
        int swap = A[low];
        A[low] = A[pivotpoint];
        A[pivotpoint] = swap;
        return pivotpoint;
    }

    public static void QuickSortLeft(int[] A, int low, int high) {
        if (low < high) {
            Sorting.countLeft += high-low;
            int pivotpoint = PartitionLeft(A, low, high);
            //Sorting.countLeft += (pivotpoint-1 - low);
            QuickSortLeft(A, low, pivotpoint - 1);
            //Sorting.countLeft += (high - pivotpoint+1);
            QuickSortLeft(A, pivotpoint + 1, high);
        }
    }

    public static int PartitionRight(int[] A, int low, int high) {
        //Sorting.countRight += high-low;
        int swap = A[low];
        A[low] = A[high];
        A[high] = swap;
        int pivotpoint = low;
        //int pivotpoint = a[high];
        int j = low;
        for (int i = low + 1; i <= high; i++) {
            if (A[pivotpoint] >= A[i]) {
                j++;
                swap = A[i];
                A[i] = A[j];
                A[j] = swap;
            }
        }
        // Swapping the elements
        pivotpoint = j;
        swap = A[low];
        A[low] = A[pivotpoint];
        A[pivotpoint] = swap;
        return pivotpoint;
    }

    public static void QuickSortRight(int[] A, int low, int high) {
        if (low < high) {
            Sorting.countRight += high-low;
            int pivotpoint = PartitionRight(A, low, high);
            QuickSortRight(A, low, pivotpoint - 1);
            QuickSortRight(A, pivotpoint + 1, high);
        }
    }
}