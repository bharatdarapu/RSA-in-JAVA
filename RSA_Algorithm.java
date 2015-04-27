package syr.rsa;

import java.util.Scanner;
import java.math.*;

public class RSA_Algorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner read = new Scanner(System.in);
		long p,q,e,d;
		long num,eNum,dNum;
		long N;
		System.out.println("Please enter the first Prime number:");
		p=read.nextLong();
		System.out.println("Please enter the Second Prime number:");
		q=read.nextLong();
		System.out.println("Please enter the number to be encrypted:");
		num=read.nextLong();
		N=p*q;
		e=encExponent(p,q);
		System.out.println("Encryption Exponenet:- "+e);
		//e=3533;
		eNum=encrypt(num,N,e);
		System.out.println("Encrypted number is:- "+eNum);
		d=decExponent(p,q,e);
		System.out.println("Encryption Exponenet:- "+d);
		dNum=decrypt(eNum,N,d);
		System.out.println("Decrypted number is:- "+dNum);
		read.close();
	}
	
	static long findGCD(long l1, long l2) {
	    if(l2 == 0){
	        return l1;
	    }
	    return findGCD(l2, l1%l2);
	}
	
	static long encExponent(long p,long q)
	{
		long encExp=0;
	    for(long e=2;;e++)
	    {
	    if(e==p||e==q)
	    e++;
	    if(findGCD(e,(p-1)*(q-1))==1)
	    {
	    encExp=e;
	    break;
	    }
		}
		return encExp;
	}
	
	static long decExponent(long p,long q, long e)
	{
	
		long N=(p-1)*(q-1);
		BigInteger NB = BigInteger.valueOf(N);
		BigInteger eB = BigInteger.valueOf(e);
		BigInteger eNum;
		
		eNum=eB.modInverse(NB);
		
		return eNum.longValue();
	}
	
	
	static long encrypt(long num,long N, long e)
	{
		long eNum;
		
		BigInteger eBig= BigInteger.valueOf(e);
		
		BigInteger big = (BigInteger.valueOf(num).pow(eBig.intValue())).mod(BigInteger.valueOf(N));
		eNum=big.longValue();
		return eNum;
	}
	
	static long decrypt(long eNum,long N, long d)
	{
		long dNum;
		
		BigInteger dBig= BigInteger.valueOf(d);
		
		BigInteger big = (BigInteger.valueOf(eNum).pow(dBig.intValue())).mod(BigInteger.valueOf(N));
		dNum=big.longValue();
		return dNum;
	}
	
}
