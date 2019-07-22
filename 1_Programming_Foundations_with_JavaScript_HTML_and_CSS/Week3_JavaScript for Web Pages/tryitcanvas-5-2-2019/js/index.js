function changeColor() {
  var canElement1 = document.getElementById("can1");
  var canElement2 = document.getElementById("can2");
  canElement1.className = "blueback";
  canElement2.className = "orangeback"
}

function doBlue() {
  var canElement1 = document.getElementById("can1");
  canElement1.style.backgroundColor="blue";
  var context = canElement1.getContext("2d");
  context.fillStyle = "yellow";
  context.fillRect(10,10,60,60);
  context.fillRect(80,10,60,60);
  
  context.fillStyle = "black";
  context.font = "20px Arial";
  context.fillText("Hello", 15, 45);
}

function doOrange() {
  var canElement1 = document.getElementById("can1");
  var context = canElement1.getContext("2d");
  context.clearRect(0,0,canElement1.width,canElement1.height);
  canElement1.style.backgroundColor="orange";
}