import edu.duke.*;
import java.io.*;

public class InversionConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            int red = inPixel.getRed();
            int blue = inPixel.getBlue();
            int green = inPixel.getGreen();

            //set pixel's red to average
            pixel.setRed(255 -red);
            //set pixel's green to average
            pixel.setGreen(255 - green);
            //set pixel's blue to average
            pixel.setBlue(255 - blue);
        }
        //outImage is your answer
        return outImage;
    }

    public void testMakeInversion() {
        ImageResource ir = new ImageResource();
        ImageResource inverted = makeInversion(ir);
        inverted.draw();
    }
    
    public void testSelectAndConvert2 () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource inverted = makeInversion(inImage);
            inverted.setFileName(newName);
            inverted.draw();
            inverted.save();
        }
    }
}
