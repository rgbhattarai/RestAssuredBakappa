package com.Testautomation.apiTesting.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Testautomation.apiTesting.Utils.test.FileNameCons;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PatchApiRequest {
	@Test
	public void postApiRequest () {
		try {
			String postApiRequestBody=	FileUtils.readFileToString(new File(FileNameCons.POST_API_REQUEST_BODY),"UTF-8");
		    String tokenApiRequest= FileUtils.readFileToString(new File(FileNameCons.Token_Request_Body),"UTF-8");
		    String putApiRequestBody = FileUtils.readFileToString(new File(FileNameCons.PUT_Api_Request_Body),"UTF-8");
		    String patchApiRequestBody = FileUtils.readFileToString(new File(FileNameCons.Patch_Api_request_Body),"UTF-8");
		    
		    // System.out.println(postApiRequestBody);
		Response response=	
			RestAssured 
			.given()
				.contentType(ContentType.JSON)
				.body(postApiRequestBody)
				.baseUri("https://restful-booker.herokuapp.com/booking")
			
				.when()
					.post()
				.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.response();
		
JSONArray jsonArray =JsonPath.read(response.body().asString(), "$.booking..firstname");
		String firstName = (String) jsonArray.get(0);
		
		Assert.assertEquals(firstName, "api testing");
		
		int bookingID=JsonPath.read(response.body().asString(), "$.bookingid");
	
		// get API call
		
		RestAssured 
		
		 .given()
		 	.contentType(ContentType.JSON)
		 	.baseUri("https://restful-booker.herokuapp.com/booking")
		 .when ()
		 	.get("/{bookingId}",bookingID)
		 .then()
		 	.assertThat()
		 	.statusCode(200);
		 	
		 	// token generation
		 	Response tokenApiresponse=
		 	RestAssured 
		 	.given()
		 		.contentType(ContentType.JSON)
		 		.body(tokenApiRequest)
		 		.baseUri("https://restful-booker.herokuapp.com/auth")
		 	.when()
		 	   .post()
		 	   
		 	.then()
		 	    .assertThat()
		 	    .statusCode(200)
		 	    .extract()
		 	    .response();
		 	    
		 	   String tokenId=JsonPath.read(tokenApiresponse.body().asString(), "$.token");
		 	
		 	   // put api call
		 	   RestAssured 
		 	     .given() 
		 	     	.contentType(ContentType.JSON)
		 	     	
		 	     	.body(putApiRequestBody)
		 	     	.header("Cookie","token="+tokenId)
		 	     	.baseUri("https://restful-booker.herokuapp.com/booking")
		 	     	
		 	     .when()
		 	        .put("/{bookingID}",bookingID)
		 	     .then()
		 	     	.assertThat()
		 	     	.statusCode(200)
		 	     	.body("firstname", Matchers.equalTo("Rushie"));
		 	     	
		 	     	
		 	     	// patch api call
		 	   RestAssured 
		 	   .given()
		 	   	.contentType(ContentType.JSON)
		 	   	.body(patchApiRequestBody)
		 	   .header("Cookie","token="+tokenId)
	 	     	.baseUri("https://restful-booker.herokuapp.com/booking")
	 	     	
	 	     .when()
	 	        .patch("/{bookingID}",bookingID)
	 	     .then()
	 	     	.assertThat()
	 	     	.statusCode(200)
	 	     	.body("firstname", Matchers.equalTo("Testers Talk"));
	 	     	
		 	     	;
		 	        
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
