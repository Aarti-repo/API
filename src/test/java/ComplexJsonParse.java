import Files.Json_Data;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
	JsonPath js2	= new JsonPath(Json_Data.CoursePrice());        //  mock/dummy API Response for testing
		
	//	String Mockresponse = js2.getString("dashboard");
		
	//	System.out.println(js2.getString(Json_Data.CoursePrice()));

		//1. Print No of courses returned by API
		
		int count = js2.getInt("courses.size()");
		System.out.println(count);
		
		// 2.Print Purchase Amount
		
		 int totalAmount = js2.getInt("dashboard.purchaseAmount");
		 
		 System.out.println(totalAmount);
		 
	//	 3. Print Title of the first course
		 
		String FirstTitle =  js2.getString("courses[0].title");
		System.out.println(FirstTitle);
		
	//	4. Print All course titles and their respective Prices
		
		for (int i=0; i<count; i++) {
			
	String title = js2.get("courses["+i+"].title"); 
	int Price = js2.getInt("courses["+i+"].price");
	
	System.out.println(title +" "+Price);
	
	System.out.println(js2.get("courses["+i+"].copies").toString());
		}
		
		
		//5. Print no of copies sold by RPA Course
		
		for (int i=0; i<count; i++) {
			
			String title = js2.get("courses["+i+"].title");
			
			if(title.equalsIgnoreCase("RPA")) {
			
				System.out.println("Print no of copies sold by RPA Course");
			System.out.println(js2.get("courses["+i+"].copies").toString());
			break;
			}
		
		}
		
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount - nw class
		
		
		
	}

}
