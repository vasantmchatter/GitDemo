import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files_func.AllHeaders;
import Files_func.UserMethods;

public class UserCreation {

	@Test(dataProvider="user")
	public static void UserActivation(String Firstname,String LastName,String Email,String UserName) {
		  //SIT URL
		RestAssured.baseURI="https://sit.set.nm.digitalharbor.us";
		
		//Common Code URL
 	//	RestAssured.baseURI="https://test.set.nm.digitalharbor.us";
		
		String UserDetails= given().log().all().
		header("Authorization",AllHeaders.headersVariable()).
		header("Content-Type","application/json").
		header("X-TENANT-ID","skyp").
		body(UserMethods.userCreationMethod(Firstname,LastName,Email,UserName)).
		when().post("set/pi/security-service/users").
		then().log().all().assertThat().statusCode(201).
		header("server","nginx").
		header("Content-Type","application/json").
		extract().response().asString();
		
	   JsonPath js= new JsonPath(UserDetails);
	    String AuthTokenID =js.getString("authToken");
	    String Slugid=js.getString("userId");
	   
		//System.out.println("authToken:" +AuthTokenID);	
		//System.out.println("Userid:"+Slugid);
	   
	   
	   /// User Activation code 
		 UserMethods.UseractivationMethod(AuthTokenID, Slugid);    
	       System.out.println("\n\n++++++++++++++++++++++++++++++user activation successfull\n\n++++++++++++++++++++++++++++++++++++++++++");
	   
	   //  User get role Function===================================================== 
	                  // UserMethods.UserGetRoles();
	              System.out.println("\n\n+++++++++++++++++++++++++++user get role compeleted+++++++++++++++++++++++\n\n");
	    //===============User get Role method ends==========================================   
	       
	       
	   // User Assign function
	                  
	         //int GetRoleID[] = {21,6,17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
	         int GetRoleID[] = {33}; //17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
	         
	            for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            	    
	       System.out.println("++++++++++++++User Successfully Created+++++++++++++++++++++++++++++++==");
	        
	       
	   // Delete user method  
	      // UserMethods.DeleteUserMethod(Slugid);
	      // System.out.println("\n\n++++++++++++++Deleted User Successfully+++++++++++++++++++++++++++\n\n");
	}
	
					
	

	@DataProvider(name="user")
	public static Object[][] getUsers() {
		
		//return new Object[][]{{"Test","all","testall@gmailcom","testall"}};
		
		return new Object[][]{
								{"Babhani","anst","Bhabanianst@gmailcom","Babhanianst"},
								{"Chaitra","anst","chaitraanst@gmailcom","chaitraanst"},
								{"Deevti","anst","Deevtianst@gmailcom","Deevtianst"},
								{"Avinash","anst","Avinashanst@gmailcom","Avinashanst"},
								{"Basav","anst","Basavanst@gmailcom","Basavanst"}		
							};
	}
	

}


/* writting to get user
 *  
 *   if (LastName.equalsIgnoreCase('sr')){
 *   		 int GetRoleID[] = {21,6};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	   else if (LastName.equalsIgnoreCase('rev')){
	   int GetRoleID[] = {17,28,30,3};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	         
	         
	          else if (LastName.equalsIgnoreCase('sqc')){
	   int GetRoleID[] = {10};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('qc')){
	   int GetRoleID[] = {1};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('ssa')){
	   int GetRoleID[] = {23};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	             else if (LastName.equalsIgnoreCase('sa')){
	   int GetRoleID[] = {20};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('svsr')){
	   int GetRoleID[] = {8};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('svr')){
	   int GetRoleID[] = {27};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('ceb')){
	   int GetRoleID[] = {12};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	            
	             else if (LastName.equalsIgnoreCase('sup')){
	   int GetRoleID[] = {16};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
	            
	            
	             else if (LastName.equalsIgnoreCase('anst')){
	   int GetRoleID[] = {33};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
 * 
 * else if (LastName.equalsIgnoreCase('monsr')){
	   int GetRoleID[] = {22};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
 * 
 * else if (LastName.equalsIgnoreCase('monr')){
	   int GetRoleID[] = {13,7};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
 * 
 * 
 * else (LastName.equalsIgnoreCase('monssr')){
	   int GetRoleID[] = {15};
 * 				 for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);
	            }
 * 
 * 
 
 */


