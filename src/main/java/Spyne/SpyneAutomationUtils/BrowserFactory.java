package Spyne.SpyneAutomationUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserFactory {
	
	private static Logger log = LoggingUtils.getLogger(BrowserFactory.class);
	
	public static WebDriver getWebDriverInstance(String browser)
	{
		WebDriver driver;
		
		Map<String , Object> perfs = new HashMap<String , Object>();
		
		perfs.put("download.prompt_for_download", false);
		perfs.put("credentials_enable_service", false);
		perfs.put("profile.password_manager_enabled", false);
		perfs.put("download.default_directory", System.getProperty("user.dir")+File.separator+"Downloaded_Files");
				
		 if (browser.equalsIgnoreCase("Firefox")) 
		{

			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			driver=  new FirefoxDriver();
		}
		
		else if (browser.equalsIgnoreCase("Edge")) 
		{

			WebDriverManager.edgedriver().clearDriverCache().setup();
			driver=  new EdgeDriver();
		}
		
		else if (browser.equalsIgnoreCase("Safari")) 
		{

			WebDriverManager.safaridriver().clearDriverCache().setup();
			driver= new SafariDriver();
		}
		
		 
		// Default browser Chrome
		else 
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", perfs);
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver(options);
		}
		 
		 // Delete all existing cookies
		 driver.manage().deleteAllCookies();
		
		 //Set page load timeout
		 driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(Utility.getProperty("MaxWaitTime")), TimeUnit.SECONDS);
			
		 return driver;
		
	}
	
	public static void openWebPage(WebDriver driver , String url)
	{
		if (driver!=null)
		{
			driver.get(url);
			driver.manage().window().maximize();
		}
	}

}
