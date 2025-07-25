package restapi;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@XmlRootElement(name="person") //xml 형식으로 표시할 경우 반드시 표시, json은 무관함
public class Person {
	private String name;
	private int age;
}
