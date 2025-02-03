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

public class UserCreationWithallRole {

	@Test(dataProvider="user")
	public static void UserActivation(String Firstname,String LastName,String Email,String UserName) {
		  //SIT URL
		RestAssured.baseURI="https://sit.set.nm.digitalharbor.us";
		//RestAssured.baseURI="https://uat.set.nm.digitalharbor.us";
		//Common Code URL
 	//	RestAssured.baseURI="https://test.set.nm.digitalharbor.us";
		
		String UserDetails= given().log().all().
		header("Authorization",AllHeaders.headersVariable()).
		header("Content-Type","application/json").
		header("X-TENANT-ID","skyp").
		//header("X-TENANT-ID","ukyp").
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
	   
	   // User get role Function===================================================== 
	                  // UserMethods.UserGetRoles();
	              System.out.println("\n\n+++++++++++++++++++++++++++user get role compeleted+++++++++++++++++++++++\n\n");
	    //===============User get Role method ends==========================================   
	       
	       
	   // User Assign function
	                  
	       //  int GetRoleID[] = {21,6,17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
	        /* int GetRoleID[] = {33}; //17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
	         
	            for (int i=0;i<GetRoleID.length;i++) {
	            	UserMethods.getRoles(Slugid, GetRoleID[i]);*/
	            		
	              if (LastName.equalsIgnoreCase("SR")){
	            	  int sr[] = {21,6};
	              for (int i=0;i<sr.length;i++) 
	              { 
	            	  UserMethods.getRoles(Slugid, sr[i]); }
	              }

	              else if (LastName.equalsIgnoreCase("rev")){ 
	            	  int rev[] = {17,28,30,3};
	              for (int i=0;i<rev.length;i++) { UserMethods.getRoles(Slugid,rev[i]); 
	              }
	              }


	              else if (LastName.equalsIgnoreCase("sqc")){ int sqc[] = {10}; for (int
	            		  i=0;i<sqc.length;i++) { UserMethods.getRoles(Slugid, sqc[i]); 
	            		  }
	            		  }



	              else if (LastName.equalsIgnoreCase("qc")){ int qc[] = {1}; for (int
	            		  i=0;i<qc.length;i++) { UserMethods.getRoles(Slugid, qc[i]); 
	            		  }
	            		  }




	              else if (LastName.equalsIgnoreCase("ssa")){ int ssa[] = {23}; for (int
	            		  i=0;i<ssa.length;i++) { UserMethods.getRoles(Slugid, ssa[i]);
	              			}
	              }


	              else if (LastName.equalsIgnoreCase("sa")){ int sa[] = {20}; for (int
	            		  i=0;i<sa.length;i++) { UserMethods.getRoles(Slugid, sa[i]); 
	            		  }
	            		  }



	              else if (LastName.equalsIgnoreCase("svsr")){ int svsr[] = {8}; for (int
	            		  i=0;i<svsr.length;i++) { UserMethods.getRoles(Slugid, svsr[i]); 
	            		  }
	            		  }



	              else if (LastName.equalsIgnoreCase("svr")){ int svr[] = {27}; for (int
	            		  i=0;i<svr.length;i++) { UserMethods.getRoles(Slugid, svr[i]); 
	            		  }
	            		  }



	              else if (LastName.equalsIgnoreCase("ceb")){ int ceb[] = {12}; for (int
	            		  i=0;i<ceb.length;i++) { UserMethods.getRoles(Slugid, ceb[i]); 
	            		  }
	            		  }



	              else if (LastName.equalsIgnoreCase("sup")){ int sup[] = {16}; for (int
	            		  i=0;i<sup.length;i++) { UserMethods.getRoles(Slugid, sup[i]); 
	            		  }
	            		  }


	              else if (LastName.equalsIgnoreCase("anst")){ int anst[] = {33}; for (int
	            		  i=0;i<anst.length;i++) { UserMethods.getRoles(Slugid, anst[i]); 
	            		  }
	            		  }

	              else if (LastName.equalsIgnoreCase("monsr")){ int monsr[] = {22};
	              for (int i=0;i<monsr.length;i++) { UserMethods.getRoles(Slugid,
	            		  monsr[i]); 
	              }}

	              else if (LastName.equalsIgnoreCase("monr")){ int monr[] = {13,7};
	              for (int i=0;i<monr.length;i++) { UserMethods.getRoles(Slugid,monr[i]); 
	              }
	              }

	              else if (LastName.equalsIgnoreCase("monssr")){
	            	  int monssr[] = {15};
	              for (int i=0;i<monssr.length;i++) { 
	            	  UserMethods.getRoles(Slugid, monssr[i]);
	              }
	              }
	              
	              else if (LastName.equalsIgnoreCase("all")){
	            	  int all[] = {21,6,17,28,30,3,10,1,23,20,8,27,12,16,33,22,13,7,15};
	              for (int i=0;i<all.length;i++) { 
	            	  UserMethods.getRoles(Slugid, all[i]);
	              }
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
								{"DATEAM","sr","DATEAM1@gmailcom","DATEAMsr"},
								{"DATEAM","rev","DATEAM211@gmailcom","DATEAMrev"},
								{"DATEAM","sqc","DATEAM31@gmailcom","DATEAMsqc"},
								{"DATEAM","qc","DATEAM101@gmailcom","DATEAMqc"},
								{"DATEAM","ssa","DATEAM40@gmailcom","DATEAMssa"},
								{"DATEAM","sa","DATEAM50@gmailcom","DATEAMsa"},
								{"DATEAM","svsr","DATEAM60@gmailcom","DATEAMsvsr"},
								{"DATEAM","svr","DATEAM70@gmailcom","DATEAMsvr"},
								{"DATEAM","ceb","DATEAM80@gmailcom","DATEAMceb"},
								{"DATEAM","anst","DATEAM120@gmailcom","DATEAManst"},
								{"DATEAM","monsr","DATEAM90@gmailcom","DATEAMmonsr"},
								{"DATEAM","monr","DATEAM100@gmailcom","DATEAMmonr"},
								{"DATEAM","monssr","DATEAM110@gmailcom","DATEAMmonssr"},
								{"DATEAM","all","DATEAM130@gmailcom","DATEAMall"}
								
	
							};
	}
	

}




  
 
  
 


