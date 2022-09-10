package com.qa.nopencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.nopencart.factory.DriverFactory;
import com.qa.nopencart.pages.LoginPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	LoginPage loginpage;
	Properties prop;
	
	@BeforeTest
	public void setup() {
	 df=new DriverFactory();
	 prop=df.init_prop();
	 driver=df.init_driver(prop);
	 loginpage=new LoginPage(driver);
		
	}
	@AfterTest
	public void tearDown() {
		
	}

}
