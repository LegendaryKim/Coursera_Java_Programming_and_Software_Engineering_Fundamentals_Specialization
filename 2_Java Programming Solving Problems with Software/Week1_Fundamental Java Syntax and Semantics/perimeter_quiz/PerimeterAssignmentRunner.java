import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int countPt = 0;
        for (Point point : s.getPoints()) {
            countPt = countPt + 1;
        }
        return countPt;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int countPt = getNumPoints(s);
        double length = getPerimeter(s);
        return length / (double) countPt;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = currPt.distance(prevPt);
            if (largestSide < currDist) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        int largestX = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            int currX = currPt.getX();
            if (largestX < currX) {
                largestX = currX;
            }
            prevPt = currPt;
        }
        return (double) largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double LargestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (LargestPerimeter < currPerimeter) {
                LargestPerimeter = currPerimeter;
            }
        }
        return LargestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double LargestPerimeter = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (LargestPerimeter < currPerimeter) {
                LargestPerimeter = currPerimeter;
                temp = f;
            }
        }      
        //File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int countPt = getNumPoints(s);
        System.out.println("num of points = " + countPt);
        double avgLength = getAverageLength(s);
        System.out.println("Average Length = " + avgLength);
        double LargestSide = getLargestSide(s);
        System.out.println("Largest Side = " + LargestSide);
        double LargestX = getLargestX(s);
        System.out.println("Largest X = " + LargestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double LargestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largetst Perimeter = " + LargestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String FileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter = " 
            + FileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
