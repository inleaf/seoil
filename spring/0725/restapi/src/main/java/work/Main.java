package work;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import restapi.Member;

public class Main {
	public static void main(String[] args) throws IOException {
		String address="http://172.16.15.72:8888/api/members";
		URL url = new URL(address);
		InputStream in = url.openStream();
		int read=0;
		String data="";
		while((read=in.read())!=-1) {
			data+=(char)read;
		}
		//문자열 형태의 배열을 객체리스트 배열로 변환
		/*
		ObjectMapper mapper = new ObjectMapper();
		List<Member> members = mapper.readValue(data, new TypeReference<List<Member>>() {});
		System.out.println(members);
		*/
		
		String trimmed = data.substring(1, data.length() - 1);
		String[] arr = trimmed.split("},\\{");

		for (int i = 0; i < arr.length; i++) {
		    if (!arr[i].startsWith("{")) arr[i] = "{" + arr[i];
		    if (!arr[i].endsWith("}")) arr[i] = arr[i] + "}";
		    System.out.println("array[" + i + "] = " + arr[i]);
		}
		//해당 리스트 객체를 데이터베이스에 연결하여 데이터를 입력(Connection)
		//중복되는 데이터를 허용하여 데이터를 입력하려면 단순히 insert만 해주면 됨
		//하지만 중복이 되지 않은 데이터만을 입력하려면 필터를 이용하여 입력 안한 데이터를 추출 후 데이터 입력
	}

}
