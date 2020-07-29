import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb = sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker c = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            key[i] = c.getKey(sliceString(encrypted, i, klength));
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        DirectoryResource dictionaries = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>> ();
        for (File f : dictionaries.selectedFiles()) {
            FileResource lang = new FileResource(f);
            languages.put(f.getName(), readDictionary(lang));
        }
        //System.out.println(breakForLanguage(input, dict));
        //int [] key = tryKeyLength(input, 38, 'e');
        //VigenereCipher v = new VigenereCipher(key);
        //String decrypted = v.decrypt(input);
        //System.out.println(countWords(decrypted, dict));
        System.out.println(breakForAllLangs(input, languages));
    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String lines : fr.lines()) {
            lines = lines.toLowerCase();
            dict.add(lines);
        }
        return dict;
    }
    
    public int countWords (String message, HashSet<String> dict) {
        String [] words = message.split("\\W+");
        int times = 0;
        for (int i = 0; i < words.length; i++) {
            if (dict.contains(words[i].toLowerCase())) {
                times++;
            }
        }
        return times;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dict) {
        int max = 0;
        int klength = 0;
        String decrypted = "";
        for (int i = 1; i <= 100; i++) {
            int [] key = tryKeyLength(encrypted, i, mostCommonCharIn(dict));
            VigenereCipher v = new VigenereCipher(key);
            String currDecrypt = v.decrypt(encrypted);
            int currMax = countWords(currDecrypt, dict);
            if (currMax > max) {
                max = currMax;
                klength = i;
                decrypted = currDecrypt;
            }
        }
        System.out.println("Max. counts: " + max);
        System.out.println("Key length: " + klength);
        return decrypted;
    }
    
    public char mostCommonCharIn (HashSet<String> dict) {
        char maxOccur = ' ';
        int max = -1;
        HashMap <Character, Integer> counts = new HashMap <Character, Integer> ();
        for (String word : dict) {
            word = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if (Character.isAlphabetic(currChar)) {
                    counts.put(currChar, counts.getOrDefault(currChar, 0) + 1);
                }
            }
        }
        for (char c : counts.keySet()) {
            if (counts.get(c) > max) {
                max = counts.get(c);
                maxOccur = c;
            }
        }
        return maxOccur;
    }
    
    public String breakForAllLangs (String encrypted, HashMap <String, HashSet<String>> languages) {
        String decrypted = "";
        String lang = "";
        int max = 0;
        for (String language : languages.keySet()) {
            HashSet<String> wordsInLang = languages.get(language);
            String currDecrypt = breakForLanguage(encrypted, wordsInLang);
            int counts = countWords(currDecrypt, wordsInLang);
            if (counts > max) {
                max = counts;
                decrypted = currDecrypt;
                lang = language;
            }
        }
        System.out.println("Language: " + lang);
        return decrypted;
    }
}
