package restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "index"; 
	}
	
	@GetMapping("/xhr")
	public void xhr() {}
	
	@GetMapping("/ajax")
	public void ajax() {}
	
	@GetMapping("/fetch")
	public void fetch() {}
	
	@GetMapping("/myname")
	@ResponseBody
	public String myname() {
		return "myname is hong";
	}
	
	@GetMapping("/ajax2")
	public void ajax2() {}
	
	@GetMapping("/smallweb")
	@ResponseBody
	public String smallweb() {
		return "<html><head></head><body><h1>small web</h1></body></html>";
	}
	
	@GetMapping("/test")
	public void test() {}
}
