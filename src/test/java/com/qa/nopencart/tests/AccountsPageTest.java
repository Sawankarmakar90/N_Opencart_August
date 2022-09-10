package com.qa.nopencart.tests;

import org.testng.annotations.BeforeClass;

public class AccountsPageTest extends BaseTest{
@BeforeClass
public void accPageSetup() {
	loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}

}
