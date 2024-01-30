package com.Testautomation.apiTesting.Utils.test;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
	
	@BeforeMethod
	public void beforeMethod() {
	
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

}
	}
