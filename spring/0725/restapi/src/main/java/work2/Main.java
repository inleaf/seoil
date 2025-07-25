package work2;

import java.sql.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		String address="https://jsonplaceholder.typicode.com/users";
		URL url = new URL(address);
		InputStream in = url.openStream();
		int read=0;
		String buf="";
		
		while((read=in.read())!=-1) {
			buf+=(char)read;
		}
		System.out.println(buf);
		//buf에 있는 문자열을 자바 객체로 변환
		ObjectMapper mapper = new ObjectMapper();
		List<User> users= mapper.readValue(buf, new TypeReference<List<User>>() {});
		System.out.println(users);
		//데이터베이스에 입력
		insertUsers(users);
		
	}

	//자바 코드 변경 버전
	/*
	private static void insertUsers(List<User> users) throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try(Connection conn=DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe"
				,"night","night")){
			conn.setAutoCommit(false);
			//각각의 테이블에 시퀸스 작성하여 시퀀스 작성하여 시퀀시명.nextval
			String geoSql="insert into geo(id,address_id,lat,lng) values(?,?,?,?)";
			String addressSql="insert into address(id, user_id, street, suite, city,zipcode) values(?,?,?,?,?,?)";
			String companySql="insert into company(id,user_id,name,catch_phrase,bs) values(?,?,?,?,?)";
			String userSql="insert into users(id,name,username,email,phone,website) values(?,?,?,?,?,?)";
			
			//user의 id를 제외하고는 address, company, geo에 id는 자동시퀸스생성
			//입력순서는 역으로 처리해야한다.(geo-address-company-user)
			try(
			PreparedStatement geops=conn.prepareStatement(geoSql);
			PreparedStatement addressps=conn.prepareStatement(addressSql);
			PreparedStatement companyps=conn.prepareStatement(companySql);
			PreparedStatement userps=conn.prepareStatement(userSql);
			){
				String geoId="select geo_seq.nextval from dual";
				String addressId="select address_seq.nextval from dual";
				String companyId="select company_seq.nextval from dual";
				Statement seqStat=conn.createStatement();
				
				for(User user:users) {
					//user 테이블입력
					//insert into users
					//insert into users(id,name,username,email,phone,website) values(?,?,?,?,?,?)
					userps.setInt(1,user.getId());
					userps.setString(2,user.getName());
					userps.setString(3,user.getUsername());
					userps.setString(4,user.getEmail());
					userps.setString(5,user.getPhone());
					userps.setString(6,user.getWebsite());
					userps.executeUpdate();
					
					//company id생성/테이블입력
					ResultSet rsCompany=seqStat.executeQuery(companyId);
					rsCompany.next();
					int companyid=rsCompany.getInt(1);
					//insert into company(id,user_id,name,catch_phrase,bs) values(?,?,?,?,?)
					companyps.setInt(1, companyid);
					companyps.setInt(2, user.getId());
					companyps.setString(3,user.getCompany().getName());
					companyps.setString(4,user.getCompany().getCatchPhrase());
					companyps.setString(5,user.getCompany().getBs());
					companyps.executeUpdate();
					
					//address id생성/테이블입력
					ResultSet rsAddress=seqStat.executeQuery(addressId);
					rsAddress.next();
					int addressid=rsAddress.getInt(1);
					
					//insert into address
					//insert into address(id, user_id, street, suite, city,zipcode) values(?,?,?,?,?,?)
					addressps.setInt(1, addressid);
					addressps.setInt(2, user.getId());
					addressps.setString(3,user.getAddress().getStreet());
					addressps.setString(4,user.getAddress().getSuite());
					addressps.setString(5,user.getAddress().getCity());
					addressps.setString(6,user.getAddress().getZipcode());
					addressps.executeUpdate();
					
					//Geo에 id생성
					ResultSet rsGeo=seqStat.executeQuery(geoId);
					rsGeo.next();
					int geoid=rsGeo.getInt(1); //geo의 id추출
					
					//Geo테이블삽입
					//insert into geo(id,address_id,lat,lng) values(?,?,?,?)
					geops.setInt(1,geoid);
					geops.setInt(2,addressid);
					geops.setString(3, user.getAddress().getGeo().getLat());
					geops.setString(4, user.getAddress().getGeo().getLng());
					geops.executeUpdate();
				}
				//반복문으로 입력 후 commit처리
				conn.commit();
				System.out.println("모든 데이터 정상입력");
				//모든 자원에 대한 반환
			}catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			
		}
	}*/
	
	//db변경 버전
	private static void insertUsers(List<User> users) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try(Connection conn=DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe"
				,"night","night")){
			conn.setAutoCommit(false);
			String geoSql="insert into geo(id,lat,lng) values(?,?,?)";
			String addressSql="insert into address(id,street, suite, city,zipcode,geo_id) values(?,?,?,?,?,?)";
			String companySql="insert into company(id,name,catch_phrase,bs) values(?,?,?,?)";
			String userSql="insert into users(id,name,username,email,phone,website,address_id,company_id) values(?,?,?,?,?,?,?,?)";
			
			//user의 id를 제외하고는 address, company, geo에 id는 자동시퀸스생성
			//입력순서는 역으로 처리해야한다.(geo-address-company-user)
			try(
			PreparedStatement geops=conn.prepareStatement(geoSql);
			PreparedStatement addressps=conn.prepareStatement(addressSql);
			PreparedStatement companyps=conn.prepareStatement(companySql);
			PreparedStatement userps=conn.prepareStatement(userSql);
			){
				String geoId="select geo_seq.nextval from dual";
				String addressId="select address_seq.nextval from dual";
				String companyId="select company_seq.nextval from dual";
				Statement seqStat=conn.createStatement();
				
				for(User user:users) {
					//Geo에 id생성
					ResultSet rsGeo=seqStat.executeQuery(geoId);
					rsGeo.next();
					int geoid=rsGeo.getInt(1); //geo의 id추출
					
					//Geo테이블삽입
					geops.setInt(1,geoid);
					geops.setString(2, user.getAddress().getGeo().getLat());
					geops.setString(3, user.getAddress().getGeo().getLng());
					geops.executeUpdate();
					
					//address id생성/테이블입력
					ResultSet rsAddress=seqStat.executeQuery(addressId);
					rsAddress.next();
					int addressid=rsAddress.getInt(1);
					
					//insert into address
					//(id,street, suite, city,zipcode,geo_id) values(?,?,?,?,?,?)
					addressps.setInt(1, addressid);
					addressps.setString(2,user.getAddress().getStreet());
					addressps.setString(3,user.getAddress().getSuite());
					addressps.setString(4,user.getAddress().getCity());
					addressps.setString(5,user.getAddress().getZipcode());
					addressps.setInt(6,geoid);
					addressps.executeUpdate();
					
					//company id생성/테이블입력
					ResultSet rsCompany=seqStat.executeQuery(companyId);
					rsCompany.next();
					int companyid=rsCompany.getInt(1);
					//insert into company(id,name,catchPhrase,bs) values(?,?,?,?)
					companyps.setInt(1, companyid);
					companyps.setString(2,user.getCompany().getName());
					companyps.setString(3,user.getCompany().getCatchPhrase());
					companyps.setString(4,user.getCompany().getBs());
					companyps.executeUpdate();
					
					//user 테이블입력
					//insert into users
					//(id,name,username,email,phone,website,address_id,company_id) values(?,?,?,?,?,?,?,?)";
					userps.setInt(1,user.getId());
					userps.setString(2,user.getName());
					userps.setString(3,user.getUsername());
					userps.setString(4,user.getEmail());
					userps.setString(5,user.getPhone());
					userps.setString(6,user.getWebsite());
					userps.setInt(7,addressid);
					userps.setInt(8,companyid);
					userps.executeUpdate();
				}
				//반복문으로 입력 후 commit처리
				conn.commit();
				System.out.println("모든 데이터 정상입력");
				//모든 자원에 대한 반환
			}catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			
		}
		
	}
}
