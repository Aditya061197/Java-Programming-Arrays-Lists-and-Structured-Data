/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (20-07-2020)
 */


import edu.duke.*;

public class CaesarCipherTwo {
  private String alphabet;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private int mainKey1;
  private int mainKey2;
    
  public CaesarCipherTwo (int key1, int key2) {
    alphabet = "abcdefghijklmnopqrstuvwxyz";
    shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    mainKey1 = key1;
    mainKey2 = key2;
  }
    
  public String encrypt (String input) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      if (Character.isAlphabetic(sb.charAt(i))) {
        int index = alphabet.indexOf(Character.toLowerCase(sb.charAt(i)));
        if (i % 2 == 0) {
          if (Character.isUpperCase(sb.charAt(i))) {
            sb.setCharAt(i, Character.toUpperCase(shiftedAlphabet1.charAt(index)));
          }
          else {
            sb.setCharAt(i, shiftedAlphabet1.charAt(index));
          }
        }
        else {
          if (Character.isUpperCase(sb.charAt(i))) {
            sb.setCharAt(i, Character.toUpperCase(shiftedAlphabet2.charAt(index)));
          }
          else {
            sb.setCharAt(i, shiftedAlphabet2.charAt(index));
          }
        }
      }
    }
    return sb.toString();
  }
    
  public String decrypt (String input) {
    CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
    return cct.encrypt(input);
  }
    
  public static void main (String[] args) {
    CaesarCipherTwo cct = new CaesarCipherTwo (14, 24);
    FileResource fr = new FileResource();
    String input = fr.asString();
    String encrypted = cct.encrypt(input);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + cct.decrypt(encrypted));
  }
}
