/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (18-07-2020)
 */
public class CaesarCipher {
  public String encrypt (String input, int key) {
    StringBuilder sb = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String newAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    for (int i = 0; i < sb.length(); i++) {
      char currChar = sb.charAt(i);
      int index = 0;
      if (Character.isAlphabetic(currChar)) {
        if (Character.isLowerCase(currChar)) {
          index = alphabet.toLowerCase().indexOf(currChar);
          sb.setCharAt(i, newAlphabet.toLowerCase().charAt(index));
        }
        else {
          index = alphabet.indexOf(currChar);
          sb.setCharAt(i, newAlphabet.charAt(index));
        }
      }
    }
    return sb.toString();
  }
    
  public void testCaesar () {
    FileResource fr = new FileResource();
    String message = fr.asString();
    int key = 15;
    String encrypted = encrypt(message, key);
    System.out.println("key is " + key + "\n" + encrypted);
  }
    
  public String encryptTwoKeys (String input, int key1, int key2) {
    StringBuilder sb = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String newAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    String newAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    for (int i = 0; i < sb.length(); i++) {
      char currChar = sb.charAt(i);
      int index = 0;
      if (Character.isAlphabetic(currChar)) {
        if ((i % 2) == 0) {
          if (Character.isLowerCase(currChar)) {
            index = alphabet.toLowerCase().indexOf(currChar);
            sb.setCharAt(i, newAlphabet1.toLowerCase().charAt(index));
          }
          else {
            index = alphabet.indexOf(currChar);
            sb.setCharAt(i, newAlphabet1.charAt(index));
          }
        }
        else {
          if (Character.isLowerCase(currChar)) {
            index = alphabet.toLowerCase().indexOf(currChar);
            sb.setCharAt(i, newAlphabet2.toLowerCase().charAt(index));
          }
          else {
            index = alphabet.indexOf(currChar);
            sb.setCharAt(i, newAlphabet2.charAt(index));
          }
        }
      }
    }
    return sb.toString();
  }
                    
    
  public static void main (String[] args) {
    CaesarCipher cc = new CaesarCipher();
    cc.testCaesar();
    //System.out.println(cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
  }
}
