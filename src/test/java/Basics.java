import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;  ///for given method pkg bcoz its static method
import static org.hamcrest.Matchers.*;         //pkg available in java - need to remember bcoz this pkg is under static library.

import Files.Json_Data;
import Files.ReusableMethods;
import POJO_Classes.Get_Details;

public class Basics {

	public static void main(String[] args) {

		//given method - all input details
		// when method - submit the API - Resources and http method like put post
		// Then - validate the response
		
		// All the API test automation will wrap in above three methods
	
//step1 : Validate if add Place API is working as Expected
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	String response =	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")     //log all method will show all details valid only for input
		.body(Json_Data.AddPlace())                                                          // Eclipse will automatically convert this json format into string  
	     .when().post("/maps/api/place/add/json")                                                        // post method accept URI as arg which is nothing but Resources
	     .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))                                        //all response log details will get logged and u can see it.
	     .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();                                    // whatever written after then is for output response validation.
	
	System.out.println(response);
			
	
//Step2: Add Place-> update place wid new address-> Get place to validate if new address is present in response.
	
	JsonPath js = new JsonPath(response);  //  JsonPath class will convert the given string into Json, and js object will used for Parsing Json
	String placeID = js.getString("place_id");    //path of the string which u want to check
	
	System.out.println(placeID);
	
	
        // Update Place
	
	      String newAddress = "Nanded";
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	    .body("{\r\n"
	    		+ "\"place_id\":\""+ placeID +"\",\r\n"
	    		+ "\"address\":\""+ newAddress +"\",\r\n"
	    		+ "\"key\":\"qaclick123\"\r\n"
	    		+ "}")
	    .when().put("/maps/api/place/update/json")
	    .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));         //inbuild restAssured assertion method
	
	
	    // Get Place
/*	    
	    
	   String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")                              //header is not require in get method bcoz we dnt give body in it.
	    
	    .queryParam("place_id", placeID)
	    .when().get("/maps/api/place/get/json")
	    .then().assertThat().statusCode(200).extract().response().asString();      // Also use can body with asertion as - body("address", equalTo(newAddress)); for address validation
	
	   
	   System.out.println(getPlaceResponse);
//  	JsonPath js1 = new JsonPath(getPlaceResponse);                        // we have used this in seperate class
	   
	JsonPath js1 =    ReusableMethods.rawToJson(getPlaceResponse);
	String actualAddress = js1.getString("address");l
	   
	//Junit or TestNG framework jar we need to use for assertion bcoz there is no any inbuild method in java for assertion.
	
	Assert.assertEquals(newAddress, actualAddress);                   //java method for assertion using testng
	//System.out.println(Address);     */

	/* ********************************using POJO class - Deserilising Response payload is converting to java object****************************************/	
	
     Get_Details gd = given().queryParam("key", "qaclick123").queryParam("place_id", placeID).expect().defaultParser(Parser.JSON)  // - method for understanding restassured to response is in json format.
	                  .when().get("/maps/api/place/get/json").as(Get_Details.class);
	
	System.out.println(gd.getAccuracy());
	System.out.println(gd.getTypes());
	System.out.println(gd.getWebsite());
	
	
	}
	
}
