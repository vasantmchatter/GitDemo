package Demo;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class Serialization2_SpecBuilder {

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
		
     //Requestspecification 
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").
				 setContentType(ContentType.JSON).build();	
		 
		  ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req).log().all()
				.body(p);
				
		
				Response responsedata=  res.when().post("maps/api/place/update/json").
				  then().spec(resp).log().all().extract().response();
		
				
		String Responsevalue= responsedata.asString();
		System.out.println(Responsevalue);
				
	}

}
