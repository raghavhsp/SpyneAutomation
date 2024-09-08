package SpyneAutomation.PageObjects;

import org.openqa.selenium.By;

public class Image_Upscale_Upload {
	
	public static By uploadBtn = By.xpath("//div[@class='uploadBoxContent ']/input[@type='file']");
	
	public static By getStartedButton=By.xpath("//button[text()='Get Started']");
	
	public static  By continueWithGoogleBtn = By.xpath("//p[text()='Continue with Google']/parent::button");
	public static  By continueWithEmailBtn = By.xpath("//button[text()='Continue with Email']");
	
	public static By emailIDTxtBox= By.xpath("//input[@name='emailId']");
	public static By emailConfirmnNextBtn= By.xpath("//button[text()='Next']");
	
	public static By nameTxtBox= By.xpath("//input[contains(@name,'name')]");
	public static By continueBtn= By.xpath("//button[text()='Continue']");
	
	public static By imgCloseSPIcn = By.xpath("//img [@alt='close']");
	public static By imgCloseSubPopUpIcn = By.xpath("//h1[text()='Subscription Plan']/parent::div/following-sibling::div//img[@alt='close']");
	public static By freeCreditTxt = By.xpath("//p [text()='Free Credits:']/following-sibling::p");
	
	
	public static By profileIcn = By.xpath("//div[@class='relative']//img");
	
	public static By logOutBtn = By.xpath("//p[text()='Log Out']/parent::button");
	
	public static By uploadLoader = By.xpath("//p[text()='Uploading...']");
	
	public static By sampleUploadFile = By.xpath("//button[text()='Upload an image']/following-sibling::div/div/img");
}
