
/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (24-07-2020)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs () {
         ArrayList<String> uniqueIPs = new ArrayList<String> ();
         for (LogEntry le : records) {
             String IP = le.getIpAddress();
             if (!uniqueIPs.contains(IP)) {
                 uniqueIPs.add(IP);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num) {
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
         ArrayList<String> uniqueVisits = new ArrayList<String> ();
         for (LogEntry le : records) {
             String access = le.getAccessTime().toString();
             String IP = le.getIpAddress();
             if (access.contains(someday) && !uniqueVisits.contains(IP)) {
                 uniqueVisits.add(le.getIpAddress());
             }
         }
         return uniqueVisits;
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> unique = new ArrayList<String> ();
         for (LogEntry le : records) {
             String IP = le.getIpAddress();
             int status = le.getStatusCode();
             if (((status >= low) && (status <= high)) && !unique.contains(IP)) {
                 unique.add(IP);
             }
         }
         return unique.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP () {
         HashMap<String, Integer> counts = new HashMap<String, Integer> ();
         for (LogEntry le : records) {
             String IP = le.getIpAddress();
             if (!counts.containsKey(IP)) {
                 counts.put(IP, 1);
             }
             else {
                 counts.put(IP, counts.get(IP) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP (HashMap<String, Integer> counts) {
         int max = 0;
         for (String key : counts.keySet()) {
             if(counts.get(key) > max) {
                 max = counts.get(key);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
         int max = mostNumberVisitsByIP(counts);
         ArrayList<String> IPs = new ArrayList<String>();
         for (String key : counts.keySet()) {
             if (counts.get(key) == max) {
                 IPs.add(key);
             }
         }
         return IPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> IPMap = new HashMap<String, ArrayList<String>> ();
         for (LogEntry le : records) {
             String date = le.getAccessTime().toString().substring(4, 10);
             String IP = le.getIpAddress();
             if (!IPMap.containsKey(date)) {
                 ArrayList<String> logs = new ArrayList<String> ();
                 logs.add(IP);
                 IPMap.put(date, logs);
             }
             else {
                 IPMap.get(date).add(IP);
                 IPMap.put(date, IPMap.get(date));
             }
         }
         return IPMap;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> IPMap) {
         int max = 0;
         String day = "";
         for (String key : IPMap.keySet()) {
             int size = IPMap.get(key).size();
             if (size > max) {
                 day = key;
             }
         }
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IPMap, String someday) {
         ArrayList<String> IPsOnDay = IPMap.get(someday);
         HashMap <String, Integer> count = new HashMap<String, Integer> ();
         for (int i = 0; i < IPsOnDay.size(); i++) {
             String key = IPsOnDay.get(i);
             if (!count.containsKey(key)) {
                 count.put(key, 1);
             }
             else {
                 count.put(key, count.get(key) + 1);
             }
         }
         return iPsMostVisits(count);
     }
}
