var origImage = null;
var editGrayImage = null;
var editRedImage = null;
var editRainbowImage = null;

function loadImage() {
  var elemInput = document.getElementById("click1");
  origImage = new SimpleImage(elemInput);
  editGrayImage = new SimpleImage(elemInput);
  editRedImage = new SimpleImage(elemInput);
  editRainbowImage = new SimpleImage(elemInput);  
  var elemCanvas = document.getElementById("can1");
  origImage.drawTo(elemCanvas);  
}

function makeGray() {
  if (editGrayImage == null || !editGrayImage.complete()) {
    alert("Image not loaded");
  } else {   
    for (var pixel of editGrayImage.values()) {
      var grayscale = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
      pixel.setRed(grayscale);
      pixel.setGreen(grayscale);
      pixel.setBlue(grayscale);
    }
    var elemCanvas = document.getElementById("can1");    
    editGrayImage.drawTo(elemCanvas); 
  }
}

function filterRed(pixel) {
  // Red
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(2*avg);
    pixel.setGreen(0);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(2*avg - 255);
    pixel.setBlue(2*avg - 255);
  }
  return pixel;
}

function filterOrange(pixel) {
  // Orange
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(2*avg);
    pixel.setGreen(0.8*avg);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(1.2*avg - 51);
    pixel.setBlue(2*avg - 255);
  }
  return pixel;
}

function filterYellow(pixel) {
  // Yellow
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(2*avg);
    pixel.setGreen(2*avg);
    pixel.setBlue(0);
  } else {
    pixel.setRed(255);
    pixel.setGreen(255);
    pixel.setBlue(2*avg - 255);
  }
  return pixel;
}

function filterGreen(pixel) {
  // Green
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(0);
    pixel.setGreen(2*avg);
    pixel.setBlue(0);
  } else {
    pixel.setRed(2*avg - 255);
    pixel.setGreen(255);
    pixel.setBlue(2*avg - 255);
  }
  return pixel;
}

function filterBlue(pixel) {
  // Blue
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(2*avg);
  } else {
    pixel.setRed(2*avg - 255);
    pixel.setGreen(2*avg - 255);
    pixel.setBlue(255);
  }
  return pixel;
}

function filterIndigo(pixel) {
  // Indigo
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(0.8*avg);
    pixel.setGreen(0);
    pixel.setBlue(2*avg);
  } else {
    pixel.setRed(1.2*avg - 51);
    pixel.setGreen(2*avg - 255);
    pixel.setBlue(255);
  }
  return pixel;
}

function filterViolet(pixel) {
  // Violet
  var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
  if (avg < 128) {
    pixel.setRed(1.6*avg);
    pixel.setGreen(0);
    pixel.setBlue(1.6*avg);
  } else {
    pixel.setRed(0.4*avg + 153);
    pixel.setGreen(2*avg - 255);
    pixel.setBlue(0.4*avg + 153);
  }
  return pixel;
}

function makeRed() {
  if (editRedImage == null || !editRedImage.complete()) {
    alert("Image not loaded");
  } else {   
    for (var pixel of editRedImage.values()) {
      // pixel.setRed(255);
      pixel = filterRed(pixel);
    }
    var elemCanvas = document.getElementById("can1");    
    editRedImage.drawTo(elemCanvas); 
  }
}

function makeRainbow() {
  if (editRainbowImage == null || !editRainbowImage.complete()) {
    alert("Image not loaded");
  } else {   
    var height = editRainbowImage.getHeight();
    for (var pixel of editRainbowImage.values()) {
      var y = pixel.getY();
      if (y < height/7) {
        // Red
        pixel = filterRed(pixel);       
      } else if (y < height*2/7) {
        // Orange
        pixel = filterOrange(pixel);
      } else if (y < height*3/7) {
        // Yellow
        pixel = filterYellow(pixel);
      } else if (y < height*4/7) {
        // Green
        pixel = filterGreen(pixel);
      } else if (y < height*5/7) {
        // blue
        pixel = filterBlue(pixel);
      } else if (y < height*6/7) {
        // indigo
        pixel = filterIndigo(pixel);           
      } else {
        //violet
        pixel = filterViolet(pixel);         
      }
    }
    var elemCanvas = document.getElementById("can1");    
    editRainbowImage.drawTo(elemCanvas); 
  }
}


function doReset() {
  if (origImage == null || !origImage.complete()) {
    alert("Image not loaded");
  } else {  
    var elemInput = document.getElementById("click1");
    editGrayImage = new SimpleImage(elemInput);    
    editRedImage = new SimpleImage(elemInput);    
    editRainbowImage = new SimpleImage(elemInput);      
    var elemCanvas = document.getElementById("can1");        
    origImage.drawTo(elemCanvas); 
  }
}