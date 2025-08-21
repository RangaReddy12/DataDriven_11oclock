package util;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import commonFunctions.AdminLoginpage;
import commonFunctions.AdminLogoutPage;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeMethod
public static void setUp()throws Throwable
{
	conpro = new Properties();
	//load property file here
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AdminLoginpage login = PageFactory.initElements(driver, AdminLoginpage.class);
		//call login method from AdminLoginpage class
		login.adminLogin("admin", "master");
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AdminLoginpage login = PageFactory.initElements(driver, AdminLoginpage.class);
		//call login method from AdminLoginpage class
		login.adminLogin("admin", "master");
	}
	else
	{
		try {
			throw new IllegalArgumentException("Browser value is Not matching");	
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
@AfterMethod
public static void tearDown()
{
	AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
	logout.adminLogout();
	driver.quit();
}
}



















