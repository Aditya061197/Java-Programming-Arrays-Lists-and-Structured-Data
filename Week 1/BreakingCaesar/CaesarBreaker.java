/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (19-07-2020)
 */

import edu.duke.*;

public class CaesarBreaker {
  public int [] countLetters (String resource) {
    int [] counts = new int [26];
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < resource.length(); i++) {
      char ch = Character.toLowerCase(resource.charAt(i));
      int index = alphabet.indexOf(ch);
      if (index != -1) {
        counts[index] += 1;
      }
    }
    return counts;
  }
    
  //make sure CaesarCipher.class is in the same folder
  public int maxIndex (int []  counts) {
    WordLengths wl = new WordLengths();
    return wl.indexOfMax(counts);
  }
    
  public String decrypt (String input) {
    CaesarCipher cc = new CaesarCipher();
    int [] frequency = countLetters(input);
    int maxIdx = maxIndex(frequency);
    int key = maxIdx - 4;
    if (maxIdx < 4) {
      key = 26 - (4 - maxIdx);
    }
    System.out.println("Key is " + key);
    return cc.encrypt(input, 26 - key);
  }
    
  public void testDecrypt () {
    FileResource fr = new FileResource();
    String message = fr.asString();
    CaesarCipher cc = new CaesarCipher();
    String encryptedMessage = cc.encrypt(message, 23);
    System.out.println(encryptedMessage);
    String decryptedMessage = decrypt(encryptedMessage);
    System.out.println(decryptedMessage);
  }
    
  public String halfOfString(String message, int start) {
    String output = "";
    for (int i = start; i < message.length(); i += 2) {
      output += message.charAt(i);
    }
    return output;
  }
    
  public int getKey (String s) {
    int [] frequency = countLetters(s);
    int maxIdx = maxIndex(frequency);
    if ( maxIdx < 4) {
      return 26 - (4 - maxIdx);
    }
    return maxIdx - 4;
  }
    
  public String decryptTwoKeys (String encrypted) {
    String odd = halfOfString(encrypted, 0);
    String even = halfOfString(encrypted, 1);
    int keyOdd = getKey(odd);
    int keyEven = getKey(even);
    System.out.println("key1: " + keyOdd + "\tkey2: " + keyEven);
    CaesarCipher cc = new CaesarCipher();
    return cc.encryptTwoKeys(encrypted, 26 - keyOdd, 26 - keyEven);
  }
    
  public void testDecryptTwoKeys() {
    FileResource fr = new FileResource();
    String message = fr.asString();
    CaesarCipher cc = new CaesarCipher();
    String encrypted = cc.encryptTwoKeys(message, 23, 2);
    System.out.println(decryptTwoKeys(encrypted));
  }
}
