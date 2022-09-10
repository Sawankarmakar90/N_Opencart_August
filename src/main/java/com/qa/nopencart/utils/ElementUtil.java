package com.qa.nopencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public static By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "clssname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			System.out.println("please pass the right locatorType and locator value......");
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(By locator, int timeOut) {
		return doPresenceOfElementLocated(locator, timeOut);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}
    public void doClear(By locator) {
    	getElement(locator).clear();
    }
	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int timeOut) {
		doPresenceOfElementLocated(locator, timeOut).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int timeOut, long intervalTime) {
		doPresenceOfElementLocated(locator, timeOut, intervalTime).sendKeys(value);
	}

	public void doSendKeys(By locator, int timeOut, String value) {
		waitForElementToBeVisible(locator, timeOut).sendKeys(value);
	}

	public void doSendKeys(By locator, int timeOut, long intervalTime, String value) {
		waitForElementToBeVisible(locator, timeOut, intervalTime).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	public void doClick(By locator, int timeOut) {
		doPresenceOfElementLocated(locator, timeOut).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String getAttributeValue(By locator, String attrName) {
		String attrVal = getElement(locator).getAttribute(attrName);
		System.out.println(attrVal);
		return attrVal;
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isElementExist(By locator) {
		int elementCount = getElementCount(locator);
		System.out.println("total element found:" + elementCount);
		if (elementCount <= 1) {
			System.out.println("element id found:" + locator);
			return true;
		} else {
			System.out.println("element is not found:" + locator);
			return false;
		}
	}

	public List<String> getElementTextCount(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (!eleText.isEmpty()) {
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public void printElementValue(List<String> eleList) {
		for (String e : eleList) {
			System.out.println(e);
		}
	}

	public List<String> getAttributeList(By locator, String attributeName) {
		List<WebElement> eleList = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attributeName);
			attrList.add(attrValue);
		}
		return attrList;
	}

	/*********************** Drop Down Utils **********************/

	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doDropDownSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void selectDropDownValueWithourSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/********************** Links Util *************************/
	public List<String> getLinksTextList(By locator) {
		List<WebElement> linkList = getElements(locator);
		List<String> linkTextList = new ArrayList<String>();
		System.out.println(linkList.size());
		for (WebElement e : linkList) {
			String text = e.getText().trim();
			linkTextList.add(text);
		}
		return linkTextList;
	}

	public void clickOnElementFromSection(By locator, String textValue) {
		List<WebElement> langList = getElements(locator);
		System.out.println(langList.size());
		for (WebElement e : langList) {
			String text = e.getText().trim();
			System.out.println(text);
			if (text.equals(textValue)) {
				e.click();
				break;
			}
		}
	}

	/*********************** WebTable Util **************************/
	public void printTable(By rowLocator, By colLocator, String beforeXpath, String afterXpath) {
		int rowCount = getElements(rowLocator).size();
		int colCount = getElements(colLocator).size();
		for (int row = 2; row <= rowCount; row++) {
			for (int col = 1; col <= colCount; col++) {
				String xpath = beforeXpath + row + afterXpath + col + "]";
				String text = doGetText(By.xpath(xpath));
				System.out.print(text + "     |     ");
			}
			System.out.println();
		}
	}

	/************************ Actions Util ***************************/
	public void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}

	public void doClickOnChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);
	}

	public void doActionClick(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionClicOnActiveElement(By locator, String value) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}

	/****************************** Wait Utils ********************************/

	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement doPresenceOfElementLocated(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	/******************* Wait Util For Non-WebElements *********************/
	public Boolean waitForURLContain(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}

	public Boolean waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}

	public String doGetTitleWithFraction(String titleFraction, int timeOut) {
		if (waitForTitleContain(titleFraction, timeOut)) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String doGetTitle(String title, int timeOut) {
		if (waitForTitleToBe(title, timeOut)) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public Boolean waitForTitleContain(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}

	public Boolean waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void doAlertAccept(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void doAlertDismiss(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void enterAlertText(String text, int timeOut) {
		waitForAlert(timeOut).sendKeys(null);
	}

	public void waitForFrameByIdOrName(String nemeOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nemeOrId));
	}

	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickElementWhenReady(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementPresentByFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage(Error.ELEMENT_NOT_FOUND_ERROR_MESSEGE);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementPresentByWebDriverWait(By loctor, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage(Error.ELEMENT_NOT_FOUND_ERROR_MESSEGE);
		wait.until(ExpectedConditions.presenceOfElementLocated(loctor));
	}


	/********************** Custom Wait **************************/

	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("element is not found in attempt:" + attempts + " : " + locator);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if (element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("element not found exception...tried for :" + timeOut + " with the interval of : "
						+ 500 + "ms");
			}
		}
		return element;
	}

	public WebElement retryingElement(By locator, int timeOut, long intervalTime) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("element is not found in attempt :" + attempts + " : " + locator);
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if (element == null) {
				try {
					throw new Exception();
				} catch (Exception e) {
					System.out.println("element is not found exception...tried for :" + timeOut + " : "
							+ "with the interval of:" + intervalTime + "mc");
				}
				attempts++;
			}
		}
		return element;
	}
}
