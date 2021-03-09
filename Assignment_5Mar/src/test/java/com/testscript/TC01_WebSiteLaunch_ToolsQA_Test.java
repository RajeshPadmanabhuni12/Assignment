package com.testscript;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageObjects.InteractionsTab;
import com.pageObjects.WebTables;
import com.utils.WebDriverUtils;


public class TC01_WebSiteLaunch_ToolsQA_Test {

	private WebDriver driver;
	Properties obj;
	FileInputStream objfile;
	String timeStamp;

	File testReports;
	File screenshots;
	File timeStamp1;
	private String timeStampPath;
	private String screenshotsPath;

	InteractionsTab interactionsTab;
	WebTables webtables;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		String path="";

		// Making folder with name as testReports
		testReports=new File("testReports");
		if(!testReports.exists()) {


			testReports.mkdir();
		}
		path=testReports.getAbsolutePath();

		// Making folder with name as screenshots in testReports folder
		screenshots=new File(path+"/screenshots");
		if(testReports.exists() && (!screenshots.exists()))
		{
			screenshots.mkdir();
		}
		screenshotsPath=screenshots.getAbsolutePath();

	
	}

	@BeforeTest
	@Parameters("browser")
	public void configure(String browser) throws Exception {

		if(testReports.exists() && screenshots.exists()) {

			// Time stamp creation
			timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

			// Making folder with timestamp (ddMMyyyHHmmss) as nomenclature.
			timeStamp1=new File(screenshotsPath+"/"+timeStamp);
			timeStamp1.mkdirs();
			timeStampPath=timeStamp1.getAbsolutePath();
		}


		WebDriverUtils setup=new WebDriverUtils();
		driver=setup.setup(browser);

		obj=new Properties();
		objfile=new FileInputStream(System.getProperty("user.dir")+"\\application.properties");
		obj.load(objfile);
		
		
		interactionsTab=new InteractionsTab(driver,obj);

		webtables=new WebTables(driver,obj);
		
	}

	public void launchUrl() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		driver.get(obj.getProperty("baseurl"));
		driver.manage().window().maximize();

	}

	@Test(priority=0)
	public void TestInteractionsTab() throws AWTException, IOException {

		launchUrl();
		String actualTitle=driver.getTitle();
		String expectedTitle="ToolsQA";
		Assert.assertEquals(expectedTitle, actualTitle);
		
		//Submitting form & Assertion to check form submitted or not
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(interactionsTab.testFlow(timeStampPath),"Form submitted successfully");

		launchUrl();
		//Assertion to check value updated or not
		sa.assertEquals(obj.getProperty("updatedEmail"),webtables.testFlow(timeStampPath),"Email is not updated");
		
		sa.assertAll();
	}


	@AfterTest
	public void teardown() throws IOException {
		objfile.close();
		driver.close();
	}
}