package chapter1;

import java.util.HashMap;

/**
 * Cracking the coding interview
 * chapter 1
 * @author zg55
 *
 */
public class ArrayAndString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayAndString a = new ArrayAndString();
//		int[][] matrix = getMatrix(6,6);
//		printMatrix(matrix);
//		matrix = new ArrayAndString().rotate(matrix);
//		printMatrix(matrix);
	    
//	    int[][] matrix = new int[][]{
//	        {0, 1, 1, 1, 0},
//	        {1, 0, 1, 0, 1},
//	        {1, 1, 1, 1, 1},
//	        {1, 0, 1, 1, 1},
//	        {1, 1, 1, 1, 1}
//	      };
//	    a.zeroMatrix(matrix);
//	    printMatrix(matrix);
		String s= "taco cat";

	    System.out.println(a.palindromePermutation(s));
		
		
	    String s1 = "waterbottle";
	    String s2 = "erbottlewa";
	    
	    System.out.println(a.stringRotation(s1,s2));
	}

	/*
	 * 1.1
	 * Is Unique: implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
	 */
	/*
	 * first ask if it is an ASCII string (128 characters with 0-31 unprintable control codes and 32-127 printable characters, but 256 characters if extended ascii) 
	 * or a unicode string(
	 * Unicode can be implemented by different character encodings. The most commonly used encodings are UTF-8, UTF-16 and the now-obsolete UCS-2. 
	 * UTF-8 uses one byte for any ASCII character, all of which have the same code values in both UTF-8 and ASCII encoding, 
	 * and up to four bytes for other characters. UCS-2 uses a 16-bit code unit (two 8-bit bytes) for each character 
	 * but cannot encode every character in the current Unicode standard. UTF-16 extends UCS-2, 
	 * using one 16-bit unit for the characters that were representable in UCS-2 and two 16-bit units (4 ¡Á 8 bits) to handle each of the additional characters.)
	 */
	/*
	 * can use an array[128] if assume it is ascii
	 */
	public boolean isUnique(String s) {
		if("".equals(s)) return true;
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for(int i=0; i < s.length(); i ++) {
			if(hm.get(s.charAt(i))==null)
				hm.put(s.charAt(i), 1);
			else return false;
		}
		return true;
	}

	/*
	 * 1.2
	 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
	 */
	public boolean isPermutation(String s1, String s2) {
		if(s1 == null || s2 == null) return false;
		if(s1.length() != s2.length()) return false;
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for(int i = 0; i < s1.length(); i ++ ) {
			if(hm.get(s1.charAt(i))==null) {
				hm.put(s1.charAt(i), 1);
			}
			else
				hm.put(s1.charAt(i), hm.get(s1.charAt(i))+1);
		}
		
		for(int i = 0; i < s2.length(); i ++) {
			if(hm.get(s2.charAt(i))==null) 
				return false;
			else
				hm.put(s2.charAt(i), hm.get(s2.charAt(i))-1);
		}
		
		for(char c: hm.keySet()) {
			if(hm.get(c)!=0) return false;
		}
		return true;
	}
	
	/*
	 * 1.3
	 * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the 
	 * end to hold the additional characters, and that you are given the "true" length of the string. (Note: If implementing in Java, 
	 * please use a character array so that you can perform this operation in place.)
	 * example:
	 * input: "Mr John Smith    ", 13
	 * output: "Mr%20John%20Smith"
	 */
	public void urlify(char[] s, int l) {
		int countspace = 0;
		for(int i=0; i < l; i++) {
			if(s[i]==' ') countspace++;
		}
		int index = l + countspace*2; //every space needs an extra 2 characters in array to store ' '->'%'+'2'+'0'
		if(l < s.length) s[l] = '\0';
//		do it backwards
		for(int j=l-1; j>0; j--) {
			if(s[j]!=' ') {
				s[index-1]=s[j];
				index--;
			}
			else {
				s[index--]='0';
				s[index--]='2';
				s[index--]='%';
			}
		}
	}
	
	/*
	 * 1.4
	 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome is a word or phrase that is 
	 * the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
	 * example,
	 * input: Tact Coa
	 * Output: true (permutations: "taco cat", "atco cta", etc.)
	 */
	public boolean palindromePermutation(String s) {
		if(s == null || "".equals(s)) return true;
		int mask = 1 << s.charAt(0)-'a';
		for(int i = 1; i < s.length(); i++) {
			int index = s.charAt(i)-'a';
			if(index < 0) continue;
			mask = mask ^ 1 << index;
		}
		return mask == 0 || (mask & mask-1) == 0;
	}
	
	/*
	 * 1.5
	 * There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
	 * Given two strings, write a function to check if they are one edit (or zero edits) away.
	 */
	/*
	 * SEE DIFFERENT APPROACH ON PAGE 200
	 */
	public boolean oneAway(String s, String t) {
		if(s==null || t==null) return false;
		if(s.length() == t.length()) {
//			check if the same
			if(s.equals(t)) return true;
//			check if replaced
			for(int i=0;i<s.length();i++) {
				if(new StringBuilder(s).deleteCharAt(i).toString().equals(new StringBuilder(t).deleteCharAt(i).toString())) 
					return true;
			}
		} else if(s.length() - t.length() == 1) {
//			check if removed
			for(int i=0;i<s.length();i++) {
				if(new StringBuilder(s).deleteCharAt(i).toString().equals(t))
					return true;
			}
		} else if(s.length() - t.length() == -1) {
//			check if inserted
			for(int i=0;i<t.length();i++) {
				if(new StringBuilder(t).deleteCharAt(i).toString().equals(s))
					return true;
			}
		}
		return false;
	}
	
	/*
	 * 1.6
	 * String Compression: implement a method to perform basic string compression using the counts of repeated characters. 
	 * For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string, your method
	 * should return the original string. You can assume the string has only uppercase and lowercase letters (a-z).
	 */
	public String compress(String s) {
		if(s.length() == 1) return s;
		StringBuilder sb = new StringBuilder();
		int slow = 0;
		int fast = 1;
		int count = 1;
		while(fast < s.length()) {
			if(s.charAt(slow)!=s.charAt(fast)) {
				sb.append(s.charAt(slow)).append(count);
				slow = fast++;
				count = 1;
			} else {
				fast++;
				count++;
			}
		}
		sb.append(s.charAt(slow)).append(count);
		return sb.length()<s.length()? sb.toString(): s;
	}
	
	/*
	 * 1.7
	 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
	 * Can you do this in place?
	 */
	public int[][] rotate( int[][] matrix) {
		int l = matrix.length;
		int i = 0; 
		while(i <= l/2){
			int j = i;
			for(int k=0; k < l - 2*i - 1;k++,j++ ) {
				int prev = matrix[i][j];
				for(int m=0; m < 4; m++) {
					int dest_i = l - j -1;
					int dest_j = i;
					int current = matrix[dest_i][dest_j];
					matrix[dest_i][dest_j] = prev;
					prev = current;
					i = dest_i;
					j = dest_j;
				}
			}
			i++;
		}
		return matrix;
	}

	/*
	 * 1.8
	 * Zero matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
	 */
	public void zeroMatrix(int[][] matrix) {
		boolean firstRow = false;
		boolean firstCol = false;
		int row = matrix.length;
		int col = matrix[0].length;
		if(matrix[0][0] == 0) {
			firstRow = true;
			firstCol = true;
		} else {
			for(int i=1; i < col; i++) {
				if(matrix[0][i] == 0)
					firstRow = true;
			}
			for(int i=1; i < row; i++) {
				if(matrix[i][0] == 0)
					firstCol = true;
			}
		}
		for(int i=1; i<row; i++) {
			for(int j=1; j < col; j ++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for(int i=1; i < col; i++) {
			if(matrix[0][i] == 0)
				for(int j=1; j < row; j++) 
					matrix[j][i] = 0;
		}
		for(int i=1; i < row; i++) {
			if(matrix[i][0] == 0)
				for(int j=1; j < col; j++)
					matrix[i][j] = 0;
		}
		if(firstRow)
			for(int i=0;i < col; i++) 
				matrix[0][i] = 0;
		if(firstCol)
			for(int i=0;i < row; i++) 
				matrix[i][0] = 0;
	}
	
	/*
	 * 1.9
	 * String Rotation: Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2 write
	 * code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g. "waterbottle" is a rotation of "erbottlewat").
	 */
	public boolean stringRotation(String s1, String s2) {
		if(s1 == null || s2==null ) return false;
		if(s1.length() != s2.length()) return false;
		return isSubstring(s1, s2+s2);
	}
	
	/*
	 * for test purpose, return if s1 is a substring of s2
	 */
	private boolean isSubstring(String s1, String s2) {
		return s2.contains(s1);
	}
	/*
	 * for test purpose
	 */
	private static int[][] getMatrix(int row, int col) {
		int matrix[][] = new int[row][col];
		for(int i = 0; i < row; i ++) {
			for(int j = 0; j < col; j++) {
				matrix[i][j] = i*col + j;
			}
		}
		return matrix;
	}
	
	/*
	 * for test purpose
	 */
	private static void printMatrix(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for(int i = 0; i < row; i ++) {
			for(int j = 0; j < col; j++) {
				System.out.print(matrix[i][j]+", ");
			}
			System.out.println();
		}
	}
}
