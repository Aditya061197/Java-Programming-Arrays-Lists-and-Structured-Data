/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (20-07-2020)
 */


import edu.duke.*;

public class TestCaesarCipher {
  public void simpleTests () {
    FileResource fr = new FileResource();
    String input = fr.asString();
    CaesarCipher cc = new CaesarCipher(18);
    String encrypted = cc.encrypt(input);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + cc.decrypt(encrypted));
        
    System.out.println(breakCaesarCipher(encrypted));
  }
    
  public String breakCaesarCipher (String input) {
    CaesarBreaker cb = new CaesarBreaker();
    int []  frequency = cb.countLetters(input);
    int maxIdx = cb.maxIndex(frequency);
    int key = maxIdx - 4;
    if (maxIdx < 4) {
      key = 26 - (4 - maxIdx);
    }
    System.out.println("Key is " + key);
    CaesarCipher cc = new CaesarCipher(26 - key);
    return (cc.encrypt(input));
  }
    
  public static void main (String[] args) {
    TestCaesarCipher tcc = new TestCaesarCipher();
    tcc.simpleTests();
  }
}
