<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><input type="text" id="username" placeholder="username 입력"></div>
<div><input type="password" id="password" placeholder="password 입력"></div>
<div><input type="email" id="email" placeholder="email 입력"></div>
<div>
<button id="insertexecbtn">입력실행</button>
<button id="updateexecbtn" style="display:none">수정실행</button>
<button id="deleteexecbtn" style="display:none">삭제실행</button>
</div>
<hr>
<div><button id="selectAllexecbtn">전체 출력 실행</button></div>
<hr>
<div><input type="text" id="findusername" placeholder="username 입력"></div>
<div><button id="selectByUsernameexecbtn">검색 출력 실행</button></div>
<div id="result"></div>
<script>
let buttonId=null;
const execbtn = document.getElementById("insertexecbtn");
const div = document.getElementById("result");
const username = document.getElementById("username");
const password = document.getElementById("password");
const email = document.getElementById("email");

var xhr = new XMLHttpRequest();
xhr.onload = function() {
    if (xhr.status === 200  || xhr.status === 201) {
        const result = xhr.responseText;
        console.log(result);
        div.innerHTML=result;
             
        //입력을 했을 때
   		if(buttonId === "insertexecbtn" ){
   			console.log("입력을 했을 때");
   		} 
   		//전체 출력을 했을 때     
   		else if(buttonId === "selectAllexecbtn" ){
   			console.log("전체 출력을 했을 때");
   		} 
        //선택 출력을 했을 때
        else if(buttonId === "selectByUsernameexecbtn" ){
   			console.log("선택 출력을 했을 때");
   			let obj = JSON.parse(xhr.responseText);
   			username.value=obj["username"];
   			password.value=obj["password"];
   			email.value=obj["email"];
   			updateexecbtn.style.display="inline";
   			deleteexecbtn.style.display="inline";
   			insertexecbtn.style.display="none";
   			username.readOnly="true";
   		} 
        //수정을 했을 때
        else if(buttonId === "updateexecbtn" ){
   			console.log("수정을 했을 때");
   		} 
        //삭제를 했을 때
        else if(buttonId === "deleteexecbtn" ){
   			console.log("삭제를 했을 때");
   		} else{
   			console.log("그외 코드 일 때");
   		}
    }else{
    	console.error('요청 실패:',xhr.status);
    }
    buttonId=null;
};
xhr.onerror = function() {console.error('요청 중 오류 발생')}
execbtn.addEventListener("click", (event)=>{
	console.log(event.target.id);
	buttonId=event.target.id;
	xhr.open("post", "/api/member", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	let data=JSON.stringify({"username":username.value, 
			"password":password.value, 
			"email":email.value});
	xhr.send(data);
	
	username.value="";
	password.value="";
	email.value="";
	//xhr.onReadyStateChange
});

const selectAllexecbtn = document.getElementById("selectAllexecbtn");
selectAllexecbtn.addEventListener("click", (event)=>{
	console.log(event.target.id);
	buttonId=event.target.id;
	//http://localhost:8888/api/members
	xhr.open("GET", "/api/members", true);
	xhr.send();
});

const selectByUsernameexecbtn = document.getElementById("selectByUsernameexecbtn");
const findusername = document.getElementById("findusername");
selectByUsernameexecbtn.addEventListener("click", (event)=>{
	console.log(event.target.id);
	buttonId=event.target.id;
	//http://localhost:8888/api/member?username=xxx
	//http://localhost:8888/api/member/xxxxx
	/*첫번째 호출 방법*/
	
	xhr.open("GET", "/api/member?username="+findusername.value, true);
	xhr.send();
	
});

const updateexecbtn = document.getElementById("updateexecbtn");

updateexecbtn.addEventListener("click", (event)=>{
	console.log(event.target.id);
	buttonId=event.target.id;
	
    // JSON 객체 만들기
    const data = JSON.stringify({
        username: username.value,
        password: password.value,
        email: email.value
    });

    // AJAX 요청
    xhr.open("PUT", "/api/member", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            div.innerHTML = "<b>수정 성공:</b><br>" + xhr.responseText;
        } 
    };
    xhr.onerror = function() {console.error('요청 중 오류 발생')}
    xhr.send(data);
});

const deleteexecbtn = document.getElementById("deleteexecbtn");
deleteexecbtn.addEventListener("click", (event)=>{
	console.log(event.target.id);
	buttonId=event.target.id;
    xhr.open("DELETE", "/api/member?username=" + username.value);
    xhr.onload = function() {
    	 if (xhr.status === 200 || xhr.status === 201){
    		 div.innerHTML = "<b>삭제 성공</b>";
    	 }
    };
    xhr.onerror = function() {console.error('요청 중 오류 발생')}
    xhr.send();
});
</script>
</body>
</html>