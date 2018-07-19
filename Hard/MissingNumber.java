package Hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An array A contains all the integers from 0 to n, except for one number which
 * is missing. In this problem, we cannot access an entire integer in A with a
 * single operation. The elements of A are represented in binary, and the only
 * operation we can use to access them is “fetch the jth bit of A[i],” which
 * takes constant time. Write code to find the missing integer. Can you do it in
 * O(n) time.
 */
public class MissingNumber {
    
    public static int findMissing(List<BitInteger> list) {
        return findMissing(list, BitInteger.INTEGER_SIZE - 1);
    }
    
    public static int findMissing(List<BitInteger> list, int column) {
        if (column <0) {
            return 0;
        }
        
        List<BitInteger> oneBits = new ArrayList<>(list.size() / 2);
        List<BitInteger> zeroBits = new ArrayList<>(list.size() / 2);
        for (BitInteger t : list) {
            if (t.fetch(column) == 0) {
                zeroBits.add(t);
            } else {
                oneBits.add(t);
            }
        }
        if (zeroBits.size() <= oneBits.size()) {
            int v = findMissing(zeroBits, column - 1);
            return (v << 1);
        } else {
            int v = findMissing(oneBits, column - 1);
            return (v << 1) | 1;
        }
        
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(300000) + 1;
        int missing = rand.nextInt(n+1);
        ArrayList<BitInteger> array = initialize(n, missing);
        System.out.println("The array contains all numbers but one from 0 to " + n + ", except for " + missing);
        
        int result = findMissing(array);
        if (result != missing) {
            System.out.println("Error in the algorithm!\n" + "The missing number is " + missing + ", but the algorithm reported " + result);
        } else {
            System.out.println("The missing number is " + result);
        }
    }
    
    public static ArrayList<BitInteger> initialize(int n, int missing) {
        BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
        ArrayList<BitInteger> array = new ArrayList<BitInteger>();
        
        for (int i = 1; i <= n; i++) {
            array.add(new BitInteger(i == missing ? 0 : i));
        }
        
        // Shuffle the array once.
        for (int i = 0; i < n; i++){
            int rand = i + (int) (Math.random() * (n-i));
            array.get(i).swapValues(array.get(rand));
        }
        
        return array;
    }
}

class BitInteger {
    public static int INTEGER_SIZE;
    private boolean[] bits;
    
    public BitInteger() {
        bits = new boolean[INTEGER_SIZE];
    }
    
    /*
     * Creates a number equal to given value. Takes time proportional to
     * INTEGER_SIZE.
     */
    public BitInteger(int value) {
        bits = new boolean[INTEGER_SIZE];
        for (int j = 0; j < INTEGER_SIZE; j++) {
            if (((value >> j) & 1) == 1)
                bits[INTEGER_SIZE - 1 - j] = true;
            else
                bits[INTEGER_SIZE - 1 - j] = false;
        }
    }
    
    /** Returns k-th most-significant bit. */
    public int fetch(int k) {
        if (bits[k])
            return 1;
        else
            return 0;
    }
    
    /** Sets k-th most-significant bit. */
    public void set(int k, int bitValue) {
        if (bitValue == 0)
            bits[k] = false;
        else
            bits[k] = true;
    }
    
    /** Sets k-th most-significant bit. */
    public void set(int k, char bitValue) {
        if (bitValue == '0')
            bits[k] = false;
        else
            bits[k] = true;
    }
    
    /** Sets k-th most-significant bit. */
    public void set(int k, boolean bitValue) {
        bits[k] = bitValue;
    }
    
    public void swapValues(BitInteger number) {
        for (int i = 0; i < INTEGER_SIZE; i++) {
            int temp = number.fetch(i);
            number.set(i, this.fetch(i));
            this.set(i, temp);
        }
    }
    
    public int toInt() {
        int number = 0;
        for (int j = INTEGER_SIZE - 1; j >= 0; j--) {
            number = number | fetch(j);
            if (j > 0) {
                number = number << 1;
            }
        }
        return number;
    }
}

