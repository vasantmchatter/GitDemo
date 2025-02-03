package Files_func;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class UserMethods {

	
	public static String userCreationMethod(String Firstname,String LastName,String Email,String UserName) {
		
		String UserCrationBody= "{ \r\n"
				+ "	\"firstName\": \""+Firstname+"\",\r\n"
				+ "    \"lastName\": \""+LastName+"\",\r\n"
				+ "    \"email\": \""+Email+"\",\r\n"
				+ "    \"password\": \"Password@1\",\r\n"
				+ "    \"phoneNumber\": \"91 9901305774\",\r\n"
				+ "    \"avatar\": \"Avatar String\",\r\n"
				+ "    \"userName\": \""+UserName+"\",\r\n"
				+ "    \"locked\": false,\r\n"
				+ "    \"isDeleted\": false,\r\n"
				+ "    \"enabled\": true,\r\n"
				+ "    \"objectClass\": [\r\n"
				+ "        \"User\"\r\n"
				+ "    ],\r\n"
				+ "    \"attributes\": {\r\n"
				+ "        \"userPrincipalName\": \"system2@newmex.com\"\r\n"
				+ "    }}";
		
		return UserCrationBody;
	}
	
	       public static String UseractivationMethod(String AuthTokenID, String Slugid) {
	    	   
	    	   String UserActivation = given().log().all().
	    		       header("Authorization",AllHeaders.headersVariable()).
	    				header("Content-Type","application/json").
	    				header("X-TENANT-ID","skyp").
	    				//header("X-TENANT-ID","ukyp").
	    				header("User-Agent","insomnia/2023.5.8pair_835c5adfbcd347fcb514c562b4ee7cab").
	    		       body("{ 	\"authToken\": \""+AuthTokenID+"\" }").
	    		       when().post("set/pi/security-service/users/"+Slugid+"/activate").
	    		       then().log().all().
	    		       header("server","nginx").
	    				header("Content-Type","application/json").
	    				extract().response().asString();
	    	    return UserActivation;
	       }
	
	
	    public static String UserGetRoles() {
	    	
	    	String GetRoles=given().
	    	header("Authorization",AllHeaders.headersVariable()).
	    	header("User-Agent","insopair_9a6f154be5db4437pair_9a6f154be5db4437997b82674f136fa2997b82674f136fa2mnia/2023.5.8").
	    	header("X-TENANT-ID","skyp").
	    	//header("X-TENANT-ID","ukyp").
	    	when().get("set/pi/security-service/roles").
	    	then().assertThat().statusCode(200).
	    	extract().response().asString();
	    	
	    	return GetRoles;
	    }
	    
	    public static String getRoles(String Slugid, int getRoleID) {
	    	
	    	String getrolesIDs= given().log().all().queryParam("roleIds",getRoleID).
		        header("Authorization",AllHeaders.headersVariable()).
				header("Content-Type","application/json").
				header("X-TENANT-ID","skyp").
				//header("X-TENANT-ID","ukyp").
				when().post("set/pi/security-service/users/"+Slugid+"/assignRoles").
				then().log().all().statusCode(201).
				extract().response().asString();
	    	
	    	return getrolesIDs;
	    }

		public static String DeleteUserMethod(String Slugid) {
			
			String DeleteUser =given().log().all().
					    header("Authorization",AllHeaders.headersVariable()).
						header("Content-Type","application/json").
						header("X-TENANT-ID","skyp").
						//header("X-TENANT-ID","ukyp").
						when().delete("set/pi/security-service/users/"+Slugid+"").
						then().log().all().assertThat().statusCode(204).
						extract().response().asString();
			
			return DeleteUser;
		}
	    
}
