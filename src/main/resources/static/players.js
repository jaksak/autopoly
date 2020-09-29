var sidebarInsert = document.getElementById('insertArea');

var sidebarContent="Hello World!";

sidebarInsert.innerHTML=sidebarContent;

function playersUpdate(a){
	var b="";
	for(i=0;i<(a.length);i++){
		b=b+a[i].nick+" <span class='moneyCount'> $"+a[i].moneyAmount+"</span><br>Position:"+a[i].position+"<br><br>";
	}
	sidebarInsert.innerHTML=b;
}