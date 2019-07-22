function upload() {
  var fileinput = document.getElementById("finput1");
  var filename = fileinput.value;
  alert("You chose " + filename);
}

function upload2() {
  var imgcanvas = document.getElementById("can1");
  var fileinput = document.getElementById("finput2");
  // alert("You chose " + fileinput.value);
  var image = new SimpleImage(fileinput);
  image.drawTo(imgcanvas);
}