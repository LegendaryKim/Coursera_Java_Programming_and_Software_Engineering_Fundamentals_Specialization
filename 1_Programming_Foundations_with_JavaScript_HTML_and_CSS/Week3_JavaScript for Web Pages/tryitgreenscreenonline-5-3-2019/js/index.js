var fgImage = null;
var bgImage = null;

function loadForegroundImage() {
  var imgcanvas = document.getElementById("can1");
  var ImgInput = document.getElementById("Foreground");
  fgImage = new SimpleImage(ImgInput);
  fgImage.drawTo(imgcanvas);
}

function loadBackgroundImage() {
  var imgcanvas = document.getElementById("can2");
  var ImgInput = document.getElementById("Background");
  bgImage = new SimpleImage(ImgInput);
  bgImage.drawTo(imgcanvas);
}

function clearCanvas() {
  var imgcanvas1 = document.getElementById("can1");
  var imgcanvas2 = document.getElementById("can2");
  var context1 = imgcanvas1.getContext("2d");
  var context2 = imgcanvas2.getContext("2d");
  context1.clearRect(0,0,imgcanvas1.width, imgcanvas1.height);
  context2.clearRect(0,0,imgcanvas2.width, imgcanvas2.height);
}

function doGreenScreen() {
  if (fgImage == null || !fgImage.complete()) {
    alert("foreground not loaded");
  } else if (bgImage == null || !bgImage.complete()) {
  } else if (fgImage.getWidth() != bgImage.getWidth() || fgImage.getHeight() != bgImage.getHeight()) {
    alert("Dimesions are not matched");    
  } else {  
    var output = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());

    clearCanvas();
    
    var imgcanvas1 = document.getElementById("can1");

    for (var pixel of fgImage.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        if (pixel.getGreen() > pixel.getRed() + pixel.getBlue()) {
            output.setPixel(x,y, bgImage.getPixel(x,y));
        }
        else {
            output.setPixel(x,y, pixel);
        }
    }
    output.drawTo(imgcanvas1);
  }
}