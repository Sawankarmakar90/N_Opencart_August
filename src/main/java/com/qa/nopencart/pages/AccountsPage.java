package com.qa.nopencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.nopencart.utils.Constants;
import com.qa.nopencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;	
		eleUtil=new ElementUtil(driver);
	}
	
	private By header=By.cssSelector("div#logo a");
	private By accountsSections=By.cssSelector("div#content h2");
	private By searchField=By.name("search");
	private By searchBtn=By.cssSelector("div#search button");
	private By logoutLink=By.linkText("Logout");
	
	public String getAccountPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	public String getAccountPageHeader() {
		return eleUtil.doGetText(header);
	}
	public boolean isLogoutListExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	public void logout() {
		if(isLogoutListExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	public List<String> getAccountSecList() {
		List<WebElement>accSecList=eleUtil.waitForElementsToBeVisible(accountsSections, 10);
		List<String>accSecValList=new ArrayList<String>();
		for(WebElement e: accSecList) {
			String text=e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(searchField);
	}
	public void doSearch(String productName) {
		System.out.println("search the product :"+ productName );
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchBtn);
	}

}
