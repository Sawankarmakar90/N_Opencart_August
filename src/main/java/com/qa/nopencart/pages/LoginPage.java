package com.qa.nopencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nopencart.utils.Constants;
import com.qa.nopencart.utils.ElementUtil;

public class LoginPage {
	//1. declare private WebDriver
	private WebDriver driver;
	private ElementUtil eleUtil;
	//2. Create page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	//3. Create private By Locator
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	
	//4.page actions
	
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	public boolean getLoginPageUrl() {
		 return eleUtil.waitForURLContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	public boolean isForgetPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);	
	}
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	public void doLogin(String un,String pwd) {
		System.out.println("login with :" + un +" "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	}
		

}
