package syr.rsa;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Test
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    System.out.printf("Enter first Value:  ", new Object[0]);
    BigInteger p = in.nextBigInteger();
    System.out.printf("Enter second Value:  ", new Object[0]);
    BigInteger q = in.nextBigInteger();
    System.out.printf("Enter message to be encrypted:  ", new Object[0]);
    BigInteger msg = in.nextBigInteger();
    
    BigInteger n = p.multiply(q);
    BigInteger z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    


    BigInteger e = new BigInteger(String.valueOf(2));
    
    while ((z.gcd(e).compareTo(BigInteger.ONE) > 0) && (e.compareTo(z) < 0)) {
    e= e.add(BigInteger.ONE);
    }
    
    BigInteger d = e.modInverse(z);
    System.out.println("The original message is : " + msg);
    


    BigInteger c = msg.modPow(e, n);
    System.out.println("The encrypted message is : " + c);
    


    BigInteger msg2 = c.modPow(d, n);
    if (msg.equals(msg2))
    {
      System.out.println("Decryption successful....");
      System.out.println("The decrypted message is : " + msg2);
    }
  
    in.close();
  }
}
