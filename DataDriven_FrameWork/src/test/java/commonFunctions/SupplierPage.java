package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierPage {
WebDriver driver ;
public SupplierPage(WebDriver driver)
{
	this.driver=driver;
}
//Define OR for Supplier Module
@FindBy(xpath = "(//a[starts-with(text(),'Suppliers')])[2]")
WebElement supplier_Link;
@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement Add_ClickIcon;
@FindBy(xpath = "//input[@id='x_Supplier_Number']")
WebElement Supplier_Number;
@FindBy(xpath = "//input[@id='x_Supplier_Name']")
WebElement supplier_Name;
@FindBy(xpath = "//textarea[@id='x_Address']")
WebElement Supplier_Address;
@FindBy(xpath = "//input[@id='x_City']")
WebElement Supplier_City;
@FindBy(xpath = "//input[@id='x_Country']")
WebElement Supplier_Country;
@FindBy(xpath = "//input[@id='x_Contact_Person']")
WebElement Supplier_ContactPerson;
@FindBy(xpath = "//input[@id='x_Phone_Number']")
WebElement Supplier_phoneNum;
@FindBy(xpath = "//input[@id='x__Email']")
WebElement Supplier_Email;
@FindBy(xpath = "//input[@id='x_Mobile_Number']")
WebElement Supplier_MobileNum;
@FindBy(xpath = "//textarea[@id='x_Notes']")
WebElement Supplier_Notes;
@FindBy(id = "btnAction")
WebElement Click_AddBtn;
@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement Clik_ConfirmOk;
@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
WebElement click_Alertok;
@FindBy(xpath = "//span[@data-caption='Search']")
WebElement click_SearchPanel;
@FindBy(xpath = "//input[@id='psearch']")
WebElement search_textbox;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement search_Button;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
WebElement Webtable;
//method for add suppier 
public boolean add_Supplier(String sname,String Address,String city,String country,String cperson,
		String pnumber,String email,String mnunber,String notes) throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(supplier_Link).click().perform();
	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//wait until add + icon is clickable
	mywait.until(ExpectedConditions.elementToBeClickable(Add_ClickIcon));
	ac.moveToElement(Add_ClickIcon).click().perform();
	//wait until supplier number texbox visible
	mywait.until(ExpectedConditions.visibilityOf(Supplier_Number));
	//capture supplier number into one varibale
	String Exp_data = Supplier_Number.getAttribute("value");
	this.supplier_Name.sendKeys(sname);
	this.Supplier_Address.sendKeys(Address);
	this.Supplier_City.sendKeys(city);
	this.Supplier_Country.sendKeys(country);
	this.Supplier_ContactPerson.sendKeys(cperson);
	this.Supplier_phoneNum.sendKeys(pnumber);
	this.Supplier_Email.sendKeys(email);
	this.Supplier_MobileNum.sendKeys(mnunber);
	this.Supplier_Notes.sendKeys(notes);
	this.Click_AddBtn.sendKeys(Keys.ENTER);
	//wait until confirm ok is clickable
	Thread.sleep(2000);
	this.Clik_ConfirmOk.click();
	//wait until ok alert is clickable
	Thread.sleep(2000);
	this.click_Alertok.click();
	mywait.until(ExpectedConditions.visibilityOf(click_SearchPanel));
	if(!search_textbox.isDisplayed())
		//click search panel when search textbox is not displayed
		this.click_SearchPanel.click();
	Thread.sleep(2000);
	this.search_textbox.clear();
	this.search_textbox.sendKeys(Exp_data);
	this.search_Button.click();
	Thread.sleep(2000);
	//capture suppliernumber cell data
	String Act_Data =Webtable.getText();
	Reporter.log(Exp_data+"    "+Act_Data,true);
	if(Act_Data.equals(Exp_data))
	{
		
		return true;
	}
	else
	{
		return false;
	}
	
}
}
