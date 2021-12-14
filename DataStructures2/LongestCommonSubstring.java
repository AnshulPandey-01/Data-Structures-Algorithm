package DataStructures2;

import java.util.*;

// input:
// abcdaf
// zbcdf
// output: bcd
class LongestCommonSubstring{
    
    public static String getLCS(char[] x, char[] y){
        // for max length of LCS return max.
        int max = 0, index = 0;
        int[][] dp = new int[x.length+1][y.length + 1];
        
        for(int i = 1; i<=x.length; i++){
            for(int j = 1; j<=y.length; j++){
                if(x[i-1]==y[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(max < dp[i][j]){
                        max = dp[i][j];
                        index = i;
                    }
                }
            }
        }
        
        char[] s = new char[max];
        for(int i = index, k = max - 1; k>=0; i--, k--)
            s[k] = x[i-1];
        
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