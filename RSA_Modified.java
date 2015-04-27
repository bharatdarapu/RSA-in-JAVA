/*Rivest-Shamir-Adleman Algorithm 
 * Written by Bharath Darapu * */

package syr.rsa;

import java.util.Scanner;
import java.math.*;

public class RSA_Modified {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner read = new Scanner(System.in);
		BigInteger p,q,e,d;
		BigInteger num,eNum,dNum;
		BigInteger N;
		
		/*Scanning the two prime numbers*/
		System.out.println("Please enter the first Prime number:");
		p=read.nextBigInteger();
		System.out.println("Please enter the Second Prime number:");
		q=read.nextBigInteger();
		
		/*Reading the message(number) to be encrypted*/
		System.out.println("Please enter the number to be encrypted:");
		num=read.nextBigInteger();
		
		/*Calculating the encryption exponent*/
		e=encExponent(p,q);
		System.out.println("Encryption Exponent:- "+e);
		N=p.multiply(q);
		eNum=encrypt(num,N,e);
		System.out.println("Encrypted number is:- "+eNum);
		
		/*Calculating the decryption exponent*/
		d=decExponent(p,q,e);
		System.out.println("Decryption Exponent:- "+d);
		
		dNum=decrypt(eNum,N,d);
		System.out.println("Decrypted number is:- "+dNum);
		read.close();
	}
	
	
	/*Euclid's Algorithm implementation for finding the GCD*/
	static BigInteger findGCD(BigInteger l1, BigInteger l2) {
		if(l2.equals(BigInteger.ZERO))
	        return l1;
	    return findGCD(l2, l1.mod(l2));
	}
	
	
	/*Calculating the Encryption exponent*/
	static BigInteger encExponent(BigInteger p,BigInteger q)
	{
		BigInteger encExp= new BigInteger("0");
		
		/*Starting with prime number '2' we check for all other numbers greater than 2. If GCD of the selected number and (p-1)*(q-1) is one then we stop.
		 * here we are checking for the smallest value for easy encryption */
	    for(BigInteger e= new BigInteger("2");;e=e.add(BigInteger.ONE))
	    {
	   
	    /*Writing a condition to be sure the calculated encrytion exponent is not either p or q*/	
	    if(e.equals(p)||e.equals(q))
	    e=e.add(BigInteger.ONE);
	    
	    /*if the GCD of e and (p-1)*(q-1) is one then we assign e to be the encryption exponent*/
	    if(findGCD(e,p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")))).equals(BigInteger.ONE))
	    {
	    encExp=e;
	    break;
	    }
		}
		return encExp;
	}
	
	
	/*Calculating the decryption exponent*/
	static BigInteger decExponent(BigInteger p,BigInteger q, BigInteger e)
	{
		BigInteger eNum= e.modInverse(p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1"))));
		return eNum;
	}
	
	/*Encrypting*/
	static BigInteger encrypt(BigInteger num,BigInteger N, BigInteger e)
	{
		BigInteger eNum = num.modPow(e,N);
		return eNum;
	}
	
	/*Decrypting*/
	static BigInteger decrypt(BigInteger eNum,BigInteger N,BigInteger d)
	{
		BigInteger dNum = eNum.modPow(d, N);
		return dNum;
	}
	
}
