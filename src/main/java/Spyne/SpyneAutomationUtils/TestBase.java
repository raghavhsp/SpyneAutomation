package Spyne.SpyneAutomationUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
	
	public static WebDriver  driver;
	public static  int pageLoadTimeOutInSeconds=100;
	public static  String BaseURL;
	public static int pageLoadTimeOut;
	public static Duration maxWaitTime;
	public static Duration eleWaitTime;
	
	
	@Parameters({"browserType"})
	@BeforeTest (alwaysRun=true)
	public void inititalizeWebDriver(String browser)
	{
		// Get webdriver instance
		driver=BrowserFactory.getWebDriverInstance(browser);
	
		// Set PageLoad TimeOut
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
		
		//Open Obrowser window
		driver.get(BaseURL);
		driver.manage().window().maximize();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		
		//Get Base URL
		BaseURL=Utility.getProperty("BaseURL");
		
		// Get Page Load TimeOut
		pageLoadTimeOut=Integer.valueOf(Utility.getProperty("pageLoadTimeOutInSeconds"));
		
		//GetMax MAX waitTime
		maxWaitTime= Duration.ofSeconds(Integer.valueOf(Utility.getProperty("MaxWaitTime")));
		
		//GetMax waitTime
		eleWaitTime = Duration.ofSeconds(Integer.valueOf(Utility.getProperty("ElementWaitTime")));
		
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	

}
