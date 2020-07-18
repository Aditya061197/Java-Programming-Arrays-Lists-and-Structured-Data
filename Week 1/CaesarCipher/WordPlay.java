/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (18-07-2020)
 */
 
 
public class WordPlay {
  public boolean isVowel (char ch) {
    if (Character.isLowerCase(ch)) {
      ch = Character.toUpperCase(ch);
    }
    String vowels = "AEIOU";
    if (vowels.contains(Character.toString(ch))) {
      return true;
    }
    else {
      return false;
    }
  }
    
  public String replaceVowels (String phrase, char ch) {
    StringBuilder sb = new StringBuilder(phrase);
    for (int i = 0; i < sb.length(); i++) {
      if (isVowel(sb.charAt(i))) {
        sb.setCharAt(i, ch);
      }
    }
    return sb.toString();
  }
    
  public String emphasize (String phrase, char ch) {
    StringBuilder sb = new StringBuilder(phrase);
    for (int i = 0; i < sb.length(); i++) {
      char currCh = sb.charAt(i);
      if (Character.toUpperCase(currCh) == ch || Character.toLowerCase(currCh) == ch) {
        if ((i % 2) == 0) {
          sb.setCharAt(i, '*');
        }
        else {
          sb.setCharAt(i, '+');
        }
      }
    }
    return sb.toString();
  }
   
  public static void main (String[] args) {
    WordPlay wp = new WordPlay();
    System.out.println(wp.isVowel('a'));
    System.out.println(wp.replaceVowels("Hello World", '*'));
    System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
  }
}
