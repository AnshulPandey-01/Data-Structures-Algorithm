package DataStructures2;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class BitWise{
    public static void main(String[] args){
        // x which is 3 in binary will be 0011
        int x = 3;
        // y which is 6 in binary will be 0110
        int y = 6;
        
        // AND operation: Only when both are 1 output will be 1
        System.out.println("AND operation: " + (x&y));
        
        // OR operation: Only when both are 0 output will be 0
        System.out.println("OR operation: " + (x|y));
        
        // XOR operation: When both the bits are different output will be 1 else 0
        System.out.println("XOR operation: " + (x^y));
        
        // NOT operation: Reverse all the bits
        // In Java negative numbers are stored in 2's compliment form i.e reverse all the bits and add 1
        System.out.println("NOT operation: " + (~x));
        
        /**
         * Left Shift operation: Left shifts the bits of the first operand by the second operand places.
         * Fills new bit by 0.
         * Equivalent to multiplying x with 2^y.
         */
        System.out.println("Left Shift operation: " + (4<<1));
        
        /** 
         * Right Shift operation: Right shifts the bits of the first operand by the second operand places.
         * Fills leading bit with 0 in case of positive numbers and with 1 in case of negative numbers.
         * Equivalent to dividing x with 2^y.
         */
        System.out.println("Right Shift operation: " + (7>>1));
        
        // Unsigned Right Shift operation: same as right shift operator but fills leading bit with 0 in case of negative nummbers.
        // converts negative numbers to positive
        System.out.println("Unsigned Right Shift operation: " + (-1>>>1));
    }
}