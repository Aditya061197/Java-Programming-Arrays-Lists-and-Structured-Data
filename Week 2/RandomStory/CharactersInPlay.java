/**
 * Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
  private ArrayList <String> characterNames;
  private ArrayList <Integer> count;
    
  public CharactersInPlay() {
    characterNames = new ArrayList<String>();
    count = new ArrayList<Integer>();
  }
    
  public void update(String person) {
    if (!characterNames.contains(person)) {
      characterNames.add(person);
      count.add(1);
    }
    else {
      int index = characterNames.indexOf(person);
      int value = count.get(index);
      count.set(index, value + 1);
    }
  }
    
  public void findAllCharacters() {
    FileResource fr = new FileResource();
    characterNames.clear();
    count.clear();
        
    for (String line : fr.lines()) {
      int idx = line.indexOf('.');
      if (idx == -1) continue;
      String person = line.substring(0, idx);
      update(person);
    }
  }
    
  public void tester() {
    findAllCharacters();
    for (int i = 0; i < characterNames.size(); i++) {
      if (count.get(i) > 1) {
        System.out.println(count.get(i) + "\t\t" + characterNames.get(i));
      }
    }
    System.out.println("--------------------------------------");
    charactersWithNumParts (10, 15);
  }
    
  public void charactersWithNumParts (int num1, int num2) {
    for (int i = 0; i < characterNames.size(); i++) {
      if (count.get(i) >= num1 && count.get(i) <= num2) {
        System.out.println(count.get(i) + "\t\t" + characterNames.get(i));
      }
    }
  }
    
  public static void main (String[] args) {
    CharactersInPlay c = new CharactersInPlay();
    c.tester();
  }
}
