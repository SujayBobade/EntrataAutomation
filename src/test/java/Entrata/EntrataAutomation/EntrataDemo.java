package Entrata.EntrataAutomation;

import java.awt.Window;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EntrataDemo {
	
	public static void main(String[] args) {
		
		
		//webdriver initialization
		WebDriverManager.chromedriver().setup();
		WebDriver Driver = new ChromeDriver();
		//maximize window
		Driver.manage().window().maximize();
		//wait for 10sec to load the All elements in web page
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		/*
		 * Test 1: Validate the entrata Title
		 */
		//Go to entrata site
		Driver.get("https://www.entrata.com/");
		
		//Accepts the cookies.
		Driver.findElement(By.cssSelector("#rcc-decline-button")).click();
		
		//validate the title of entrata web page
		String ActualTitle= Driver.getTitle();
		String Expectedtitle="Property Management Software | Entrata";
		Assert.assertEquals(ActualTitle, Expectedtitle);
		
		//explicit wait to wait for a particular condition.
		WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
		
		
		/*
		 * Test2 : Validate the contact form in Residental Portal
		 */
		
		//Click on the Sign In button
		Driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		
		//wait untill the landing logo of entrata will come into picture.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".landing-logo")));
		
		//click on the resident link
		Driver.findElement(By.cssSelector(".dark-cta-link")).click();
		
		//click on the website button
		Driver.findElement(By.cssSelector(".website-button")).click();
		
		//click on contact us link
		Driver.findElement(By.cssSelector(".four-content a[href='#app-contact']")).click();
		
		//wait untill the contact header will come into picture
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".contact-header")));
		
		//fill the form 
		Driver.findElement(By.id("name")).sendKeys("Sujay");
		Driver.findElement(By.id("email")).sendKeys("sujaybobade6131@gmail.com");
		Driver.findElement(By.id("property_name")).sendKeys("Entrata");
		Driver.findElement(By.id("property_url")).sendKeys("https://www.residentportal.com/");
		
		//select the dropdown using select class. here have selected payments options from Dropdown
		WebElement ChooseCategory= Driver.findElement(By.cssSelector(".contact-drop"));
		Select selectOption= new Select(ChooseCategory);
		selectOption.selectByVisibleText("Payments");
		Driver.findElement(By.id("message")).sendKeys("Thanks for Review");
		
		//Check whether the submit button is working or not by using Assertions.
		Boolean Status=	Driver.findElement(By.id("contact-submit")).isEnabled();
		//validating the STatus of submit button
		Assert.assertTrue(Status);
		
		/*
		 * Test3: Validate all the links in Base Camp footer section working or not.
		 */
		//here we're navigating to entrata website again to test the 3rd Test
		Driver.navigate().to("https://www.entrata.com/");
		
		//click on the base camp tab.
		Driver.findElement(By.cssSelector("a[href*='basecamp']")).click();
		
		//here we're switching the windows from parent to child
		Set<String> AllWindowHandles=	Driver.getWindowHandles();
		Iterator<String> WindowHandles= AllWindowHandles.iterator();
		String ParentWindow= WindowHandles.next();
		String childWindow = WindowHandles.next();
		
		//switching from parent to child.
		Driver.switchTo().window(childWindow);
		//validating the title of base camp web page.
		Assert.assertEquals(Driver.getTitle(), "Base Camp 2024 | Entrata");
		

	
		/*
		 * Test4: Validate Hotel Website under Base Camp.
		 */
		
		//click on the 3 dots from right top. which will gives options
		Driver.findElement(By.cssSelector(".w-icon-nav-menu")).click();
		
		//click on the Travel Options
		Driver.findElement(By.cssSelector("a[href*='travel']")).click();
		
		//click on the Travel Website link
		Driver.findElement(By.cssSelector(".link")).click();
		
		// extracting the resort name text from web page and validating it 
		String Resortname= Driver.findElement(By.cssSelector(".name")).getText();
		String ActualResortName="ARIA RESORT & CASINO";
		Assert.assertEquals(Resortname, ActualResortName);
		
		//once validation is done. Going back to the Base Camp webpage for testing the next Test case
		Driver.navigate().back();
		
		
		//limiting the webdriver scope here to check the Links present in the footer section
		WebElement FooterDriver= Driver.findElement(By.cssSelector(".nav-holder"));
		
		//getting count of links present in the footer section
		int FooterLinks=	FooterDriver.findElements(By.tagName("a")).size();
		//iterating through the  count of FooterLinks
		for(int i=0; i<FooterLinks; i++)
		{
			
			//here we're opening the links in new tabs
			String Click=	Keys.chord(Keys.CONTROL, Keys.ENTER);
			FooterDriver.findElements(By.tagName("a")).get(i).sendKeys(Click);
		}
		
		//handling the child & parent window
		Set<String> FooterWindow=Driver.getWindowHandles();
		Iterator<String> WindowID= FooterWindow.iterator();
		
		//by using while loop, checking if the next window ID is present or not
		while(WindowID.hasNext())
		{
			//switching from parent to child.
			Driver.switchTo().window(WindowID.next());
		}
		//here we're switching from Child to Parent
		Driver.switchTo().window(ParentWindow);
		//Validating the Actual and expected Title.
		Assert.assertEquals(ActualTitle, Expectedtitle);
		
		
		/*
		 *Test5:Validate Student Option under Solutions Tab is working or not.
		 * 
		 */
		
		//using actions class to mouse over to element.
		Actions action = new Actions(Driver);
		
		//Move to given locator element 
		action.moveToElement(Driver.findElement(By.xpath("(//div[@class='main-nav-link'])[2]"))).build().perform();
		//limit the web driver to just work inside the web element only
		WebElement HeaderDriver= Driver.findElement(By.cssSelector(".header-nav-item:nth-child(2)"));
			
		//It returns statue of student whether it is enabled or not.
		Boolean SolutionsStat=	HeaderDriver.findElement(By.cssSelector("a[href*='student']")).isEnabled();
		
		//validating the student link
		Assert.assertTrue(SolutionsStat);
		HeaderDriver.findElement(By.cssSelector("a[href*='student']")).click();
		
		//close all the open browser.
		Driver.quit();
		
		
	}

}
