package SpyneAutomation.TestCases;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Spyne.SpyneAutomationUtils.FileOutputFormat;
import Spyne.SpyneAutomationUtils.LoggingUtils;
import Spyne.SpyneAutomationUtils.SeleniumUtility;
import Spyne.SpyneAutomationUtils.TestBase;
import Spyne.SpyneAutomationUtils.Utility;
import SpyneAutomation.BusinessFunctions.UpscalceFunctions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * @description : Test case to validate scale functionality of 
 * @author raghav
 * 
 */


public class TC_UploadImage extends TestBase

{
	public static SeleniumUtility seleniumUtility= new SeleniumUtility();
	public static UpscalceFunctions upscalceFunctions = new UpscalceFunctions();
	public static Logger log = LoggingUtils.getLogger(TC_UploadImage.class);
	
	// Create Fake data for sign-up
	public static Faker faker = new Faker();
	public static String emailid , name , filename;
	
	
	@BeforeClass
	@Parameters("fileName")
	public void beforeTest(String fileName)
	{
		Utility.deleteFileOfDirectory(Utility.getProperty("downloadFileFolder"));
		emailid=faker.internet().emailAddress();
		name =faker.superhero().name();
		filename=fileName;
	}
	
	@Story("Validating upscale feature in Spyne")	
	@Description("This test is to validate the upscale image feature of Spyne")
	@Severity(SeverityLevel.BLOCKER)
	@Test()
	public static void upscale_test() {
		

		// Click Upload Button
		String filePath =  System.getProperty("user.dir")+ File.separator+"Attachments"+File.separator+"Valid_Attachments"+File.separator+filename;
		
		log.info("File Sent :"+filePath);
		// Upload Image
		upscalceFunctions.uploadImage(driver, filePath);

		// Complete Get started process
		upscalceFunctions.completeGetStartedProcess(driver , emailid , name , eleWaitTime);
		
		//Process Image
		upscalceFunctions.processImage(driver, FileOutputFormat.PNG, eleWaitTime);
		
		// Download and validate file after download
		boolean flag=upscalceFunctions.valiidateFileAfterDownload(driver, FileOutputFormat.PNG, eleWaitTime);
		
		Assert.assertEquals(flag, true , "Is file downloaded after processing");;
	
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
		
	}
}
