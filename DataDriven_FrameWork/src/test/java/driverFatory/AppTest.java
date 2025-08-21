package driverFatory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.CustomerPage;
import commonFunctions.SupplierPage;
import util.AppUtil;
import util.ExcelFileUtil;

public class AppTest extends AppUtil{
	String fileinputpath ="./DataTables/ERPData.xlsx";
	String fileoutpath ="./DataTables/supplierResults.xlsx";
	String fileoutpath1 ="./DataTables/customerResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	@Test(enabled = true,priority = 0)
	public void verifySupplier() throws Throwable
	{
		reports = new ExtentReports("./target/ERP/supplier.html");
		logger = reports.startTest("Supplier");
		//create object for Excelfileutil class 
		ExcelFileUtil xl = new ExcelFileUtil(fileinputpath);
		int rc = xl.rowCount("supplierdata");
		Reporter.log("No of rows are::"+rc,true);
		logger.log(LogStatus.INFO, "No of rows are::"+rc);
		for(int i=1;i<=rc;i++)
		{
			String sname = xl.getCellData("supplierdata", i, 0);
			String address = xl.getCellData("supplierdata", i, 1);
			String city = xl.getCellData("supplierdata", i, 2);
			String country = xl.getCellData("supplierdata", i, 3);
			String cperson = xl.getCellData("supplierdata", i, 4);
			String pnumber = xl.getCellData("supplierdata", i, 5);
			String email = xl.getCellData("supplierdata", i, 6);
			String mnumber = xl.getCellData("supplierdata", i, 7);
			String notes = xl.getCellData("supplierdata", i, 8);
			SupplierPage sup = PageFactory.initElements(driver, SupplierPage.class);
			boolean res = sup.add_Supplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			logger.log(LogStatus.INFO, sname+"   "+address+"   "+city+"   "+country+"   "+cperson+"   "+pnumber+"    "+email+"  "+mnumber+"   "+notes);
			if(res)
			{
				xl.setCelldata("supplierdata", i, 9, "Pass", fileoutpath);
				logger.log(LogStatus.PASS, "Add supplier Success");
			}
			else
			{
				xl.setCelldata("supplierdata", i, 9, "Fail", fileoutpath);
				logger.log(LogStatus.FAIL, "Add supplier UnSuccess");
			}
			reports.endTest(logger);
			reports.flush();
		}

	}
	@Test(enabled = true,priority = 1)
	public void validateCustomer() throws Throwable
	{
		reports = new ExtentReports("./target/ERP/customer.html");
		ExcelFileUtil xl = new ExcelFileUtil(fileinputpath);
		int rc = xl.rowCount("customerdata");
		Reporter.log("No of rows in Customer Sheet  "+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger= reports.startTest("Validate Customer");
			String cname = xl.getCellData("customerdata", i, 0);
			String address = xl.getCellData("customerdata", i, 1);
			String city = xl.getCellData("customerdata", i, 2);
			String country = xl.getCellData("customerdata", i, 3);
			String cperson = xl.getCellData("customerdata", i, 4);
			String pnumber = xl.getCellData("customerdata", i, 5);
			String email = xl.getCellData("customerdata", i, 6);
			String mnumber = xl.getCellData("customerdata", i, 7);
			String notes = xl.getCellData("customerdata", i, 8);
			logger.log(LogStatus.INFO, cname+"-----"+address+"----"+city+"----"+country+"-----"+cperson+"----"+pnumber+"-----"+email+"-----"+mnumber+"----"+notes);
			CustomerPage cus =PageFactory.initElements(driver, CustomerPage.class);
			boolean res = cus.add_customer(cname, address, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				xl.setCelldata("customerdata", i, 9, "Pass", fileoutpath1);
				logger.log(LogStatus.PASS, "Cusotomer Added Success");
			}
			else
			{
				xl.setCelldata("customerdata", i, 9, "Fail", fileoutpath1);
				logger.log(LogStatus.FAIL, "Cusotomer Added UnSuccess");
			}
			reports.endTest(logger);
			reports.flush();

		}

	}
}





















