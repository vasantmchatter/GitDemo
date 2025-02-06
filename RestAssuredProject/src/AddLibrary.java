import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddLibrary {
	@Test(dataProvider="booksData")
	public static void addBook(String aisle, String isbn) {
		//public static void addBook(String isbn, String aisle) {
	//public static void addBook(int isbn, String aisle) {
		RestAssured.baseURI ="http://216.10.245.166";
		String AddBookResponse= given().
		 header("Content-Type","application/Json").
		 body(Payload.addBookValue(isbn,aisle)).
		 when().post("Library/Addbook.php").
		 then().
		 assertThat().statusCode(200).
		 extract().response().asString();
		 JsonPath js=ReUsableMethods.rawToJson(AddBookResponse); 
		String ID=js.get("ID");
	 System.out.println("ID:"+ID);
	 
	 }
	
	@DataProvider(name="booksData")  //This helps to form connection between @Test and @DataProvider
	 public static Object[][] getData() {
		
		return new Object[] [] { {"Book1","2334"},{"book2","3788"},{"book3","37839"}};
		//return new Object[] [] { {"2334","Book1"},{"3788","book2"},{"37839","book3"}};
		//return new Object[] [] { {"Book1",2334},{"book2",3788},{"book3",37839}};
	}

	
	
	
}





