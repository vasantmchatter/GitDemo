package Demo;



	import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.API;
import POJO.GetCourse;
import POJO.webAutomation;
import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.specification.RequestSpecification;



	public class OAuth {

		public static void main(String[] args) {
			String[] courseTitle= {"Selenium Webdriver Java","Cypress","Protractor"};
			
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
				String AccessToken= Js.getString("access_token");
				System.out.println(AccessToken);
				
		/*	//Get Course Details
				String GetCourseDetails=
						 given().log().all().
						 queryParam("access_token",AccessTokenValue).
						 when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").
						 then().log().all().extract().response().asString(); */
				
						 
					
		//Get courses deatails Uisng Deserialization by Using POJO class 
				
			GetCourse gc =given().log().all().
					 queryParam("access_token",AccessToken).
					 when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
			//need to print linkedin URL
			   System.out.println(gc.getLinkedIn());
			   System.out.println(gc.getInstructor());
			   
			   
	   //Get the all the title of API and Price of SOAP UI title
			   
		String APIcourseTitle =gc.getCourses().getAPI().get(1).getcourseTitle();
			   System.out.println(APIcourseTitle);
			   
			   
		//when title	SoapUI Webservices testing then print the price    
			  List<API> ApiCourses= gc.getCourses().getAPI();
			   
			   
		  for (int i=0;i<ApiCourses.size();i++) {
				   if(ApiCourses.get(i).getcourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
					   String apiprice=ApiCourses.get(i).getprice();
					   System.out.println(apiprice);
				   }
			   }
			
		  //Course title of webautomation
		  
		     String webCourseTitle= gc.getCourses().getWebAutomation().get(0).getcourseTitle();
		     System.out.println(webCourseTitle);
		     
		    List<webAutomation> alllWebCourseTitle= gc.getCourses().getWebAutomation();
		    System.out.println(alllWebCourseTitle);
		    
		     for (int i=0; i<alllWebCourseTitle.size();i++) {
		    	 
		    	 //String webtitles = gc.getCourses().getWebAutomation().get(i).getcourseTitle();
		    	 
		    	// System.out.println("webtitles:" +webtitles);
		    	 
               String webtitles = alllWebCourseTitle.get(i).getcourseTitle();
		    	 
		    	 System.out.println("webtitles:" +webtitles);
		    	 

	}
		     //Explicit array by using Arralist
		     
		     ArrayList<String> a= new ArrayList<String>();
		     for (int i=0; i<alllWebCourseTitle.size();i++) {
		    	 
		     a.add( alllWebCourseTitle.get(i).getcourseTitle());
		     }
		     
		    List<String> expectedlist= Arrays.asList(courseTitle);
		     a.equals(expectedlist);
		     Assert.assertTrue(a.equals(expectedlist));
		     
		     
		}

		

		
	}
