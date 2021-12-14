package DataStructures;

import java.util.Scanner;
import java.util.Stack;

class BalancedParantheses{
    public static char[][] TOKENS = {{'{','}'},{'[',']'},{'(',')'}};
    
    public static boolean isOpenTerm(char c){
        for(char[] array: TOKENS){
            if(array[0]==c){
                return true;
            }
        }
        return false;
    }
    
    public static boolean matches(char openTerm, char closeTerm){
        for(char[] array: TOKENS){
            if(array[0]==openTerm){
                return array[1]==closeTerm;
            }
        }
        return false;
    }
    
    public static boolean isBalanced(String expression){
        Stack<Character> stack = new Stack<Character>();
        for(char c: expression.toCharArray()){
            if(isOpenTerm(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty() || !matches(stack.pop(),c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public static void main(String[] args){
        System.out.println(isBalanced(new Scanner(System.in).nextLine()));
    }
}