package com.pageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.utils.Screenshot;
import com.utils.WebElementUtils;

public class WebTables {
	WebDriver driver;
	Properties obj;
	
	@FindBy(xpath="//div[@class=\"rt-tr -even\"][1]/div[4]") WebElement fetchedData;
	
	@FindBy(id="submit") WebElement submitButton;
	
	@FindBy(id="userEmail") WebElement updateEmail;
	
	@FindBy(how = How.CSS, using = "#edit-record-2 path") WebElement editIcon;

	@FindBy(how=How.ID, using="item-3") WebElement webTables;
	
	@FindBy(xpath="//div[@class=\"accordion\"]/div[1]/span/div") WebElement elements;
	
	@FindBy(xpath="//div[@class=\"category-cards\"]/div[5]") WebElement interactionsTab;

	
	
	WebElementUtils webUtils=new WebElementUtils();

	public WebTables(WebDriver driver, Properties obj) {
		this.driver = driver;
		this.obj=obj;
		
		PageFactory.initElements(driver, this);	
	}

	public String testFlow(String timeStamp) throws AWTException, IOException {
		clickOnInteractionTab();
		clickOnElements();
		webUtils.scrollingDownWindow(driver,0,180);
		clickOnWebTables();
		clickOnEdit2();
		editEmailAddress();
		clickOnSubmit();
		return verifyUpdatedData(timeStamp);//validation
	}

	private String verifyUpdatedData(String timeStamp) throws IOException {

		
		//Takes Screenshot
		Screenshot scr=new Screenshot(driver);
		scr.clickScreenshot(timeStamp,"Values updated.png");

		//fetches value from table
		String updatedEmail=webUtils.fetchText(driver, fetchedData);

		return updatedEmail;

	}

	private void clickOnSubmit() {
		// Click on submit
		webUtils.webElementClick(driver, submitButton);
	}

	private void editEmailAddress() {
		// update email address
		webUtils.sendKeys(driver, updateEmail, obj.getProperty("updatedEmail"));
	}

	private void clickOnEdit2() {
		// Click on edit icon
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webUtils.webElementClick(driver, editIcon);
	}

	private void clickOnWebTables() {
		// Click on WebTables
		webUtils.webElementClick(driver, webTables);
	}

	private void clickOnElements() {
		// Clicking on elements
		webUtils.webElementClick(driver, elements);
	}
	private void clickOnInteractionTab() {
		// clicking on interactions tab
		webUtils.webElementClick(driver, interactionsTab);
	}

}
