package DataStructures;

import java.util.Scanner;

/**
 * Time Complexity: O(2^n)
 * Total Moves required: 2^(n) - 1
 */
public class TowerOfHanoi {
    
    private static int count = 0;
    
    public static void towerOfHanoi(int disks){
        solve(disks, 'A', 'B', 'X');
    }
    
    public static void solve(int disks, char fromRod, char toRod, char auxRod){
        count++;
        if(disks==1){
            System.out.println("Move disk 1 from " + fromRod + " to " + toRod);
            return ;
        }
        solve(disks - 1, fromRod, auxRod, toRod);
        System.out.println("Move disk " + disks + " from " + fromRod + " to " + toRod);
        solve(disks - 1, auxRod, toRod, fromRod);
    }
    
    public static void main(String[] args){
        System.out.print("Enter no of Disks: ");
        int n = new Scanner(System.in).nextInt();
        towerOfHanoi(n);
        System.out.println("Total moves requred: " + count);
    }
}
