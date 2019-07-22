function doAlert() {
  alert('clicked button by JS');
}

function doConfirm() {
  var choice = confirm('clicked confirm button by JS');
  var message;
  if (choice==true) {
    message = "You pressed OK!";
  } else {
    message = "Are you sure you want to cancel?";
  }
  alert(message);
}

function changeColor() {
  var divElement1 = document.getElementById("div1");
  var divElement2 = document.getElementById("div2");
  divElement1.className = "blueback";
  divElement2.className = "orangeback"
}

function changeText() {
  var divElement1 = document.getElementById("div1");
  var divElement2 = document.getElementById("div2");
  divElement1.innerHTML = "Erste";
  divElement2.innerHTML = "Zweite";
}

function changeColor2() {
  document.getElementById("div1").style.color = "red";
  document.getElementById("div2").style.color = "blue"
}
function changeValue() {
  var dd1 = document.getElementById("but1");
  dd1.value = "New Value"
}