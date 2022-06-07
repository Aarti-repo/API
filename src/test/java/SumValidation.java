import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Json_Data;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	
	//6. Verify if Sum of all Course prices matches with Purchase Amount - nw class
	
	int sum =0;
	
	@Test
	public void SumofCourses() {
		
		JsonPath js2	= new JsonPath(Json_Data.CoursePrice());
		int count = js2.getInt("courses.size()");
		
		for(int i = 0;i<count;i++) {
			
			int copies = js2.getInt("courses["+i+"].copies");
			int price = js2.getInt("courses["+i+"].price");
			
			int amount =price *copies;
			System.out.println(amount);
			
			sum = sum+ amount;
		}
		System.out.println("total sum is :"+ sum);
		
		int Purchaseamt = js2.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, Purchaseamt);
		
		System.out.println("Equal");
		
		
	}
}
