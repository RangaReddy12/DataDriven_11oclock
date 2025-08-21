package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginpage {
WebDriver driver;
//constructor for invoking webdriver methods
public AdminLoginpage(WebDriver dr)
{
	this.driver=dr;
}
//define OR For Admin login
@FindBy(xpath = "//input[@id='username']")
WebElement user;
@FindBy(xpath = "//input[@id='password']")
WebElement Objpass;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement ObjLogin;
//method for login
public void adminLogin(String user,String pass)
{
this.user.clear();
this.user.sendKeys(user);
this.Objpass.clear();
Objpass.sendKeys(pass);
ObjLogin.click();
}

}
