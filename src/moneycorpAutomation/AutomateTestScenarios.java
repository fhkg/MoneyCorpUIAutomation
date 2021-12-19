package moneycorpAutomation;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomateTestScenarios {
	
	static WebDriver driver = null;
	
	public static void main(String args[]) throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver", ApplicationProp.chromePath); // path for the chromeDriver
		driver = new ChromeDriver();
		String url = "https://www.moneycorp.com/en-gb/";
		driver.get(url);  // to getting the url
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.findElement(By.xpath("//img[@alt = 'English']")).click();
		Thread.sleep(5000);
		
		WebElement lang = driver.findElement(By.xpath("//a[@aria-label = 'USA English']"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", lang);
		lang.click();
		System.out.println("USA English language selected");
		Thread.sleep(7000);
		
		WebElement findOutmoreBtn = driver.findElement(By.xpath("(//a[@aria-label = 'Find out more '])[1]"));
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", findOutmoreBtn);
		findOutmoreBtn.click();
		System.out.println("FindOutMore button clicked");
		Thread.sleep(5000);
		
		// validate we arrived on the "Foreign Exchange Solution" page
		String ExpectedCurrentPageURL = driver.getCurrentUrl();
		String ActualCurrentPageURL = "https://www.moneycorp.com/en-us/business/foreign-exchange-solutions/";
		if(ActualCurrentPageURL.equals(ExpectedCurrentPageURL))
		{
			System.out.println("Foreign Exchange Solutions page displayed successfully");
		}
		else
		{
			System.out.println("Invalid page");
		}
		Thread.sleep(3000);
		
		//click on search icon
		driver.findElement(By.xpath("//button[@class = 'navigation-search icon']")).click();
		WebElement ele = driver.findElement(By.xpath("//input[@id = 'nav_search']"));
		Thread.sleep(1000);
		ele.sendKeys("international payments");
		ele.sendKeys(Keys.ENTER);
		System.out.println("Word international payments searched");
		Thread.sleep(3000);
		
		//validate valid page displayed
		String ExpectedCurrentPageUrl = driver.getCurrentUrl();
		String ActualCurrentPageUrl = "https://www.moneycorp.com/en-us/search/?q=international+payments";
		if(ActualCurrentPageUrl.equals(ExpectedCurrentPageUrl))
		{
			System.out.println("Internal payments page displayed successfully");
		}
		else
		{
			System.out.println("Invalid page");
		}
		
		Thread.sleep(3000);
		//Validate that each article in the list displays a link that starts with
		List<WebElement> links = driver.findElements(By.xpath("//a[@class='title']"));
		System.out.println("total articls size: "+links.size());
		Iterator<WebElement> itr = links.iterator();
		
			while(itr.hasNext())
			{
				url = itr.next().getAttribute("href");
			}
		
			if(url.startsWith("https://www.moneycorp.com/en-us/"))
			{
				System.out.println("Each article starts with the same link");
			}
			else
			{
				System.out.println("Invalid link");
			}
		}
}
