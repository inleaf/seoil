//오류발생
/*
let menu=document.getElementsByTagName("li");
console.log(menu);
menu.forEach(element => {
    console.log(element);
});
*/

let menu = document.querySelectorAll("li");
console.log(menu);

menu.forEach(element => {
    console.log(element); //출력이 안됨 => script tag defer
    element.addEventListener("click",()=>{
        //console.log(element.innerText);
        console.log(`${element.innerText}.html`);
        fetch(`${element.innerText}.html`)
        .then(resp =>resp.text())
        .then(result=>{
            let main=document.querySelector("main");
            main.innerHTML=result;
        })
    });
});

/*
menu.forEach(element => {
    console.log(element); //출력이 안됨
    element.addEventListener("click",function(){
        
    });
});
*/

/*
menu.forEach(element => {
    console.log(element); //출력이 안됨
});
*/

/*
menu.forEach(function(element){

});
*/