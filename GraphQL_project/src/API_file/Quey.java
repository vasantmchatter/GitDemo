package API_file;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

public class Quey {
	
	public static void main(String args[]) {
	String Response=given().log().all().
	header("content-Type","application/json").
	body("query ($characterId : Int!,$episodeId: Int!){\r\n"
			+ "  character(characterId: $characterId){\r\n"
			+ "    id \r\n"
			+ "    name\r\n"
			+ "    status\r\n"
			+ "    species\r\n"
			+ "  }\r\n"
			+ "  episode(episodeId: $episodeId ){\r\n"
			+ "    name\r\n"
			+ "    \r\n"
			+ "    }\r\n"
			+ "}\r\n"
			+ "").
	 when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().
	extract().response().asString();
	
	System.out.println(Response);
	JsonPath js =new JsonPath(Response);
	String CharacterName= js.getString("data.character.name");
	System.out.println(CharacterName);
	Assert.assertEquals(CharacterName,"vasant");
	
	

	//Mutation 
	
	String MutationResponse1=given().log().all().
			header("content-Type","application/json").
			body("mutation($locationname:String!,$locationtype:String!, $episodename:String!){\r\n"
					+ "  \r\n"
					+ "   createLocation(location:{name:$locationname,type: $locationtype,dimension:\"349\"}){\r\n"
					+ "    id\r\n"
					+ "  }\r\n"
					+ "  \r\n"
					+ "  createEpisode(episode:{name:$episodename,air_date:\"1873 june\",episode:\"76\"}){\r\n"
					+ "    id\r\n"
					+ "  }\r\n"
					+ "   \r\n"
					+ " \r\n"
					+ "\"locationname\": \"South Africa\",\r\n"
					+ "  \"locationtype\": \"Southzone\",\r\n"
					+ "  \"episodename\": \"Mahabharath\"}").
			 when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().
			extract().response().asString();
			
	        System.out.println(MutationResponse1);
			JsonPath js2 =new JsonPath(MutationResponse1);
			String creationid= js2.getString("data.creation.id");
			System.out.println(creationid);
			Assert.assertEquals(creationid,"17237");
			
}
}
