var canvas = document.getElementById("main-canvas");
var ctx = canvas.getContext("2d");

var imgTest = document.getElementById("imgTest");

function drawimg(){
	
	ctx.drawImage(imgTest,0,0);
}

imgTest.onload=function(){
	setInterval(drawimg,200);
}
