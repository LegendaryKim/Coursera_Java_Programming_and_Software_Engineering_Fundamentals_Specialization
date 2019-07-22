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