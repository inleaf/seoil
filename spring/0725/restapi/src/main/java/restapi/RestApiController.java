package restapi;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //@RestController 함수에 @ResponseBody 없어도 문자열을 리턴하는 역할을 한다.
public class RestApiController {
	
	//@GetMapping("/")
	//@ResponseBody //페이지와는 전혀 무관한 코드
	public String index() {
		return "index"; 
		//index.jsp -> 문자열로 "index" 문자가 페이지로 전송(@ResponseBody)
	}
	
	@GetMapping("myjson")
	public String myjson() {
		return "{\"name\":\"kim\",\"age\":\"25\"}";
	}
	
	@GetMapping("objectjson")
	public Person objectjson() {
		Person person = new Person();
		person.setName("hongkildong");
		person.setAge(30);
		return person;
	}
	/*
	@GetMapping(
			value = "/person/xml",
			produces = {MediaType.APPLICATION_XML_VALUE})*/
	@GetMapping(value="/person/xml", produces="application/xml")
	public Person getPerson() {
		return new Person("홍길동", 30);
	}	
	
    @GetMapping(
            value = "/person/json",
            produces = MediaType.APPLICATION_JSON_VALUE
        )
        public Person getPersonJson() {
            return new Person("홍길동", 30);
        }
    
    @PostMapping("/insert")
    //Person에 아무것도 없는 상태
    //public ResponseEntity<String> insert(Person person){
    //public ResponseEntity<String> insert(@RequestParam Person person){
    //public ResponseEntity<String> insert(@RequestBody Person person){
    public ResponseEntity<String> insert(Person person){
    	System.out.println(person);
    	return ResponseEntity.ok("insert confirm");
    }
}
