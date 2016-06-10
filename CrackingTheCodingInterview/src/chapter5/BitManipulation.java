package chapter5;

public class BitManipulation {

	public static void main(String[] args) {
		BitManipulation bm = new BitManipulation();
		String n = "11011101111";
		String m = "10011000011";
		int nn=Integer.parseInt(n, 2);
		int mm=Integer.parseInt(m, 2);
//		int r = bm.insertBetween(nn,mm,2,6);
//		System.out.println(Integer.toBinaryString(mm-nn));
		
		
//		double d = 0.625;
//		System.out.println(bm.binary(d));
		

//		System.out.println(1 << 32);
		

		System.out.println(Integer.toBinaryString(bm.getNextLargest(nn)));
		System.out.println(Integer.toBinaryString(bm.getNextLargest(mm)));
//		11011011111
//		10010111000
	}
	
	/*
	 * 5.1
	 * Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into N such that M starts at bit j and
	 * ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011, you can assume that there are at least 5
	 * bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between j and i. You would not, for example, have j=3
	 * and i=2, because M could not fully fit between bit 3 and bit 2.
	 * EXAMPLE
	 * INPUT: N=10000000000, M=10011, i=2, j=6
	 * OUTPUT:N=10001001100
	 */
	public int insertBetween(int n, int m, int start, int end) {
//		first clear the bits and then or with m
		int mask = ~(((1 << (end-start+1)) - 1 ) << start);
		return (n & mask) | (m << start);
	}
	
	/*
	 * 5.2
	 * Binary to String: Given a real number between 0 and 1 (e.g.,0.72) that is passed in as a double, print the binary representation. If the number cannot 
	 * be represented accurately in binary with at most 32 characters, print "ERROR".
	 */
	public String binary(double d) {
		if(d<0 || d>1) return "ERROR";
		StringBuilder res = new StringBuilder(".");
		for(int i=0;i<32&&d!=0;i++) {
			d *=2;
			if(d>=1) {
				res.append("1");
				d-=1;
			}
			else res.append("0");
		}
		if(d!=0)
			return "ERROR";
		return res.toString();
	}
	
	/*
	 * 5.3
	 * Flip Bit to Win: You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s
	 * you could create.
	 * EXAMPLE
	 * input: 1775 (or 11011101111)
	 * output:8
	 */
	public int flipBits(int i) {
		if(i==-1) return 32;
		int currentLen = 0;
		int prevLen = 0;
		int max = 1;
		while(i!=0) {
			if((i & 1) == 1)
				currentLen++;
			else {
				prevLen = (i & 2)==0? 0 : currentLen;
				currentLen = 0;
			}
			max = prevLen + currentLen+1 > max? prevLen + currentLen+1: max;
			i = i >>> 1;
		}
		return max;
	}
	
	/*
	 * 5.4
	 * Next Number: Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in there binary representation
	 */
	public int getNextLargest(int n) {//return result is smaller than n
		int c = n;
		int c1 = 0;
		int c0 = 0;
		while((c&1)==1) {
			c1++;
			c>>>=1;
		}
		if(c == 0) return -1;
		while((c&1)==0 && c!=0) {
			c0++;
			c>>>=1;
		}
		
		int p=c0+c1;
//		n=n& ((~0)<<p+1);//step 1, keep left of p and clear right of p including p
//		n= n|((1<<1+c1)-1) <<(c0-1);//step 2, add c1+1 bits 1s to right of p
		//or an arithmetic way
//		clear bits after last non-trailing one 
//		e.g. 11010011 - 11 = 11010000
		n = n - ((1<<c1) -1);
//		-1 to clear last non-trailing one and set every bit after it to 1
//		e.g. 11010000-1 = 11001111
//		keep c1+1 bits 1s to right of p and clear everything on right except the c1+1 bits 1s. which is remove c0 -1 1s from right
		n = n - (1<<(c0-1));
		
				
		return n;
	}
	
	public int getNextSmallest(int n) {//return result is larger than n
		int c = n;
		int c1 = 0;
		int c0 = 0;
		int p = 0;
		while(c!=0 && ((c&1)==0)) {
			c0++;
			c>>>=1;
		}
		while((c&1)==1) {
			c1++;
			c>>>=1;
		}
		
		if(c0+c1==31 || c0+c1==0) return -1; //no bigger number
		p=c0+c1;
		
		n=n|1<<p;//step 1, set last non-trailing zero one
		n=n&1<<p;//step 2, clear bits after non-trailing zero
		n=n|(1<<c1-1)-1;//step 3, add c1-1 bits one to right
		// or an arithmetic way
//		add 1 to the last 1 to make the last non-trailing zero one and and every bits after it zero, this merges step 1 and 2 into 1 step
//		e.g. 11011100 + 100 = 11100000
//		n=n+ 1<<c0;
//		step 3 , add c1-1 bits one to the right
//		n=n+(1 << c1-1) -1;
//		so n= n + 1<<c0 + (1 << c1-1) -1
		return n;
	}
	
	/*
	 * 5.5 Debugger: Explain what the following code does:((n&(n-1))==0)
	 * check if n is a power of 2
	 */
	
	/*
	 * 5.6
	 * Conversion: Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
	 * example:
	 * input : 29 (or 11101), 15 (or 01111)
	 * output: 2
	 */
	public int flipBits(int a, int b) {
		int diff = a^b;
		int res = 0;
		while(diff!=0) {
			if((diff & 1) == 1) res++;
			diff >>>= 1;
		//rather than shifting , we can clear the last 1 by following code
		// diff = diff & (diff-1)
		}
		return res;
	}
	
	/*
	 * 5.7
	 * Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as possible
	 * e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
	 */
	public int swapOddWithEven(int x) {
//		x & 10101010...10 to get even bits and then shift right
//		x & 01010101...01 to get odd bits and then shift left
//		merge them
		return (x&0xaaaaaaaa)>>>1 | (x&0x55555555)<<1;
	}
	
	/*
	 * Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte. The screen as width w,
	 * where w is divisible by 8 (that is, no byte will be split across rows). The height of the screen, of course, can be derived from the length of the array
	 * and the width. Implement a function that draws a horizontal line from (x1,y) to (x2,y).
	 */
	public void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 /8;
		int end_offset = x2%8;
		int last_full_byte = x2/8;
		if(start_offset!=0) first_full_byte++;
		if(end_offset!=7) last_full_byte--;
		
		for(int b=first_full_byte; b<=last_full_byte; b++) {
			screen[(width/8)*y + b] = (byte) 0xFF;
		}
		
		byte start_mask = (byte) (0xFF>>start_offset);
		byte end_mask = (byte) (0xFF >> (end_offset+1));
		
		if(x1/8 == x2/8) {
			byte mask = (byte) (start_mask&end_mask);
			screen[(width/8)*y + (x1/8)] |= mask;
		} else {
			if(start_offset!=0) {
				int byte_number = (width/8) *y +first_full_byte -1;
				screen[byte_number] |= start_mask;
			} 
			if(end_offset!=7) {
				int byte_number = (width/8) *y + last_full_byte +1;
				screen[byte_number] |= end_mask;
			}
		}
	}
}
