package SpyneAutomation.PageObjects;

import org.openqa.selenium.By;

import Spyne.SpyneAutomationUtils.FileOutputFormat;

public class Image_Upscale_Result {
	
	public static By processBtn = By.xpath("//label [@for='upload']/parent::div/button");
	public static By uploadBtn = By.xpath("//label [@for='upload']//img");
	
	public static By resize2XBtn = By.xpath("(//h3[text()='Resize']/parent::div/button)[1]");
	public static By resize4XBtn = By.xpath("(//h3[text()='Resize']/parent::div/button)[2]");
	
	public static By downloadBtn = By.xpath("(//button[contains(text(),'Download')])[1]");
	
	public static By loderImg = By.xpath("//div[contains(@class,'spinner')]");
	
	
	public static By getOutputTypeBtn (FileOutputFormat format) {
		return By.xpath("//p[text()='"+format+"']/parent::button");
	}
	
}
