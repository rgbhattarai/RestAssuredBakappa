package com.Testautomation.apiTesting.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.Testautomation.apiTesting.Utils.test.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PostApiRequest extends BaseTest {

	@Test 
	 public void createBooking() {
		
		
		 
		// prepare request Body
		JSONObject booking = new JSONObject ();
		JSONObject bookingDate = new JSONObject ();
		
		booking.put("firstname", "api testing");
		booking.put("lastname", "tutorial");
		booking.put("totalprice", 1000);
		booking.put("depositpaid", "true");
		booking.put("additionalneeds", "Breakfast");
		booking.put("bookingdates", bookingDate);
		
		bookingDate.put("checkin","2023-03-25");
		bookingDate.put("checkout","2023-03-29");
		
		Response response = 
		RestAssured  
			.given()
				.contentType(ContentType.JSON)
				.body(booking.toString())
				.baseUri("https://restful-booker.herokuapp.com/booking")
			//	.log().all()
			.when()
		     	.post()
		    .then()
				.assertThat()
				.statusCode(200)
				.body("booking.firstname", Matchers.equalTo("api testing"))
				.body("booking.totalprice", Matchers.equalTo(1000))
				.body("booking.bookingdates.checkin", Matchers.equalTo("2023-03-25"))
				.body("booking.bookingdates.checkout", Matchers.equalTo("2023-03-29"))
			//	.log().all()
				//.log().ifValidationFails()
				
			.extract()
			    .response();
		
		     int bookingID = response.path("bookingid");
		     
		     RestAssured 
		      .given()
		      		.contentType(ContentType.JSON)
		      		.pathParam("bookingid",bookingID )
		      		.baseUri("https://restful-booker.herokuapp.com/booking")
		      .when()		
		      
		            .get("{bookingid}")
		      .then()
		      		.assertThat()
		      		.statusCode(200)
		      		.body("firstname",Matchers.equalTo("api testing"))
		      		.body( "totalprice",Matchers.equalTo(1000))
	; }
}
