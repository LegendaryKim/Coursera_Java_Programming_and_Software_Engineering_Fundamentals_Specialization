function doBlue() {
  var canvas1 = document.getElementById("can1");
  canvas1.style.backgroundColor = "blue"
}

function docolor() {
  var canvas1 = document.getElementById("can1");
  var colorinput = document.getElementById("clr1");
  var color = colorinput.value;
  canvas1.style.backgroundColor = color;
}

function doSquare() {
  var rangeinput = document.getElementById("range1");
  var len = rangeinput.value;
  var canvas1 = document.getElementById("can1");
  var context = canvas1.getContext("2d")
  context.clearRect(0,0,canvas1.width, canvas1.height);
  context.fillStyle = "red";  
  context.fillRect(10,10,len,len);
  context.fillRect(parseInt(len)+20,10,len,len);
}