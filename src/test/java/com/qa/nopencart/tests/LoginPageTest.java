package com.qa.nopencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.nopencart.utils.Constants;
public class LoginPageTest extends BaseTest{
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle=loginpage.getLoginPageTitle();
		System.out.println("page title :"+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	@Test(priority=2)
	public void loginPageUrlTest() {
	Assert.assertTrue(loginpage.getLoginPageUrl());
	}
	@Test(priority=3)
	public void forgotpwdLinkTest() {
	Assert.assertTrue(loginpage.isForgetPwdLinkExist());
	}
	@Test(priority=4)
	public void registerLinkTest() {
		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}
	@Test(priority=5)
	public void loginTest() {
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
