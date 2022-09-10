package com.qa.nopencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	
	
	/**
	 * this method is used to initilize to WebDriver
	 * @param browserName
	 * @return this will return the driver
	 */
	
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName=prop.getProperty("browser").trim();
		System.out.println("browser name is :"+ browserName);
		
		if(browserName.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
		}
		else {
			System.out.println("please pass the right browser :"+ browserName);
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	/**
	 * this method is used to initialize the properties
	 * @return
	 * this will return properties porp reference
	 */
	public Properties init_prop() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/test/resource/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
