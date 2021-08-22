package sorting;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {20,-5,10,12,-22,0};

        quickSort(array,0,array.length-1);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(arr,low,high);
            quickSort(arr,low,pivotIndex);
            quickSort(arr,pivotIndex+1,high);
        }
    }

     public static int partition(int[] arr, int low, int high) {
         random(arr,low,high);
         int pivot = arr[low];

         int i = low;
         int j = high;

         while(i < j) {
             while(arr[i] < pivot) {
                 i++;
             }

             while(arr[j] > pivot) {
                 j--;
             }
             swap(arr,i,j);
         }
         return j;
     }

    public static void random(int[] arr, int low, int high) {
        Random random = new Random();
        int randomPivotIndex = random.nextInt(high-low) + low;

        swap(arr, randomPivotIndex,low);
    }

     public static void swap(int[] arr, int firstIndex, int secondIndex) {
         int temp = arr[firstIndex];
         arr[firstIndex] = arr[secondIndex];
         arr[secondIndex] = temp;
     }
}
