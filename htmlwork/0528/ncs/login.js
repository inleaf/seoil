
function loginOpen(){
    let data=document.getElementById("article");
    data.innerHTML=`
    <div class="containter text-center">
        <h3>로그인</h3>
    </div>
    <div class="container text-center w-50 border rounded bg-light">
        <div class="m-3">
            <label for="id" class="form-label container text-start">아이디</label>
            <input type="text" id="id"class="form-control" required>
        </div>
        <div class="m-3">
            <label for="password" class="form-label container text-start">비밀번호</label>
            <input type="password" id="pwd" class="form-control" required>
        </div>
        <div class="m-3">
            <input type="button" class="bg-primary btn text-white form-control" value="로그인" onclick="confirm()">
        </div>
    </div>
    `;
}

function confirm(){
    let id=document.getElementById("id");
    let pwd=document.getElementById("pwd");
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/;
    
    if(id.value === ""){
        alert("아이디를 입력하세요.");
        id.focus();
        return;
    }

    if(pwd.value===""){
        alert("비밀번호를 입력하세요.");
        pwd.focus();
        return;
    }

    if(pwd.value.length < 4){
        alert("비밀번호의 길이가 4자 이상이어야 합니다.");
        pwd.focus();
        return;
    }

    if(!regex.test(pwd.value)){
        alert("비밀번호는 소문자, 대문자, 숫자를 포함해야 합니다.");
        pwd.focus();
        return;
    }

    if(id.value === "admin" && pwd.value ==="Aa12"){
        alert("로그인 성공!");
    }else{
        alert("로그인 실패!");
        id.focus();
        return;
    }
    return;
}
