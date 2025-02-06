package Demo;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("https://rahulshettyacademy1.com");
		p.setLanguage("French-IN");
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList); //This is adding all list values to object of P
		
		Location l=new Location();	
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		

		Response Response =given().log().all().queryParam("key","qaclick123")
				.body(p)
				  .when().post("maps/api/place/update/json").
				  then().log().all().assertThat().statusCode(200).extract().response();
		
		String Responsestring=Response.asString();
		System.out.println(Responsestring);
		
	}

}
