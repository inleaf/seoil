package work;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import restapi.Member;
//@Component
public class ScheduledWork {
	
	int i = 1;
	
	public ScheduledWork() {
		System.out.println("***스케줄러 객체 실행**");
	}
	
	//@Test
	//@Scheduled(cron = "0/5 * * * * ?")
	public void work1() {
		System.out.println("scheduled execute : " + (i++));
	}
	
	@Test
	public void work2() throws IOException {
		String address="http://172.16.15.72:8888/api/members";
		URL url = new URL(address);
//		System.out.println(url.getProtocol());
//		System.out.println(url.getHost());
//		System.out.println(url.getPort());
//		System.out.println(url.getPath());
		InputStream in = url.openStream();
		int read=0;
		String data="";
		while((read=in.read())!=-1) {
			//System.out.print((char)read);
			data+=(char)read;
		}
		System.out.println(data);
		//위의 문자열 데이터를 파싱하세요.
		//1)순수 코드를 이용하여 분해
		//(String 문자열 함수 중에서 indexOf, length, substring, split)
		//[]를 없애라
		/*
		data=data.substring(1); // [ 삭제
		System.out.println(data);
		data=data.substring(0,data.length()-1); // ] 삭제
		System.out.println(data);
		String[] objs = data.split("},\\{"); //객체의 내용을 문자열로 표시하는 방법
		System.out.println(Arrays.toString(objs));
		objs[0]+="}";
		System.out.println("char array:"+objs[0]);
		
		//내가 생각하는 모든 코드는 함수로 만들어져 있다.
		List<Member> list = new ArrayList<Member>();
		ObjectMapper mapper = new ObjectMapper();
		Member member = mapper.readValue(objs[0], Member.class);
		System.out.println("object:"+member);
		list.add(member); //반복해서 처리
		*/
		ObjectMapper mapper = new ObjectMapper();
		List<Member> members = mapper.readValue(data, new TypeReference<List<Member>>() {});
		System.out.println(members);
		
	}
}
