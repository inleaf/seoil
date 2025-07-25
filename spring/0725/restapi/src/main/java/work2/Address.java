package work2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geo geo;
	/*
	public String street;
	public String suite;
	public String city;
	public String zipcode;
	public Geo geo;
	*/
}
