import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import File.ReusabilityFunc;

public class UserCreation {

	String tokenValue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://sit.set.nm.digitalharbor.us";
		// 

		String UserCreations = given().log().all().//queryParam("cn","exFJEPiWeT%27").
				header("Authorization",ReusabilityFunc.AuthorizationValue())
				.header("Content-Type","application/json").header("X-TENANT-ID","skyp")
				.body(ReusabilityFunc.body())
				.when().post("set/pi/security-service/users")
				.then().log().all().assertThat().statusCode(201)
				//.body("userName",equalTo("121rev"))
				//.body("userName",hasItem("112rev")) 
				.header("server","nginx")
				.header("Content-Type","application/json")
				.extract().response().asString();

		// System.out.println("---------usercreation body----------------");
		// System.out.println(UserCreations);

		JsonPath js =new JsonPath(UserCreations);

		String tokenValue= js.get("authToken");
		String userIdValue= js.getString("userId");

		System.out.println("AutorizationTokenvalues:"+tokenValue);  
		System.out.println("SlugID:"+userIdValue);  

		/*-------------------------------User Activation Body---------------------------------------*/
		System.out.println("\n\n++++++++++++++++++++++++=================User activation statrted===========================+++++++++++++++++++++++++++++++");

		//String UserActivation1= 
		String UserActivation=  given().log().all()
				.header("Authorization",ReusabilityFunc.AuthorizationValue())
				.header("Content-Type","application/json")
				.header("User-Agent","insomnia/2023.5.8pair_835c5adfbcd347fcb514c562b4ee7cab")
				.header("X-TENANT-ID","skyp")
				// .body("authToken:"+tokenValue)
				.body("{ \"authToken\":\""+tokenValue+"\"}\"")
				.when().post("set/pi/security-service/users"+"/"+userIdValue+"/activate")
				.then().log().all().assertThat().statusCode(201)
				.header("server",equalTo("nginx"))
				.header("Content-Type","application/json")
				.body("reason",equalTo("User activated successfully"))
				.extract().response().asString();

		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++User activated successfully++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		//User Get Roles
		
		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++Get The User Roles++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                   
		
		    String getAllRoles= given().log().all()
		    .header("Authorization",ReusabilityFunc.AuthorizationValue())
		    .header("User-Agent","insopair_9a6f154be5db4437pair_9a6f154be5db4437997b82674f136fa2997b82674f136fa2mnia/2023.5.8")
		    .header("X-TENANT-ID","skyp")
		    .when().get("set/pi/security-service/roles")
		    .then().log().all().assertThat().statusCode(200)
		    .header("server","nginx")
		    .header("content-type","application/json")
		    .extract().response().asString();

		// Assigning the User all
		    int rolesId[] = {21,6,17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
		    String auth = ReusabilityFunc.AuthorizationValue();
		    for(int i =0 ; i<rolesId.length;i++) {
			System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++Assigning the Role++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");  
		    String AssignRole=given().log().all()
		    //.queryParam("roleIds","17")
		    .queryParam("roleIds",rolesId[i])
		    .header("Authorization",auth)
		    .header("User-Agent","insopair_9a6f154be5db4437pair_9a6f154be5db4437997b82674f136fa2997b82674f136fa2mnia/2023.5.8")
		    .header("X-TENANT-ID","skyp")
		    .when().post("set/pi/security-service/users/"+userIdValue+"/assignRoles")
		    .then().log().all().assertThat().statusCode(201)
		    .header("server","nginx")
		    .header("content-type","application/json")
		    .extract().response().asString();
		    
		    
		   // System.out.println("roleid:"+rolesId[i]+"");
		    
		    
		    
			System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++User Successfylly assigned++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"); 
		   
		    
		    }
	}

}
