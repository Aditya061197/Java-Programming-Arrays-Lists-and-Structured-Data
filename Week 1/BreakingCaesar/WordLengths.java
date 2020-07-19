/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (19-07-2020)
 */

import edu.duke.*;

public class WordLengths {
  public void countWordLengths (FileResource resource, int [] counts) {
    for (String word : resource.words()) {
      int wordlength = 0;
      StringBuilder sb = new StringBuilder(word);
          
      for (int i = 0; i < sb.length(); i++) {
        if ((i == 0 && !Character.isLetter(sb.charAt(i))) || (i == sb.length() - 1 && !Character.isLetter(sb.charAt(i)))) {
          sb.deleteCharAt(i);
        }
        else {
          wordlength += 1;
        }
      }
      if (wordlength > counts.length) {
        counts[counts.length - 1] += 1;
      }
      else {
        counts[wordlength] += 1;
      }
    }
  }
    
  public void testCountWordLengths () {
    FileResource fr = new FileResource();
    int [] counts = new int [31];
    countWordLengths(fr, counts);
    for (int i = 0; i < counts.length; i++) {
      System.out.println("Word length: " + i + "\t\tCount: " + counts[i]);
    }
    System.out.println("Max. Occurence: " + indexOfMax(counts));
  }
    
  public int indexOfMax (int [] values) {
    int largest = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] > values[largest]) {
        largest = i;
      }
    }
    return largest;
  }
    
  public static void main (String[] args) {
    WordLengths wl = new WordLengths();
    wl.testCountWordLengths();
  }
}
