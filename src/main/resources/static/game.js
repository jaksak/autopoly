var canvas = document.getElementById("main-canvas");
var ctx = canvas.getContext("2d");

var board = document.getElementById("boardImg");

ctx.fillStyle="#121212";
ctx.strokeStyle="#121212";
ctx.font="9px RobotoCondensedBold";

function update(){
	ctx.clearRect(0,0,720,720);
	ctx.drawImage(board,0,0);
	if(parcelsData){
		drawParcelsData();
	};
}

board.onload=function(){
	setInterval(update,200);
}

var namesSplited=false;

function drawParcelsData(){
	
	if(!namesSplited){
		for(i=0;i<(parcelsData.length);i++){
		if(parcelsData[i].type=="PARCEL"){
			
			var a0=parcelsData[i].name;
			
			if(!namesSplited){
				var str=(a0).toString();
				a0=str.split(" ");
			}
			
			if((a0[1]=="rondo")||(a0[1]=="aleja")||(a0[1]=="pasaż")){a0.shift();};
			
			var toWrite=[];
			toWrite.length=0;
			var y=0;
			
			for(i2=0;i2<(a0.length);i2++){
				
				var w1=ctx.measureText(a0[i2]);
				var w2=ctx.measureText(a0[(i2+1)]);
				var w3=ctx.measureText(a0[(i2+2)]);
				
				if((a0[(i2+1)])&&((w1.width+w2.width)<50)){
					toWrite[y]=a0[i2]+" "+a0[(i2+1)];
					if((a0[(i2+2)])&&((w1.width+w2.width+w3.width)<50)){
						toWrite[y]=toWrite[y]+" "+a0[(i2+2)];
						i2++;
					}
					i2++;
				}
				else{toWrite[y]=a0[i2];};
				y++;
			};
			
			a0.length=0;
			a0=toWrite.splice(0,(toWrite.length));
			(parcelsData[i].name).length=0;
			parcelsData[i].name=[];
			
			
			for(i3=0;i3<a0.length;i3++){
				/*WRITE STREET NAMES ON CENTER ---DISABLED--- (for test only)
				var end="";
				var moveRight=1;
				var resetTop=0;
				if(i3==(a0.length-1)){end=parcelsData[i].price;};
				if(i>20){moveRight=2;resetTop=400;};
				ctx.fillText(a0[i3]+" "+end,(200+80*moveRight),(20*i-resetTop+i3*10+120));
				*/
				(parcelsData[i].name)[i3]=(a0[i3]);
			}
		}
		
	};
	namesSplited=true;	
	}
	else{
		for(i=0;i<(parcelsData.length);i++){
			var xc=570;/*X coordinate*/
			var yc=630;/*Y coordinate*/
			var xsh=60;/*X shift per tile*/
			var ysh=90;/*Y shift per tile*/
			/*ctx.strokeStyle="#ff0000"
			ctx.strokeRect((xc-(i*xsh)),yc,xsh,ysh);*/
			
			if(parcelsData[i].type=="PARCEL"){
				
				var tp=parcelsData[i].price+"$";/*text price*/
				var tpw=(ctx.measureText(tp)).width;/*text price width*/
				var pmargin=(xsh-tpw)/2;/*price text margin*/
				
				for(it=0;it<((parcelsData[i].name).length);it++){
					var t0=parcelsData[i].name;/*text parcel name*/
					var t0w=(ctx.measureText(t0[it])).width;/*text parcel name width*/
					var margin=(xsh-t0w)/2;/* parcel name margin*/
					ctx.save();
					if(i<10){
						ctx.fillText(t0[it],(xc-((i-1)*xsh)+margin),yc+((it+1)*9)+35);
						ctx.fillText(tp,(xc-((i-1)*xsh)+pmargin),yc+9+76);
					}
					else if(i>10&&i<=19){
						ctx.rotate(90*Math.PI/180);
						ctx.fillText(t0[it],(xc-((i-1)*xsh)+margin+600),yc+((it+1)*9)+35-720);
						ctx.fillText(tp,(xc-((i-1)*xsh)+pmargin+600),yc+9+76-720);
						ctx.restore();
					}
					else if(i>19&&i<=29){
						ctx.rotate(180*Math.PI/180);
						ctx.fillText(t0[it],(xc-((i-1)*xsh)+margin+480),yc+((it+1)*9)+35-720);
						ctx.fillText(tp,(xc-((i-1)*xsh)+pmargin+480),yc+9+76-720);
						ctx.restore();
					}
					else if(i>29){
						ctx.rotate(270*Math.PI/180);
						ctx.fillText(t0[it],(xc-((i-1)*xsh)+margin+1080),yc+((it+1)*9)+35);
						ctx.fillText(tp,(xc-((i-1)*xsh)+pmargin+1080),yc+9+76);
						ctx.restore();
					};	
				};
			};
		};
	};
};