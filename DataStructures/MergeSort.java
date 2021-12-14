package DataStructures;

//MergeSort: Divide the array in two halves and recurcively sorts them and at the end merges them
//time complexity of O(n*log(n)) which is best possible for comparision based algorithm
class MergeSort{
    
    static int n = 0;
    
    void mergeSort(int arr[], int l, int r){
        n++;
        if(l<r){
            //middle term
            int m = (l+r)/2;
            //sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            //merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    private void merge(int arr[], int l, int m, int r){
        //size of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        //temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];
        //copy data to temp arrays
        for(int i = 0; i<n1; i++)
            L[i] = arr[l+i];
        for(int j = 0; j<n2; j++)
            R[j] = arr[m+1+j];
        //initial indexes of subarrays
        int i = 0, j = 0;
        //initial index of merged subarray
        int k = l;
        while(i<n1 && j<n2){
            if(L[i]<=R[j]){
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        //coyping remaining elements of L[] if any
        while(i<n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        //coyping remaining elements of R[] if any
        while(j<n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    void print(int arr[]){
        int n = arr.length;
        for(int i = 0; i<n; i++)
            System.out.println(arr[i]);
    }
    
    public static void main(String[] args){
        int arr[] = {5, 6, 7, 8, 1, 2, 3, 4};
        MergeSort ob = new MergeSort();
        ob.mergeSort(arr, 0, arr.length-1);
        System.out.println("n: "+n);
        ob.print(arr);
    }
}