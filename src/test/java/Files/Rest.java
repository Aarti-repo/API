
package Files;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; 
public class Rest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		RestAssured.baseURI = "https://reqres.in/api/user?page=2";
		
		
	String Response =	given().log().all().queryParam("page", "2").queryParam("id", "9")
		.when().get("api/user")
		.then().assertThat().statusCode(200).extract().response().toString();

	System.out.println(Response);
	
		
	}

}
