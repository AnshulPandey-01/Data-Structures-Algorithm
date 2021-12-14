package DataStructures;

class BubbleSort{
    static void bubbleSort(int arr[]){
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for(i = 0; i < n - 1; i++){
            swapped = false;
            for(j = 0; j < n - i - 1; j++){
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // if no two elements were swapped by inner loop, then break 
            if(swapped == false) break;
        }
    }
    
    static void printArray(int arr[]){ 
        int size = arr.length;
        for (int i = 0; i < size; i++) 
            System.out.println(arr[i]); 
    }
    
    public static void main(String args[]){
        int arr[] = {64, 34, 25, 12, 22, 11, 90}; 
        bubbleSort(arr);
        printArray(arr); 
    }
}