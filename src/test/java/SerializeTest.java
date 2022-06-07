import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO_Classes.Get_Details;
import POJO_Classes.Post_Details;
import POJO_Classes.location_post;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	Post_Details p = new Post_Details();	
	
		p.setAccuracy(10);
		p.setAddress("Raj PRide C");
		p.setLanguage("English");
		p.setName("Aarti");
		p.setPhone_number("9787566456");
		p.setWebsite("aartinalge97@gmail.com");
		
		List<String> l1 = new ArrayList<String>();
		l1.add("shop1");
		l1.add("shop2");
		
		p.setTypes(l1);
		
		
		location_post lp1 = new location_post();
		lp1.setLat(-38.383494);
		lp1.setLng(33.427362);
		
		p.setLocation(lp1);
		
	
	
	String response =	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
	
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);  //  JsonPath class will convert the given string into Json, and js bject will used for Parsing Json
	String placeID = js.getString("place_id");    //path of the string which u want to check
	
	System.out.println(placeID);
	
	
	Get_Details gc = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
			         .when().get("/maps/api/place/get/json").as(Get_Details.class);
	
//	  Get_Details gc = given().queryParam("key", "qaclick123").queryParam("place_id", placeID).expect().defaultParser(Parser.JSON)  // - method for understanding restassured to response is in json format.
//              .when().get("/maps/api/place/get/json").as(Get_Details.class);

	
	System.out.println(gc.getAccuracy());
	System.out.println(gc.getWebsite());
	System.out.println(gc.getTypes());
			       
	}

}
