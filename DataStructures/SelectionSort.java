package DataStructures;

//SelctionSort: Find the lowest element in array and swap it with 0th element, then with 1st element and so on;
//time complexity of O(n^2)
//running time doesn't depends on input data
class SelectionSort{
    void sort(int arr[]){
        int n = arr.length;
        for(int i = 0; i<n-1; i++){
            int minIndex = i;
            for(int j = i+1; j<n; j++)
                if(arr[j]<arr[minIndex])
                    minIndex = j;
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    void print(int arr[]){
        int n = arr.length;
        for(int i = 0; i<n; i++)
            System.out.println(arr[i]);
    }
    public static void main(String[] args){
        SelectionSort ob = new SelectionSort();
        int arr[] = {65,23,12,58,39,83,26};
        ob.sort(arr);
        ob.print(arr);
    }
}