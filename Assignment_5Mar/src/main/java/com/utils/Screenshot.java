package com.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public WebDriver driver;

	public Screenshot(WebDriver driver) {
		this.driver=driver;
	}

	public void clickScreenshot(String timeStamp,String fileName) throws IOException {
		
		//Taking screenshot and storing in a folder created with timestamp (ddMMyyyHHmmss)  as nomenclature
		TakesScreenshot scrShot= ((TakesScreenshot) driver);
		File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(timeStamp+"/"+fileName);
		FileUtils.copyFile(srcFile, destFile);
	}
	
}
