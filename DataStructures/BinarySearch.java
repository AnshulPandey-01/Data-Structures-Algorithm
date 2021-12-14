package DataStructures;

class BinarySearch{
    //Time complexity O(logn)
    public static int search(int arr[], int low, int high, int val){
        if(high<low){
            return -1;
        }
        int mid = low + ((high-low)/2);
        if(val==arr[mid]){
            return mid;
        }else if(val<arr[mid]){
            return search(arr, low, mid-1, val);
        }else{
            return search(arr, mid + 1, high, val);
        }
    }
    public static void main(String args[]){
        int array[] = {5, 6, 9, 10, 14, 18, 20, 23, 26, 28};
        System.out.println(search(array, 0, 10, 7));
    }
}