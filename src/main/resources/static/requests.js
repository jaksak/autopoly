var gBStateButton = document.getElementById('getBoardStateButton');
var gBConfigButton = document.getElementById('getBoardConfigButton');

var boardID = "string";
var parcelsData;

const BASE_API_PATH = 'https://autopoly.herokuapp.com:443';

/* GET BOARD INIT STATE API REQUEST -beginning-*/
window.onload = function () {
	var requestURL = BASE_API_PATH + '/board/config';
	var doAfterResponse = function (a) {
		boardID = a.boardId;
		parcelsData = a.fields;
		namesSplited = false;
		playersUpdate(a.players);
	};
	universalRequestFunction(requestURL, doAfterResponse);
}
gBConfigButton.onclick=function() {
	var requestURL = BASE_API_PATH + '/board/config';
	var doAfterResponse = function (a) {
		boardID = a.boardId;
		parcelsData = a.fields;
		namesSplited = false;
		playersUpdate(a.players);
		alert((JSON.stringify(a)));
	};
	universalRequestFunction(requestURL, doAfterResponse);
}
/* GET BOARD INIT STATE API REQUEST -end-*/

/* GET CURRENT BOARD STATE API REQUEST -beginning-*/
gBStateButton.onclick=function() {
	var requestURL = BASE_API_PATH + '/board/state?boardId=' + boardID;
	var doAfterResponse = function (a) {
		playersUpdate(a.players);
		alert((JSON.stringify(a)));
	};
	universalRequestFunction(requestURL, doAfterResponse);
}
/* GET CURRENT BOARD STATE API REQUEST -end-*/

/* UNIVERSAL REQUEST FUNCTION -beginning-*/
function universalRequestFunction(requestURL,doAfterResponse){
		var serverResponse;
        var xhttp= new XMLHttpRequest();
        xhttp.onreadystatechange= function() {
            if(this.readyState==4 && this.status==200){
                serverResponse=this.responseText;
				var a=JSON.parse(serverResponse);
				doAfterResponse(a);
            }
        };
        xhttp.open("GET", requestURL, true);
        xhttp.send();  
}
/* UNIVERSAL REQUEST FUNCTION -end-*/