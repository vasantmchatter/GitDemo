
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;
import Files.ReUsableMethods;

public class BasicsRest {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
   /*  RestAssured.baseURI="https://rahulshettyacademy.com";
     given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
     .body("{\r\n"
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
     		+ "").when().post("maps/api/place/add/json")
      .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
      .header("server", "Apache/2.4.52 (Ubuntu)\r\n"
      		+ "");*/
 //==============================================================================================================   
     // By using addPlace Method in body parameter 
		/* RestAssured.baseURI="https://rahulshettyacademy.com";
	     given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	     .body(Payload.addPlace()).when().post("maps/api/place/add/json")
	      .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
	      .header("server", "Apache/2.4.52 (Ubuntu)");*/
		
//Important *****    .header("server", "Apache/2.4.52 (Ubuntu)\r\n"+ "");  dont use this (\r\n"+ "") code causing exception but code will run
	//============================================================================================================
		
 //! extract the response data and put itin string 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				      //   .body(Payload.addPlace())
				.body(new String (Files.readAllBytes(Paths.get("D:\\vasant\\Rest API Notes\\addRequest.txt"))))
				         .when().post("maps/api/place/add/json").
				          then().assertThat().statusCode(200)
				         .body("scope",equalTo("APP")).
				         	header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
	    JsonPath js = new JsonPath(response);// To parse the JSON 
		
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		//System.out.println(js.getString("place_id"));
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++		
	// Update the Address
		String UpdateAddress = "M G ROAD BANGALORE near metro station curch street";
		 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \r\n"
				+ "    \"place_id\":\""+placeId+"\",\r\n"
				+ "    \"address\":\""+UpdateAddress+"\",\r\n"
				+ "    \"key\" :\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json/")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
  //Get the details updated address is properly or not 
		 System.out.println("+++++++++++++++++++++++printing get info below+++++++++++++++++++"+"\n");
      String  GetResponse  = given().log().all().queryParam("key","qaclick123")
          .queryParam("place_id",placeId)
          .when().get("maps/api/place/get/json")
          .then().assertThat().log().all().statusCode(200).extract().response().asString();
      
      
     /// JsonPath js1= new JsonPath(GetResponse); this is we are using in Reusebale methods
      
      JsonPath js1= ReUsableMethods.rawToJson(GetResponse);
      String NewAddress = js1.getString("address");
      System.out.println("NewAddress:" +NewAddress);
      
      Assert.assertEquals(NewAddress,UpdateAddress);
      
      
      
//=+++++++++++++++++++++++++++++===================++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=
		
	}


}









