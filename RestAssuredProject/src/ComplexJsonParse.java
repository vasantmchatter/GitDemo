
import Files.Payload;
import io.restassured.path.json.JsonPath;
public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JsonPath js2= new JsonPath(Payload.CoursePrice());
		
		//Print No of Courses Edited By API
		int CourseCount =js2.getInt("courses.size()");  
		System.out.println(CourseCount);
		
		// Print Purchase Amount
		int PurchaseAmount= js2.getInt("dashboard.purchaseAmount");
		System.out.println(PurchaseAmount);
		
		//Print Title of First Course
		String FirstCourseTitle = js2.getString("courses[0].title");
		System.out.println(FirstCourseTitle);
		
		
		// Using for loop iterate all the element in the courses array
		for (int i=0; i<CourseCount; i++) {
			
			String allCourseTitles= js2.get("courses["+i+"].title");
			
			System.out.println("allCourseTitles:," +allCourseTitles);
			
		    //System.out.println(js2.get("courses["+i+"].title").toString());//Converting to all string 
			
			int printAllPrice= js2.get("courses["+i+"].price");
			//System.out.println("allCourseTitles:," +allCourseTitles);
			System.out.println("printAllPrice:," +printAllPrice);
			//System.out.println(js2.get("course["+i+"].price").toString());//Converting to all string 
					
	}

//================+++++++++++++++++++Print No.of copies sold by RPA Course++++++++++++++++++++++==========================================++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		for (int i=0; i<CourseCount; i++ ) {
			
		    String allCourseTitles =js2.get("courses["+i+"].title");
			if(allCourseTitles.equalsIgnoreCase("RPA")) {
				int NoofCopies=js2.get("courses["+i+"].copies");
				System.out.println(NoofCopies);
				break;
			}
			
		}
		
		
		
	}
}

