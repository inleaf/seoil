/*
동작순서를 글로 적는다.
1)입력상자에 제목을 입력한다.
2)찾기버튼을 누른다.
3)books.json 파일을 XMLHttpRequest 객체를 통해 가져온다.
4)가져온 데이터를 입력상자의 찾기 제목과 비교하여 정보를 획득한다.
4)획득한 책의 객체 정보를 ul 태그의 li에 입력한다.
*/

function find(){
    let xhttp = new XMLHttpRequest();
    xhttp.open("get","books.json");
    xhttp.send();

    /*
    xhttp.onload=()=>{
        let books = JSON.parse(xhttp.responseText);
        let inputText = document.getElementById("inputbox").value;
        let result=books.find(book=>book.title === inputText);

        let menu = document.querySelectorAll("li");
        menu[0].innerHTML+=result.title;
        menu[1].innerHTML+=result.author;
        menu[2].innerHTML+=result.price;
    }
    */

    xhttp.onload=()=>{
        let books = JSON.parse(xhttp.responseText);
        let inputbox = document.getElementById("inputbox")
        let search = inputbox.value;
        console.log(search);
        //정확히 찾기
        let result=books.find(book=>book.title === search);
        console.log(result);
        //찾은 객체를 화면에 표시
        // let ul = document.querySelector("#findlist");
        let ul = document.getElementById("findlist");
        ul.querySelector("li:nth-child(1)").textContent=`제목: ${result["title"]}`;
        ul.querySelector("li:nth-child(2)").textContent=`저자: ${result["author"]}`;
        ul.querySelector("li:nth-child(3)").textContent=`가격: ${result["price"]}원`;
    }

    /*
    참고사항 querySelector 함수는 클래스는 .클래스명, 아이디는 #아이디명, 일반태그는 태그명으로
    객체를 획득할 수 있다.
    */
}
