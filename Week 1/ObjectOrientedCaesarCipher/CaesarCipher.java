/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (20-07-2020)
 */


public class CaesarCipher {
  private String alphabet;
  private String shiftedAlphabet;
  private int mainKey;
  
  public CaesarCipher (int key) {
    alphabet = "abcdefghijklmnopqrstuvwxyz";
    shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    mainKey = key;
  }
    
  public String encrypt (String input) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      if (Character.isAlphabetic(sb.charAt(i))) {
        int index = alphabet.indexOf(Character.toLowerCase(sb.charAt(i)));
        if (Character.isUpperCase(sb.charAt(i))) {
          sb.setCharAt(i, Character.toUpperCase(shiftedAlphabet.charAt(index)));
        }
        else {
          sb.setCharAt(i, shiftedAlphabet.charAt(index));
        }
      }
    }
    return sb.toString();
  }
    
  public String decrypt (String input) {
    CaesarCipher cc = new CaesarCipher(26 - mainKey);
    return cc.encrypt(input);
  }
    
  public static void main (String[] args) {
    CaesarCipher cc = new CaesarCipher(15);
    String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    System.out.println("Input: " + input);
    String encrypted = cc.encrypt(input);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + cc.decrypt(encrypted));
  }
}
