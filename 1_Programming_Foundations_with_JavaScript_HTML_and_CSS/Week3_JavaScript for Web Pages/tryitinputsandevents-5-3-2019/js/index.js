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