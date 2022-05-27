package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for login
	public static boolean verifyLogin(String username,String password) {
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).click();
		String expected ="adminflow";
		String actual = driver.getCurrentUrl();
		Reporter.log(actual,true);
		if(actual.toLowerCase().contains(expected.toLowerCase())) {
			Reporter.log("Login Success",true);
			return true;
		}else {
			Reporter.log("Login Failed",true);
			return false;
		}
			
	}
	// method to click Branches
	public static void clickBranches() throws Throwable {
		   
			driver.findElement(By.xpath(config.getProperty("ObjClickBranches"))).click();
			Thread.sleep(2000);
	}
	// method to branch creation
	public static boolean createBranch(String branchName,String Addr1,String Addr2,String Addr3,String Area,
			String zipcode,String country,String state,String city) throws Throwable {
		
			driver.findElement(By.xpath(config.getProperty("ObjNewBranckBtn"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchName);
			driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Addr1);
			driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Addr2);
			driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Addr3);
			driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
			driver.findElement(By.xpath(config.getProperty("ObjZip"))).sendKeys(zipcode);
			new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(country);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(state);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(city);
			Thread.sleep(2000);
			driver.findElement(By.xpath(config.getProperty("ObjSubmitnewBranchBtn"))).click();
		   //capture alert message
			String expecteBranchAlert= driver.switchTo().alert().getText();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			String actualBranchAlert = "new branch with";
			
			if(expecteBranchAlert.toLowerCase().contains(actualBranchAlert.toLowerCase()))
			{
				Reporter.log("Baranch creation susccess",true);
				return true;
			}else {
				Reporter.log("Baranch creation failed",true);
				return false;
			}
	}
	// method for branch updation
	public static boolean verifyBranchUpdate(String branch,String address,String zip) throws Throwable {
		//click update 
	Reporter.log("Inside verify Branch",true);
		Reporter.log(driver.getCurrentUrl(),true);
		driver.findElement(By.xpath(config.getProperty("ObjEditBtn"))).click();
		Thread.sleep(2000);
		WebElement ObjBranchName = driver.findElement(By.xpath(config.getProperty("ObjBranch")));
		ObjBranchName.clear();
		ObjBranchName.sendKeys(branch);
		Thread.sleep(2000);
		
		WebElement Objaddress = driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		Objaddress.clear();
		Objaddress.sendKeys(address);
		Thread.sleep(2000);
		
		WebElement Objzipcode = driver.findElement(By.xpath(config.getProperty("ObjZipCode")));
		Objzipcode.clear();
		Objzipcode.sendKeys(zip);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(config.getProperty("ObjUpdateBtn"))).click();
		
		String expectedUpdateAlert =driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
        Thread.sleep(3000);
        
        String updateAlert="Branch updated";
        if(expectedUpdateAlert.toLowerCase().contains((updateAlert).toLowerCase())) {
        	
        	Reporter.log("Update success",true);
        	return true;
         }else {
         	Reporter.log("Update failed",true);
         	return false;
         }
	}
	
	// method for logout
	
	public static boolean verifyLogout() throws Throwable {
		driver.findElement(By.xpath(config.getProperty("ObjLogoutBtn"))).click();
		Thread.sleep(7000);
		if(driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).isDisplayed())
		{
			Reporter.log("Logout Sucess",true);
			return true;
		}else {
			Reporter.log("Logout failed",true);
			return false;
		}
	}	
}
