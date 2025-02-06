import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;



public class ClientCred {

	public static void main(String[] args) {
	 //RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String AccessTokenValue=given().log().all().
				formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
				formParams("grant_type","client_credentials").
				formParams("scope","trust ").
				when().post("oauthapi/oauth2/resourceOwner/token").
				then().log().all().extract().response().asString();
		
			JsonPath Js= new JsonPath(AccessTokenValue);
			String ClientId=Js.getString("client_id");
			
		//Get Course Details
			String GetCourseDetails=
					 given().log().all().
					 queryParam("access_token",AccessTokenValue).
					 when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").
					 then().log().all().extract().response().asString();
			
					 
				

	}

}
