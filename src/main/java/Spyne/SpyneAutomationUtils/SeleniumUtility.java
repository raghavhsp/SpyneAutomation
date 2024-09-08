package Spyne.SpyneAutomationUtils;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtility {
	private static Logger log = LoggingUtils.getLogger(SeleniumUtility.class);

	/**
	 * @description Click on web-element when visible
	 * @author raghav
	 * @param driver - Web-driver instance
	 * @param Locator- By locator of web-element
	 * @param waitTime - Max time
	 * @return
	 */
	public boolean clickWebElement(WebDriver driver, By Locator, Duration waitTime) {
		boolean isElementClicked = false;
		boolean flagVisible = false;
		boolean isElementLocated = false;
		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
			wait.until(ExpectedConditions.elementToBeClickable(Locator));
			flagVisible = true;
		} catch (Exception e) {
			log.error("Element is not visible after waiting for :" + waitTime);
		}

		if (flagVisible) {
			try {
				element = driver.findElement(Locator);
				isElementLocated = true;
			} catch (Exception e) {
				log.info("Error locating element for click :" + element + "Error :" + e);
			}
		}

		if (flagVisible && isElementLocated) {
			String color = Utility.getProperty("ClikHighlighColor");
			highlightElement(driver, color, element);
			try {
			// Click webelement
			element.click();
			isElementClicked=true;
			}
			catch (Exception e) {
				log.error("Error clicking element:" + element + "Error :" + e);
			}
		}
		return isElementClicked;

	}
	
	
	/**
	 * @description Enter text to text box after clear
	 * @author raghav
	 * @param driver
	 * @param Locator
	 * @param string
	 * @param waitTime
	 * @return
	 */
	
	public boolean enterText(WebDriver driver, By Locator, String string, Duration waitTime) {
		boolean isTextEntered= false;
		boolean flagVisible = false;
		boolean isElementLocated = false;
		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
			flagVisible = true;
		} catch (Exception e) {
			log.error("Element is not visible after waiting for :" + waitTime);
		}

		if (flagVisible) {
			try {
				element = driver.findElement(Locator);
				isElementLocated = true;
			} catch (Exception e) {
				log.error("Error locating element for click :" + element + "Error :" + e);
			}
		}

		if (flagVisible && isElementLocated) {
			String color = Utility.getProperty("textBoxHighLightColor");
			highlightElement(driver, color, element);
			
			try {
			// Click webelement
			element.clear();
			element.sendKeys(string);
			isTextEntered=true;
			}
			catch (Exception e) {
				log.error("Error enterting text to element:" + element + "Error :" + e);
			}
		}
		return isTextEntered;

	}
	
	/**
	 * @description Highlight weblement in given color or remove highl;ight
	 * @param driver
	 * @param color
	 * @param element
	 * @return
	 */

	public boolean highlightElement(WebDriver driver, String color, WebElement element) {

		boolean flag = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			color = color.toLowerCase();
			if ((color != null && !color.isBlank() && !color.isEmpty()) && ("red".equals(color) || "blue".equals(color)
					|| "green".equals(color) || "yellow".equals(color) || "orange".equals(color)
					|| "voilet".equals(color) || "pink".equals(color) || "cyan".equals(color) || "indigo".equals(color)
					|| "purple".equals(color) || "teal".equals(color) || "tan".equals(color) || "silver".equals(color)
					|| "coral".equals(color) || "tan".equals(color) || "lavender".equals(color) || "olive".equals(color)
					|| "navy blue".equals(color))) {
				js.executeScript("arguments[0].style.border='solid 4px " + color + "'", element);
				Utility.sleep(1 / 10);
				flag = true;
			}

			//Remove highlight
			else {
				js.executeScript("arguments[0].style.border=''", element);
				Utility.sleep(1 / 10);
				flag = true;
			}
		} catch (Exception e) {
			log.warn("Failed to huighlight element");
		}
		return flag;
	}
	
	/**
	 * @description : Wait for element to be visible
	 * @author raghav
	 * @param driver
	 * @param Locator
	 * @param waitTime
	 * @return
	 */
	
	public boolean waitForElementToBeVisible(WebDriver driver, By Locator, Duration waitTime)
	{
		boolean flag=false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
			flag = true;
		} catch (Exception e) {
			log.error("Element is not visible after waiting for :" + waitTime);
		}
		
		return flag;
	}
	
	
	/**
	 * @description : Wait for element to be invisible
	 * @author raghav
	 * @param driver
	 * @param Locator
	 * @param waitTime
	 * @return
	 */
	
	public boolean waitForElementToBeInvisible(WebDriver driver, By Locator, Duration waitTime)
	{
		boolean flag=false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
			flag = true;
		} catch (Exception e) {
			log.error("Element is not invisible after waiting for :" + waitTime);
		}
		
		return flag;
	}
}
