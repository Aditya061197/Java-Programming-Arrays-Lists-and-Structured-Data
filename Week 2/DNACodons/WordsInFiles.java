/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (23-07-2020)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    private HashMap <String, ArrayList<String>> files;
    
    public WordsInFiles() {
        files = new HashMap <String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        String name = f.getName();
        for (String content : fr.words()) {
            content = content.toLowerCase();
            if (files.containsKey(content) && !files.get(content).contains(name)) {
                ArrayList<String> fileName = files.get(content);
                fileName.add(name);
                files.put(content, fileName);
            }
            else if (!files.containsKey(content)) {
                ArrayList<String> newFileName = new ArrayList<String> ();
                newFileName.add(name);
                files.put(content, newFileName);
            }
        }
    }
    
    private void buildWordFileMap() {
        files.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber () {
        int max = 0;
        for (String word : files.keySet()) {
            ArrayList<String> fileNames = files.get(word);
            int arraySize = fileNames.size();
            if (arraySize > max) {
                max = arraySize;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> returnList = new ArrayList<String> ();
        for (String word : files.keySet()) {
            ArrayList<String> fileNames = files.get(word);
            int arraySize = fileNames.size();
            if (arraySize == number) {
                returnList.add(word);
            }
        }
        return returnList;
    }
    
    public void printFilesIn (String word) {
        ArrayList<String> fileNames = files.get(word);
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        ArrayList<String> maxFiles = wordsInNumFiles(max);
        for (int i = 0; i < maxFiles.size(); i++) {
            System.out.println("\n" + maxFiles.get(i) + ":");
            printFilesIn(maxFiles.get(i));
        }
        System.out.println("Maximum no. of files the wors is in: " + max + " in " + maxFiles.size() + " files");
        printFilesIn("laid");
        System.out.println(maxFiles.size());
    }
    
    public static void main (String[] args) {
        WordsInFiles w = new WordsInFiles();
        w.tester();
    }
}
