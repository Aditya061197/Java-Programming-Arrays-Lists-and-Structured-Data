/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (23-07-2020)
 */

import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap <String, Integer> DNACodons;
    
    public CodonCount() {
        DNACodons = new HashMap <String, Integer> ();
    }
    
    public void buildCodonMap (int start, String dna) {
        DNACodons.clear();
        String key = "";
        for (int i = start; i < dna.length(); i += 3) {
            if ((i + 3) <= dna.length()) {
                key = dna.substring(i, i + 3);
            }
            else break;
            if (DNACodons.containsKey(key)) {
                DNACodons.put(key, DNACodons.get(key) + 1);
            }
            else {
                DNACodons.put(key, 1);
            }
        }
    }
    
    public String getMostCommonCodon () {
        String value = "";
        for (String s : DNACodons.keySet()) {
            if (value == "") {
                value = s;
            }
            if (DNACodons.get(s) > DNACodons.get(value)) {
                value = s;
            }
        }
        return value;
    }
    
    public void printCodonCounts (int start, int end) {
        for (String s : DNACodons.keySet()) {
            if (DNACodons.get(s) >= start && DNACodons.get(s) <= end) {
                System.out.println(s + "\t" + DNACodons.get(s));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String input = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, input);
            System.out.println("Reading frame starting with " + i + " results in " + DNACodons.size()
                                + " unique codons and most common codon is " + getMostCommonCodon()
                                + " with count " + DNACodons.get(getMostCommonCodon()));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 7);
        }
    }
    
    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
