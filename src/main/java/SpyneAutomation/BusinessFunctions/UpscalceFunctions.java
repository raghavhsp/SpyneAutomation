package SpyneAutomation.BusinessFunctions;

import java.io.File;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import Spyne.SpyneAutomationUtils.FileOutputFormat;
import Spyne.SpyneAutomationUtils.LoggingUtils;
import Spyne.SpyneAutomationUtils.SeleniumUtility;
import Spyne.SpyneAutomationUtils.Utility;
import SpyneAutomation.PageObjects.Image_Upscale;
import SpyneAutomation.PageObjects.Image_Upscale_Result;
import SpyneAutomation.PageObjects.Image_Upscale_Upload;
import io.qameta.allure.Step;

public class UpscalceFunctions {
	public SeleniumUtility seleniumUtility = new SeleniumUtility();
	private static Logger log = LoggingUtils.getLogger(UpscalceFunctions.class);
	
	
	/**
	 * @description upload file for scaling
	 * @author raghav
	 * @param driver
	 * @param filePath
	 * @return
	 */
	
	@Step("Uploading image")
	public boolean uploadImage(WebDriver driver , String filePath)
	{
		// Upload Flag
		boolean flag=false;
		// Get Wait Duration
		int eleWaitTime=Integer.valueOf(Utility.getProperty("ElementWaitTime"));
		Duration wait = Duration.ofSeconds(eleWaitTime);
		
		File file = new File(filePath);
		if(file.exists() && !file.isDirectory()) 
		{ 
			// Wait for complete loading of upload page and sample uploads to appear
			seleniumUtility.waitForElementToBeVisible(driver, Image_Upscale_Upload.sampleUploadFile, wait);
			
			// Sent file upload
			driver.findElement(Image_Upscale.uploadImageBtn).sendKeys(filePath);
			
			// Check for loading icon to appear
			if (seleniumUtility.waitForElementToBeVisible(driver, Image_Upscale_Upload.uploadLoader, wait))
			{
				log.info("Uploading started");

				// Wait for loading to complete
				if (seleniumUtility.waitForElementToBeInvisible(driver, Image_Upscale_Upload.uploadLoader, Duration.ofMinutes(2))) 
				{
					log.info("Uploading completed");
					flag=true;
					 
				}

			}
			
		}
		else {
			log.error("File upload failed for file with path :"+filePath);
			flag=false;
		}
		
		return flag;
	}

	
	/**
	 * @decription complete getstarted with given data
	 * @param driver
	 * @param email
	 * @param name
	 * @param wait
	 */
	
	@Step("Complete the get started process")
	public boolean completeGetStartedProcess(WebDriver driver , String email , String name , Duration wait)

	{
		// Click Get started
				seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.getStartedButton, wait);
				
				//Click continue with email
				seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.continueWithEmailBtn, wait);
			
				// Add email address
				seleniumUtility.enterText(driver, Image_Upscale_Upload.emailIDTxtBox, email, wait);
				
				// click on confirm email button
				seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.emailConfirmnNextBtn, wait);
				
				// Enter Name of User	
				seleniumUtility.enterText(driver, Image_Upscale_Upload.nameTxtBox, name, wait);

				// Click continue button
				seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.continueBtn, wait);
				
				
				// Click Close 
				seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.imgCloseSPIcn, Duration.ofMinutes(1));

				// Click Close on Subscription Pop-up
			return	seleniumUtility.clickWebElement(driver, Image_Upscale_Upload.imgCloseSubPopUpIcn, Duration.ofMinutes(1));
		
	}
	
	
	@Step("Process image")
	public boolean processImage(WebDriver driver , FileOutputFormat outputFormat, Duration wait)
	{
		boolean flag=false;
		// Click Resize to 2X button on Subscription Pop-up	
				seleniumUtility.clickWebElement(driver, Image_Upscale_Result.resize2XBtn, wait);
				
				// Select Output format	
				seleniumUtility.clickWebElement(driver, Image_Upscale_Result.getOutputTypeBtn(outputFormat), wait);	
				
				// Click on Process button
				seleniumUtility.clickWebElement(driver, Image_Upscale_Result.processBtn, wait);	
				
				// Check for loading icon to appear
				if (seleniumUtility.waitForElementToBeVisible(driver, Image_Upscale_Result.loderImg, wait))
				{
					log.info("Processing started");

					// Wait for loading to complete
					if (seleniumUtility.waitForElementToBeInvisible(driver, Image_Upscale_Result.loderImg, Duration.ofMinutes(2))) 
					{
						log.info("Processing completed");
						flag=true;
						 
					}

				}
				
				return flag;
				
	}
	
	@Step("Validate file after downloading the processed file")
	public boolean valiidateFileAfterDownload(WebDriver driver , FileOutputFormat  outputFormat , Duration  wait)
	
	{
		
		
		// Click on download button
		seleniumUtility.clickWebElement(driver, Image_Upscale_Result.downloadBtn, wait);	
		
		// Check for downloaded file

		File[] files =Utility.getFiles(Utility.getProperty("downloadFileFolder"));
		
		
		// Poling for file download check
		for (int i=0 ; i<60 ; i++)
		{
		if (files.length==0)
			{
			Utility.sleep(5);
			log.info("Futher Waiting for download to be complted");
			files =Utility.getFiles(Utility.getProperty("downloadFileFolder"));
			}
		else
			break;
		}
		
		//Get File again
		files =Utility.getFiles(Utility.getProperty("downloadFileFolder"));
		
		if (files.length==1) {
			
			String fileName=files[0].getName().toLowerCase();
			String fileExtension = outputFormat.toString().toLowerCase();
			if (fileName.contains(fileExtension))
			{
				log.info("File downloaded successfully :"+files[0].getName());
				return true;
			}
			
			else
				return false;
		}
		
		else
		return false;
	}
}
