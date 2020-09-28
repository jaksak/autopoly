var gBStateButton= document.getElementById('getBoardStateButton');
var gBConfigButton= document.getElementById('getBoardConfigButton');

var boardID="string";

var initConfigData="empty";
var boardStateData="empty";


/* GET BOARD INIT STATE API REQUEST -beginning-*/
function gBConfigRequest(){
	
    var requestURL = 'https://autopoly.herokuapp.com:443/board/config';
    
    function getResponse(){
        var xhttp= new XMLHttpRequest();
        xhttp.onreadystatechange= function() {
            if(this.readyState==4 && this.status==200){
                initConfigData=this.responseText;
				alert(initConfigData);
            }
        };
        xhttp.open("GET", requestURL, true);
        xhttp.send();    
    }
	
    getResponse();  
}
gBConfigButton.onclick=function(){gBConfigRequest();};

/* GET BOARD INIT STATE API REQUEST -end-*/

/* GET CURRENT BOARD STATE API REQUEST -beginning-*/
function gBStateRequest(){
	
    var requestURL = 'https://autopoly.herokuapp.com:443/board/state?boardId='+boardID;
    
    function getResponse(){
        var xhttp= new XMLHttpRequest();
        xhttp.onreadystatechange= function() {
            if(this.readyState==4 && this.status==200){
                boardStateData=this.responseText;
				alert(boardStateData);
            }
        };
        xhttp.open("GET", requestURL, true);
        xhttp.send();    
    }
	
    getResponse();   
}
gBStateButton.onclick=function(){gBStateRequest();};

/* GET CURRENT BOARD STATE API REQUEST -end-*/