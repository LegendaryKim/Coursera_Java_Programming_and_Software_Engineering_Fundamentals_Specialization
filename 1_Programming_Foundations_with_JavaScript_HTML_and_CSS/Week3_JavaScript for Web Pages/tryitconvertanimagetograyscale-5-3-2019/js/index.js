var image;
var image2;

function upload() {
  var imgcanvas = document.getElementById("can1");
  var fileinput = document.getElementById("finput1");
  image = new SimpleImage(fileinput);
  image2 = image
  image.drawTo(imgcanvas);
}

function makeGray() {
  var imgcanvas = document.getElementById("can2");  
  for (var pixel of image2.values()) {
    var grayscale = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
    pixel.setRed(grayscale);
    pixel.setGreen(grayscale);
    pixel.setBlue(grayscale);
  }
  image2.drawTo(imgcanvas);
}