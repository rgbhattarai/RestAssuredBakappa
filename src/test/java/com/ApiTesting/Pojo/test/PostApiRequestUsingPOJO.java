package com.ApiTesting.Pojo.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.Testautomation.apiTesting.Utils.test.FileNameCons;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostApiRequestUsingPOJO {

	
	@Test
	public void postApiRequest () {
		
		try {
			
				String jsonSchema=FileUtils.readFileToString(new File(FileNameCons.json_Scheman),"utf-8");
			 
			
		BookingDates bookingDates = new BookingDates ("2023-03-25","2023-03-29");
		Booking booking = new Booking ("api testing","tutorial","breakfast",1000,true,bookingDates);
		
		ObjectMapper objectMapper = new ObjectMapper ();
		
			String requestBody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
		
		System.out.println(requestBody);
		
		//de-searialization
		
		Booking bookingDetails=	objectMapper.readValue(requestBody, Booking.class);
		
		System.out.println(bookingDetails.getFirstname());
		System.out.println(bookingDetails.getLastname());
		
		Response response =
		RestAssured 
		 .given()
		 
		 		.contentType(ContentType.JSON)
		 		.body(requestBody)
		 		.baseUri("https://restful-booker.herokuapp.com/booking")
		 .when()	
		 		.post()
		 .then()	
		 		.assertThat()
		 		.statusCode(200)
		 		
		 		.extract()
		 		.response();
		 		
		 	int bookingID=	response.path("bookingid");
		 	
		 //	System.out.println(jsonSchema);
		 	
		 	
		 	RestAssured 
			 .given()
			 
			 		.contentType(ContentType.JSON)
			 		.baseUri("https://restful-booker.herokuapp.com/booking")
			 .when()	
			 		.get("/{bookingid}",bookingID)
			 .then()	
			 		.assertThat()
			 		.statusCode(200)
			 		.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema))
			 		
		;} 
	
		
		
		
		
		catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
