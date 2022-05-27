package may16;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FacebookParameter {
WebDriver driver;
@Parameters({"Url"})
@BeforeTest
public void setUP(String AppUrl) {
	driver = new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.get(AppUrl);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

@Parameters({"fname","sname","email","renteremail","password","dob","bmonth","byear"})
@Test
public void createAccount(String fname,String sname,String email,String renteremail,String password,String dob,String bmonth,String byear) throws Throwable {
	driver.findElement(By.linkText("Create New Account")).click();
	WebDriverWait mywait = new WebDriverWait(driver, 10);
	mywait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("firstname")));
	driver.findElement(By.name("firstname")).sendKeys(fname);
	driver.findElement(By.name("lastname")).sendKeys(sname);
	driver.findElement(By.name("reg_email__")).sendKeys(email);
	driver.findElement(By.name("reg_email_confirmation__")).sendKeys(renteremail);
	driver.findElement(By.name("reg_passwd__")).sendKeys(password);
	
	new Select(driver.findElement(By.name("birthday_day"))).selectByVisibleText(dob);
	new Select(driver.findElement(By.name("birthday_month"))).selectByVisibleText(bmonth);
	new Select(driver.findElement(By.name("birthday_year"))).selectByVisibleText(byear);
	
	driver.findElement(By.xpath("//label[normalize-space()='Female']")).click();
	driver.findElement(By.xpath("//body/div/div/div/div/div/div/div/div/form[@method='post']/div/div/button[1]")).click();
	
	Thread.sleep(6000);
	
}

@AfterTest
public void tearDown()
{
	driver.close();
}


}
