package com.Testautomation.apiTesting.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

import com.Testautomation.apiTesting.Utils.test.BaseTest;
import com.Testautomation.apiTesting.Utils.test.FileNameCons;
import com.jayway.jsonpath.JsonPath;

public class PostApiRequestUsingFile extends BaseTest  {
	
	@Test
	public void postApiRequest () {
		try {
			String postApiRequestBody=	FileUtils.readFileToString(new File(FileNameCons.POST_API_REQUEST_BODY),"UTF-8");
		
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
		
		JSONArray jsonArray1 =JsonPath.read(response.body().asString(), "$.booking..lastname");
		String lastName = (String) jsonArray1.get(0);
		Assert.assertEquals(lastName, "tutorial");
		
		int bookingID=JsonPath.read(response.body().asString(), "$.bookingid");
		
		RestAssured 
		
		 .given()
		 	.contentType(ContentType.JSON)
		 	.baseUri("https://restful-booker.herokuapp.com/booking")
		 .when ()
		 	.get("/{bookingId}",bookingID)
		 .then()
		 	.assertThat()
		 	.statusCode(200)
		
		;} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
