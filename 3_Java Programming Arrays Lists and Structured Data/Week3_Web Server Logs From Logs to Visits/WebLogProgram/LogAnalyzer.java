
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line: fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le: records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            Date d = le.getAccessTime();
            String str = d.toString();
            String ipAddr = le.getIpAddress();
            if (str.contains(someday) && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
             int status = le.getStatusCode();
             String ipAddr = le.getIpAddress();
             if (status >= low && status <= high && !uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        int mostCount = 0;
        for (int count: counts.values()) {
            if (mostCount < count) {
                mostCount = count;
            }
        }
        return mostCount;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        ArrayList<String> iPsList = new ArrayList<String>();
        int mostCount = mostNumberVisitsByIP(counts);
        for (String ip: counts.keySet()) {
            if (counts.get(ip) == mostCount) {
                iPsList.add(ip);
            }
        }
        return iPsList;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dateIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            Date d = le.getAccessTime();
            String dateStr = d.toString().substring(4,10);
            String ipAddr = le.getIpAddress();
            if (!dateIPs.containsKey(dateStr)) {
                ArrayList<String> iPs = new ArrayList<String>();
                iPs.add(ipAddr);
                dateIPs.put(dateStr, iPs);
            } else {
                ArrayList<String> iPs = dateIPs.get(dateStr);
                iPs.add(ipAddr);
                dateIPs.put(dateStr, iPs);                
            }
        }
        return dateIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateIPs) {
        String dayMostVisits = null;
        int mostNumVisits = 0;
        for (String dateStr: dateIPs.keySet()){
            int numVisits = dateIPs.get(dateStr).size();
            if (mostNumVisits < numVisits) {
                dayMostVisits = dateStr;
                mostNumVisits = numVisits;
            }
        }
        return dayMostVisits;        
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateIPs, String dateStr) {
        ArrayList<String> IPs = dateIPs.get(dateStr);
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip: IPs) {
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        
        int mostVisitNum = mostNumberVisitsByIP(counts);
        ArrayList<String> iPsMostVisits = new ArrayList<String>();
        for (String ip: counts.keySet()) {
            int currentVisitNum = counts.get(ip);
            if (mostVisitNum == currentVisitNum) {
                iPsMostVisits.add(ip);
            }
        }
        return iPsMostVisits;
    }
}
