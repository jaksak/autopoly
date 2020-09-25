var canvas = document.getElementById("main-canvas");
var ctx = canvas.getContext("2d");


ctx.fillStyle="#881212";
ctx.strokeStyle="#121288";
ctx.lineWidth=12;

ctx.strokeRect(20,20,400,240);
ctx.fillRect(20,20,400,240);

ctx.fillStyle="#128812";
ctx.strokeStyle="#ffee00";
ctx.strokeRect(60,60,200,120);
ctx.fillRect(60,60,200,120);