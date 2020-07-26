/**
 * Write a description here.
 * 
 * @author (Aditya Kedia)
 * @date (21-07-2020)
 */

import edu.duke.*;
import java.util.*;

public class GladLibsMap {
    private HashMap <String, ArrayList<String>> wordMap = new HashMap <String, ArrayList<String>> ();
    private ArrayList<String> wordUsed;
    private int wordsReplaced;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibsMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordUsed = new ArrayList<String> ();
        wordsReplaced = 0;
    }
    
    public GladLibsMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "animal", "color", "country", "fruit", "name", "noun", "timeframe", "verb"};
        for (String s : labels) {
            ArrayList <String> listOfWords = readIt(source + "/" + s + ".txt");
            wordMap.put(s, listOfWords);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (wordUsed.indexOf(label) == -1) {
            wordUsed.add(label);
        }
        return randomFrom(wordMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = null;
        while (true) {
            sub = getSubstitute(w.substring(first + 1, last));
            int idx = wordUsed.indexOf(sub);
            if (idx == -1) {
                wordUsed.add(sub);
                wordsReplaced += 1;
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private int totalWordsInMap () {
        int total = 0;
        for (String word : wordMap.keySet()) {
            ArrayList <String> words = wordMap.get(word);
            System.out.println("Category: " + word + "\tTotal words: " + words.size());
            total += words.size();
        }
        System.out.println("Lists size: " + total);
        return total;
    }
    
    private int totalWordsConsidered () {
        ArrayList<String> content = new ArrayList<String> ();
        int total = 0;
        System.out.println("Labels used in the story:");
        for (int i = 0; i < wordUsed.size(); i++) {
            String label = wordUsed.get(i);
            content = wordMap.get(label);
            System.out.println("label: " + label + "\tWords: " + content.size());
            total += content.size();
        }
        System.out.println("Sum of possible words: " + total);
        return total;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        wordUsed.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nNo. of words replaced: " + wordsReplaced);
        wordsReplaced = 0;
        System.out.println("\n" + totalWordsInMap());
        System.out.println(totalWordsConsidered());
    }
    
    public static void main (String[] args) {
        GladLibsMap g = new GladLibsMap();
        g.makeStory();
    }
}
