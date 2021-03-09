package com.pageObjects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.Screenshot;
import com.utils.WebElementUtils;

public class InteractionsTab {

	WebDriver driver;
	Properties obj;
	
	//WebElements
	@FindBy(id="closeLargeModal") WebElement closeButton;
	
	@FindBy(id="submit") WebElement submitButton;
	
	@FindBy(id="state") WebElement selectStateMenu;

	@FindBy(xpath="//*[text()=\"NCR\"]") WebElement selectState;

	@FindBy(id="city") WebElement selectCityMenu;
	
	@FindBy(xpath="//*[text()=\"Delhi\"]") WebElement selectCity;

	@FindBy(id="currentAddress") WebElement currentAddress;

	@FindBy(xpath="//*[@id=\"uploadPicture\"]") WebElement uploadPicture;

	@FindBy(xpath="//label[@for=\"hobbies-checkbox-1\"]") WebElement hobbies1;
	
	@FindBy(xpath="//label[@for=\"hobbies-checkbox-2\"]") WebElement hobbies2;

	@FindBy(id="subjectsInput") WebElement subjectInput;
	
	@FindBy(id="react-select-2-option-0") WebElement selectSubject;
	
	@FindBy(id="dateOfBirthInput") WebElement dobInput;
	
	@FindBy(className="react-datepicker__year-select") WebElement selectYearDropdown;

	@FindBy(xpath="//option[text()=\"1999\"]") WebElement selectYear;

	@FindBy(xpath="//option[text()=\"March\"]") WebElement selectMonthDropdown;

	@FindBy(xpath="//option[text()=\"March\"]") WebElement selectMonth;

	@FindBy(xpath="//div[text()=\"12\"]") WebElement selectDay;

	@FindBy(id="userNumber") WebElement mobile;
	
	@FindBy(xpath="//label[@for=\"gender-radio-1\"]") WebElement gender;

	@FindBy(xpath="//input[@id=\"userEmail\"]") WebElement email;

	@FindBy(id="firstName") WebElement firstName;
	
	@FindBy(id="lastName") WebElement lastName;
	
	@FindBy(xpath="//span[text()=\"Practice Form\"]") WebElement practiceForm;
	
	@FindBy(xpath="//div[@class=\"accordion\"]/div[2]/span/div") WebElement forms;
	
	@FindBy(xpath="//div[@class=\"category-cards\"]/div[5]") WebElement interactionsTab;


	
	WebElementUtils webUtils=new WebElementUtils();

	public InteractionsTab(WebDriver driver, Properties obj) {
		this.driver = driver;
		this.obj=obj;
		PageFactory.initElements(driver, this);	
	}

	public boolean testFlow(String timeStamp) throws IOException {
		clickOnInteractionTab();
		clickOnForms();
		clickOnPracticeForms();
		fillingForm();
		Boolean bo=clickOnSubmit();

		//Takes Screenshot
		Screenshot scr=new Screenshot(driver);
		scr.clickScreenshot(timeStamp,"form submitted.png");
		clickOnCloseButton();
		return bo;
	}

	private void clickOnCloseButton() {
		// Closing Form
		webUtils.webElementClick(driver, closeButton);
	}

	private boolean clickOnSubmit() {
		// Clicking submit button
		webUtils.webElementClick(driver, submitButton);
		return true;
	}

	private void fillingForm() {
		// step flow to fill the form
		setName();
		setEmail();
		setGender();
		setMobileNumber();
		setDateOfBirth();
		setSubjects();
		setHobbies();
		uploadPicture();
		setCurrentAddress();
		selectStateAndCity();

	}

	private void selectStateAndCity() {
		// Selecting state
		webUtils.webElementClick(driver, selectStateMenu);		
		webUtils.webElementClick(driver, selectState);		

		//selecting city
		webUtils.webElementClick(driver, selectCityMenu);		
		webUtils.webElementClick(driver, selectCity);		
	}

	private void setCurrentAddress() {
		// filling current address
		webUtils.sendKeys(driver, currentAddress, "address");
	}

	private void uploadPicture() {
		// uploading picture
		webUtils.sendKeys(driver, uploadPicture, System.getProperty("user.dir")+"\\Images\\iTime.PNG");

	}

	private void setHobbies() {
		// selecting hobbies
		webUtils.webElementClick(driver, hobbies1);
		webUtils.webElementClick(driver, hobbies2);

	}

	private void setSubjects() {
		// filling subjects
		webUtils.sendKeys(driver, subjectInput, "Maths");
		webUtils.webElementClick(driver, selectSubject);

	}

	private void setDateOfBirth() {
		//clicks on date of birth field

		webUtils.webElementClick(driver, dobInput);
		//click on year select
		webUtils.webElementClick(driver, selectYearDropdown);
		//selecting year as 1999
		webUtils.webElementClick(driver, selectYear);		
		//click on month select
		webUtils.webElementClick(driver, selectMonthDropdown);		
		//selecting month as march
		webUtils.webElementClick(driver, selectMonth);		
		//selecting day as 12
		webUtils.webElementClick(driver, selectDay);		
	}

	private void setMobileNumber() {
		//filling mobile number
		webUtils.sendKeys(driver, mobile, "7981988418");
	}

	private void setGender() {
		//selecting Gender as Male
		webUtils.webElementClick(driver, gender);
	}

	private void setEmail() {
		//filling email address
		webUtils.sendKeys(driver, email, "rajeshpadmanabhuni123@gmail.com");
	}

	private void setName() {
		//filling firstName
		webUtils.sendKeys(driver, firstName, obj.getProperty("fName"));
		//filling lastName
		webUtils.sendKeys(driver, lastName, obj.getProperty("lName"));
	}

	private void clickOnPracticeForms() {
		// Clicking on practice forms
		webUtils.webElementClick(driver, practiceForm);
	}

	private void clickOnForms() {
		// clicking on forms
		webUtils.webElementClick(driver, forms);
	}

	private void clickOnInteractionTab() {
		// clicking on interactions tab
		webUtils.webElementClick(driver, interactionsTab);
		}
}
