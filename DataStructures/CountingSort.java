package DataStructures;

import java.util.Random;

class CountingSort{
    static Random rand= new Random();

    static void countSort(int arr[]){
        int n = arr.length;
        
        //maximum element in array
        int max = arr[0];
        for(int i = 1; i<n; i++)
            if(arr[i]>max)
                max = arr[i];
        
        //minimum element in array
        int min = arr[0];
        for(int i = 0; i<n; i++)
            if(arr[i]<min)
                min = arr[i];
        
        int range = max - min + 1;
        int count[] = new int[range];
        
        //array to store sorted array
        int output[] = new int[n];
        
        //storing the count of each unique object in count array
        for(int i = 0; i<n; i++) count[arr[i]-min]++;
        
        //modifying count array by adding the previous count
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
        
        //placing the element in corresponding position and decreasing the count
        for(int i = 0; i<n; i++){
            output[count[arr[i]-min] - 1] = arr[i];
            count[arr[i]-min]--;
        }
        
        //copying array
        for (int i  = 0; i<n; i++){
            arr[i] = output[i];
        }
    }
    static void print(int arr[]){
        for(int a : arr){
            System.out.println(a);
        }
    }
    
    static void fillArrayRandom(int a[]){
        for(int i = 0; i<a.length; i++)
            a[i] = rand.nextInt(1_000_000);
    }
    
    public static void main(String[] args){
        int arr[] = new int[1_000_000];
        fillArrayRandom(arr);
        
        countSort(arr); 
        print(arr);
    }
}