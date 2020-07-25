/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @22-07-2020)
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
  private ArrayList <String> myWords;
  private ArrayList <Integer> myFreqs;
    
  public WordFrequencies () {
    myWords = new ArrayList<String> ();
    myFreqs = new ArrayList<Integer> ();
  }
    
  public void findUnique () {
    myWords.clear();
    myFreqs.clear();
     
    FileResource fr = new FileResource();
    for (String word : fr.words()) {
      word = word.toLowerCase();
      int index = myWords.indexOf(word);
      if ( index == -1) {
        myWords.add(word);
        myFreqs.add(1);
      }
      else {
        int value = myFreqs.get(index);
        myFreqs.set(index, value + 1);
      }
    }
  }
    
  public void tester () {
    findUnique();
    System.out.println("No. of unique words: " + myWords.size());
    for (int i = 0; i < myWords.size(); i++) {
      System.out.println(myWords.get(i) + "\t\t" + myFreqs.get(i));
    }
    int maxIdx = findIndexOfMax();
    System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIdx) + "-->" + myFreqs.get(maxIdx));
    System.out.println(myWords.size());
  }
    
  public int findIndexOfMax () {
    int maxIdx = 0;
    for (int i = 0; i < myFreqs.size(); i++) {
      if (myFreqs.get(i) > myFreqs.get(maxIdx)) {
        maxIdx = i;
      }
    }
    return maxIdx;
  }
    
  public static void main (String[] args) {
    WordFrequencies wf = new WordFrequencies();
    wf.tester();
  }
}
