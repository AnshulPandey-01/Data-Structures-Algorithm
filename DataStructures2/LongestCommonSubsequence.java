package DataStructures2;

import java.util.Scanner;

// input:
// abcdaf
// acbcf
// output: abcf

class LongestCommonSubsequence{
    
    public static String getLCS(char[] x, char[] y){
        // for max length of LCS return max which also be equal to last index of matrix dp.
        int max = 0, a = 0, b = 0;
        int[][] dp = new int[x.length+1][y.length + 1];
        
        for(int i = 1; i<=x.length; i++){
            for(int j = 1; j<=y.length; j++){
                if(x[i-1]==y[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                
                if(dp[i][j]>max){
                    max = dp[i][j];
                    a = i; b = j;
                }
            }
        }
        
        char[] s = new char[max];
        for(int i = a, j = b, k = max - 1; k>=0; ){
            if(dp[i][j]==dp[i-1][j])
                i--;
            else if(dp[i][j]==dp[i][j-1])
                j--;
            else{
                s[k] = x[i-1];
                i--; j--; k--;
            }
        }
        
        return new String(s);
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String str = getLCS(s1.toCharArray(), s2.toCharArray());
        System.out.println(str);
    }
}