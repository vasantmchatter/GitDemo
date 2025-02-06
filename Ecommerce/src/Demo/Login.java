package Demo;

import io.restassured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.ListOrders;
import pojo.LoginPojo;
import pojo.LoginResponse;
import pojo.OrderDetails;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  
	RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
	
	LoginPojo LoginPojo = new LoginPojo();
	LoginPojo.setUserEmail("vasantmchatter@gmail.com");
	LoginPojo.setUserPassword("Jobs@1234");
	
	RequestSpecification request=given().spec(req).body(LoginPojo);
	//System.out.println(request);
	LoginResponse Response=request.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
	String Token =Response.getToken();
	String Userid= Response.getUserId();
	
	
	
		//Add to product or create product
	 RequestSpecification ReqaddProduct =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",Token).build();
	 
	 RequestSpecification ReqProductbody= given().spec(ReqaddProduct).param("productName","cube").param("productAddedBy",Userid).param("productCategory","Fashion").
	  param("productSubCategory","cube").param("productPrice","3000").param("productDescription","cube").
	  param("productFor","material").
	  multiPart("productImage",new File("D:\\vasant\\Rest API Notes\\api file screenshot.png"));
	  
	 String ResponseAddProduct =ReqProductbody.when().post("api/ecom/product/add-product").then().log().all().extract().response().asString();
	  JsonPath js =new JsonPath(ResponseAddProduct);
	  String productid = js.getString("productId");
	  
	  
	  // Create Product 
	  
	  RequestSpecification CreateProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			  addHeader("Authorization",Token).setContentType(ContentType.JSON).build();
	  OrderDetails orderDetails =new OrderDetails();
	  orderDetails.setCountry("India");
	  orderDetails.setProductOrderedId(productid);
	  
	  List<OrderDetails> Orders =new ArrayList();
	  Orders.add(orderDetails);
	  
	  ListOrders listorders =new ListOrders();
	  listorders.setOrders(Orders);
	  
	    RequestSpecification addtocart=given().spec(CreateProduct).body(listorders);
	    
	   String AddTocartDetails = when().post("api/ecom/order/create-order").then().log().all().extract().response().asString();
	  
	  //Delete Product
	   
	   RequestSpecification deletespec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",Token).build();
	   
	   RequestSpecification deleterequest=  given().spec(deletespec).pathParam("productId",productid);
	   
	 String DeleteResponse =  deleterequest.when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	   
	 JsonPath JS1= new JsonPath(DeleteResponse);
			Assert.assertEquals("Product Deleted Successfully", get("message")) ;
	   
	   
	  
	}

}
