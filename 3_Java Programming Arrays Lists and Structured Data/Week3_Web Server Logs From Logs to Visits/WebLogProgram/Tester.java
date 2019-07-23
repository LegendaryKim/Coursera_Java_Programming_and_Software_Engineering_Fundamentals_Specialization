
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testCountUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are unique " + uniqueIPs + " IPs");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        la.readFile("weblog1_log");
        System.out.println("bigger status code than 400: ");
        la.printAllHigherThanNum(400);        
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        // la.readFile("weblog-short_log");
        la.readFile("weblog2_log");
        // System.out.println("Sep 14: " + la.uniqueIPVisitsOnDay("Sep 14").size());
        // System.out.println("Sep 30: " + la.uniqueIPVisitsOnDay("Sep 30").size());   
        String dateStr = "Sep 27";
        System.out.println(dateStr + ": " + la.uniqueIPVisitsOnDay(dateStr).size());        
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        System.out.println("200-299: " + la.countUniqueIPsInRange(200, 299));
        //System.out.println("300-399: " + la.countUniqueIPsInRange(300, 399));
    }
 
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);       
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        for (String date: ipDays.keySet()) {
            System.out.println("Date : " + date);
            System.out.println(ipDays.get(date));
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println("day with most IP visits: " + la.dayWithMostIPVisits(ipDays));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        String dateStr = "Sep 30";
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println("iPs with Most Visits On Day " + dateStr + " :" 
                           + la.iPsWithMostVisitsOnDay(ipDays, dateStr));
    }
}
