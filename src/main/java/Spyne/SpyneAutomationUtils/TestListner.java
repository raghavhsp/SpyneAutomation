package Spyne.SpyneAutomationUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TestListner extends TestBase implements ITestListener {
	
	private static String getTestMethodName (ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	// Attachment for allure
	@Attachment(value= "Page Screenshot" , type ="image/png")
	public byte[] saveScreenshotPNG (WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult)
	{
		try {
			Object testClass= iTestResult.getInstance();
			WebDriver driver = ((TestBase)testClass).getDriver();
			
			if(driver instanceof WebDriver)
			{
				saveScreenshotPNG(driver);
			}
		}
		catch (Exception e) {
			System.out.println("Error capturing screenshot");
		}
	}
}
