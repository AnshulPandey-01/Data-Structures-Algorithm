package DataStructures;

import java.util.*;

public class OptimizedQuickSort {
    static int n = 0;
    private static Random random = new Random();

    private static int partition3(int[] a, int l, int r) {
        int m1 = l;
        int m2 = r;
        int i = l;
        int x = a[l]; // x is pivot
        int[] m = { m1, m2 };

        while(i <= m2){
            //swaping with pivot if element is less than than pivot
            if(a[i] < x){
                int temp = a[m1];
                a[m1] = a[i];
                a[i] = temp;
                m1++;
                i++;
            }
            //for the begining and when another element has same value as pivot
            else if(a[i] == x){
                i++;
            }
            //swaping element with last pointer element if element is greater than pivot
            else{
                int temp = a[m2];
                a[m2] = a[i];
                a[i] = temp;
                m2--;
            }
            m[0] = m1;
            m[1] = m2;
        }
        
        return m[0];
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        n++;
        if (l >= r) {
            return;
        }
        //swaping random pivot with first element
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        
        int m = partition3(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    private static void print(int a[]){
        for(int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
    private static void fillArrayRandom(int a[]){
        for(int i = 0; i<a.length; i++){
            a[i] = random.nextInt(1_000_000);
        }
    }
    
    public static void main(String[] args) {
        int a[] = new int[1_000_000];
        fillArrayRandom(a);
        
        long startTime = System.currentTimeMillis();
        randomizedQuickSort(a, 0, a.length - 1);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total Time Taken: "+totalTime);
        
        System.out.println("n: " + OptimizedQuickSort.n);
        //print(a);
    }
}