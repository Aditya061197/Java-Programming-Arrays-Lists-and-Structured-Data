/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (20-07-2020)
 */


import edu.duke.*;

public class TestCaesarCipherTwo {
  public void simpleTests () {
    FileResource fr = new FileResource();
    String input = fr.asString();
    CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
    String encrypted = cct.encrypt(input);
    System.out.println("Encrypt: " + encrypted);
    System.out.println("Decrypted: " + cct.decrypt(encrypted));
        
    System.out.println(breakCaesarCipher(input));
  }
    
  public String breakCaesarCipher (String input) {
    CaesarBreaker cb = new CaesarBreaker();
    String odd = cb.halfOfString(input, 0);
    String even = cb.halfOfString(input, 1);
    int keyOdd = cb.getKey(odd);
    int keyEven = cb.getKey(even);
    System.out.println("Key1: " + keyOdd + "\tKey2: " + keyEven);
    CaesarCipherTwo cct = new CaesarCipherTwo(26 - keyOdd, 26 - keyEven);
    return cct.encrypt(input);
  }
    
  public static void main (String[] args) {
    TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
    tcct.simpleTests();
  }
}
