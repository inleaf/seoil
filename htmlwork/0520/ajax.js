//ajax통신(비동기 자바스크립트 통신)
//무료 JSON데이터
/*
https://api.coingecko.com/api/v3/coins/bitcoin
https://jsonplaceholder.typicode.com/users
https://jsonplaceholder.typicode.com/posts
https://jsonplaceholder.typicode.com/comments
*/

let xhttp=new XMLHttpRequest();
console.log(xhttp);
xhttp.open("get","books.json");
// xhttp.open("get","books.xml");
xhttp.send();
xhttp.onload=() => {
    console.log(xhttp.responseText);
    const body = document.querySelector("body");
    console.log(body);
    let books=JSON.parse(xhttp.responseText);

    // body.textContent=books[0].title;
    console.log(typeof books);
    console.log(books.length);

    body.innerHTML+="------기본 반복문 사용------";
    for(i=0;i<books.length;i++){
        body.innerHTML+=books[i].title+"<br>";
    }

    body.innerHTML+="<br>------forEach 반복문 사용------";
    books.forEach(element=>{
        body.innerHTML+=element.title+"<br>";
    });

    body.innerHTML+="<br>------for in(index==번호) 반복문 사용------";
    for (const index in books){
        body.innerHTML+=books[index].title+"<br>";
    }

    body.innerHTML+="<br>------for of(객체반환) 반복문 사용------";
    for (const key of books){
        body.innerHTML+=key.title+"<br>";
    }

    /*특정조건을 이용하여 json객체를 찾는 방법(books에서 객체를 book에 넘기고
    )book.title과 문자열이 일치할 때 객체를 반환
    */
    var result=books.find(book=>book.title === "javascript 기초")
    console.log(result);
}