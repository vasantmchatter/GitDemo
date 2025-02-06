
import io.restassured.RestAssured;
import static io.restassured.Restassured.*;

public class PracticeRest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Restassured.baseURI="https://rahulshettyacademy.com"; 
		given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json").
		body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -35.383494,\r\n"
				+ "    \"lng\": 35.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 55,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"298, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "").when().post("maps/api/place/add/json").then().log().all().assertThat().statuscode(200);

	}

}
