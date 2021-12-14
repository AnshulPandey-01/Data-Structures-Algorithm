package DataStructures;

/*Insertion Sort works by swaping the element of sorted side if first element of unsorted side is smaller than key.
Swaping till the correct position. If element is bigger then key is increased by one position.*/
//time complexity of O(n^2)
class InsertionSort{
    static void sort(int arr[]){
        int n = arr.length;
        for(int i = 1; i<n; i++){
            int key = arr[i];
            int j = i - 1;
            while(j>=0 && arr[j]>key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
    
    static void printArray(int arr[]){
        for(int i : arr){
            System.out.println(i);
        }
    }
    
    public static void main(String[] args){
        int arr[] = {2, 12, 5, 8, 1, 9, 19};
        sort(arr);
        printArray(arr);
    }
}