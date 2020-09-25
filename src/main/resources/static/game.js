var canvas = document.getElementById("main-canvas");
var ctx = canvas.getContext("2d");

var test = document.getElementById("test");

function drawimg(){
	ctx.drawImage(test,0,0);
}

setInterval(drawimg,100);
