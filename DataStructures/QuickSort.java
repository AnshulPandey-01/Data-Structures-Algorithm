package DataStructures;

import java.util.Random;

//Quick Sort: It picks pivot and divide the elements around the pivot and sort the array recursively.
//Time Complexity: O(n*log(n))

public class QuickSort{
    static int n = 0;
    static Random rand= new Random();
    
    public static int partition(int a[], int l, int r){
        //random number between range
        int p = rand.nextInt(r-l+1) + l;
        //creating pivot and swaping it with lower bound
        int pivot = a[p];
        a[p] = a[l];
        a[l] = pivot;
        //variable k to store upper bound of lower elemnts than pivot
        int k = l;
        for(int i = l+1; i<=r; i++){
            //swaping smaller element than pivot with kth element
            if(a[i]<=pivot){
                k++;
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
        //swaping pivot/lower bound to its right position
        int temp = a[k];
        a[k] = a[l];
        a[l] = temp;
        return k;
    }
    
    public static void sort(int a[], int l, int r){
        n++;
        while(l<r){
            int m = partition(a, l, r);
            //recursively calling shorter array to reduce space complexity at most O(logn)
            if((m-l)<(r-m)){
                sort(a, l, m-1);
                l = m+1;
            }else{
                sort(a, m+1, r);
                r = m-1;
            }
        }
    }
    
    public static void print(int a[]){
        for(int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
    private static void fillArrayRandom(int a[]){
        for(int i = 0; i<a.length; i++){
            a[i] = rand.nextInt(1_000_000);
        }
    }
    
    public static void main(String[ ] args){
        int arr[] = new int[1_000_000];
        fillArrayRandom(arr);
        
        long startTime = System.currentTimeMillis();
        sort(arr, 0, arr.length-1);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total Time Taken: "+totalTime);
        
        System.out.println("n: " + QuickSort.n);
        //print(arr);
    }
}