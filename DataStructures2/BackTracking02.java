package DataStructures2;

import java.util.ArrayList;
import java.util.List;

// Partition an array into two equal sums
public class BackTracking02 {
    
    public static void main(String[] args){
        int[] arr = {2, 1, 2, 3, 4, 8};
        
        int sum = 0;
        List<Integer> nums = new ArrayList<>();
        for(int num : arr){
            sum += num;
            nums.add(num);
        }
        
        List<Integer> ans = new ArrayList<>();
        boolean isPossible = (sum&1) == 0 && partition(arr, sum/2, 0, ans);
        
        if(isPossible){
            for(int i = ans.size() - 1; i>=0; i--)
                nums.remove((int)ans.get(i));
            
            System.out.print("Part 1: ");
            ans.forEach((index) -> System.out.print(arr[index] + " "));
            
            System.out.print("\nPart 2: ");
            nums.forEach((num) -> System.out.print(num + " "));
        }
        else
            System.out.println("Not possible");
    }
    
    static boolean partition(int a[], int sum, int i, List<Integer> ans){
        if(i>=a.length || sum<0) return false;
        else if(sum==0) return true;
        
        ans.add(i);
        boolean leftPossible = partition(a, sum-a[i], i+1, ans);
        
        if(leftPossible) return true;
        
        ans.remove(ans.size() - 1);
        return partition(a, sum, i+1, ans);
    }
}
