import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumofValidation {
	@Test
	public static void sumOfCourse(){
         int sum=0;
		JsonPath js= new JsonPath(Payload.CoursePrice());
		int count=js.get("courses.size()");
		for (int i=0;i<count;i++) {
			int price=js.get("courses["+i+"].price");
			int totalcopies=js.get("courses["+i+"].copies");
			int amount= price * totalcopies;
			System.out.println(amount);		
			sum=sum+amount;
		}
     System.out.println("Totalsum:" +sum);
     int totalPurchaseAmount=js.getInt("dashboard.purchaseAmount");
     System.out.println("totalPurchaseAmount:"+totalPurchaseAmount);
     	Assert.assertEquals(sum, totalPurchaseAmount); //To compare the value in RestAssured/TestNG/Junit
	}
}
