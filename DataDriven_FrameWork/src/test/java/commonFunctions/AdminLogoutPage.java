package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {
@FindBy(xpath = "(//a[starts-with(text(),' Logout')])[2]")
WebElement click_logout;
public void adminLogout()
{
	click_logout.click();
}
}
