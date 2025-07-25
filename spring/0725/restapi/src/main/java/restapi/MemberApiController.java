package restapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberApiController {
	
	List<Member> members=new ArrayList<>(Arrays.asList(
			new Member("user1", "1234", "user1@naver.com"),
			new Member("user2", "1234", "user2@naver.com"),
			new Member("user3", "1234", "user3@naver.com"),
			new Member("user4", "1234", "user4@naver.com"),
			new Member("user5", "1234", "user5@naver.com")
	));
	
	@PostMapping("member")
	public ResponseEntity<Member> insert(@RequestBody Member member) {
	    // 실제로는 List<Member> members = new ArrayList<>(...); 식으로 선언되어 있어야 합니다.
	    members.add(member); // 리스트에 추가
	    System.out.println(member + " 입력");

	    // 201 Created + 추가된 객체 반환
	    return ResponseEntity.status(HttpStatus.CREATED).body(member);
	}
	
	//localhost:8888/api/member/json?username=user2
	@GetMapping(value="member/{type}")
	public ResponseEntity<Member> typeSelectById(@PathVariable String type, @RequestParam String username) {
		Map<String, Member> memberMap = members.stream().collect(Collectors.toMap(Member::getUsername, m -> m));
		Member found = memberMap.get(username);
		if(type.equals("json")) {
			return ResponseEntity.ok()
					.header("Content-type", "application/json")
					.body(found);
		}else if(type.equals("xml")) {
			return ResponseEntity.ok()
					.header("Content-type", "application/xml")
					.body(found);
		}else {
			return ResponseEntity.ok()
					.header("Content-type", "text/plain")
					.body(found);
		}
	}
	
	//http://localhost:8888/api/member?username=user2
//	@GetMapping(value="member", produces="application/json")
//	public Member selectById(String username) {
//		Optional<Member> result = members.stream().filter(member -> member.getUsername().equals(username)).findFirst();
//		result.ifPresent(System.out::println);
//		return result.get();
//	}
	
	//http://localhost:8888/api/member?username=user2
	@GetMapping(value="member", produces="application/json")
	public Member selectById(@RequestParam String username) {
		Optional<Member> result = members.stream().filter(member -> member.getUsername().equals(username)).findFirst();
		result.ifPresent(System.out::println);
		return result.get();
	}
	
	
	@GetMapping("members")
	public List<Member> selectAll() {
		return members;
	}
	
	/*
	curl -X PUT http://localhost:8888/api/member
	-H "Content-Type: application/json"
	-d "{
			\"username\": \"user1\",
			\"password\": \"newpass123\",
			\"email\": "user1@example.com\"
		}"
	*/
	@PutMapping("member")
	public ResponseEntity<?> update(@RequestBody Member member) {
		System.out.println("client send object:"+member);
		Map<String, Member> memberMap = members.stream().collect(Collectors.toMap(Member::getUsername, m->m));
		Member existingMember = memberMap.get(member.getUsername());
		System.out.println(members.get(0).hashCode());
		System.out.println(existingMember.hashCode());
//		if(existingMember == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok("수정 성공");
		
		System.out.println("client send object: " + member);

	    // username으로 기존 Member 찾기 (members 리스트에서 직접 검색)
	    for (Member m : members) {
	        if (m.getUsername().equals(member.getUsername())) {
	            // 기존 Member 내용 수정
	            m.setPassword(member.getPassword());
	            m.setEmail(member.getEmail());
	            System.out.println("수정된 객체: " + m);
	            return ResponseEntity.ok("update ok"); // 200 OK + 수정된 객체 반환
	        }
	    }

	    // 찾지 못하면 404 Not Found
	    return ResponseEntity.notFound().build();
	}
	
	//curl -X DELETE "http://localhost:8888/api/member?username=user1"
	@DeleteMapping("member")
	public ResponseEntity<Member> delete(@RequestParam String username) {
	    for (Iterator<Member> it = members.iterator(); it.hasNext();) {
	        Member m = it.next();
	        if (m.getUsername().equals(username)) {
	            it.remove(); // 리스트에서 삭제
	            return ResponseEntity.ok().build(); // 200 OK
	        }
	    }
	    // 해당 username 없을 때 404 Not Found 반환
	    return ResponseEntity.notFound().build();
	}

}
