package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.nashorn.internal.runtime.options.Options;

public class HotelAdactin {

	static WebDriver driver;
	//launching the url
	@Test
	public static void testA() throws InterruptedException {
	WebDriverManager.chromedriver().setup();
    ChromeOptions options=new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--disable notifications");
    DesiredCapabilities cp=new DesiredCapabilities();
    cp.setCapability(ChromeOptions.CAPABILITY, options);
    options.merge(cp);
	
	 driver=new ChromeDriver(options);
	 
	driver.get("https://adactinhotelapp.com/");
	Thread.sleep(1000);
	

	}
	

	
	//login without the registered account
	@Test(priority=8)
	public void testLogin() throws InterruptedException  {
		 driver.findElement(By.id("username")).sendKeys("subashashwin38@gmail.com");
		 Thread.sleep(1000);
		
		 driver.findElement(By.id("password")).sendKeys("123456789");
		 Thread.sleep(3000);
		 
		 driver.findElement(By.id("login")).click();
		 Thread.sleep(1000);
		 
		 WebElement text = driver.findElement(By.xpath("//div[ @class=\"auth_error\"]"));
		 String tt = text.getText();
		 String expectedtitle="Invalid Login details or Your Password might have expired. Click here to reset your password";
		 String actuatitle=tt;
		 Assert.assertEquals(actuatitle, expectedtitle);
		 
	     System.out.println(tt);


}
	// creating account
	@Test(enabled=false)
	public void testCreateAccount() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='New User Register Here']")).click();
		driver.findElement(By.id("username")).sendKeys("stevesubash2");
		driver.findElement(By.id("password")).sendKeys("123456789");
		driver.findElement(By.id("re_password")).sendKeys("123456789");
		driver.findElement(By.id("full_name")).sendKeys("subash");
		driver.findElement(By.id("email_add")).sendKeys("subashashwin38@gmail.com");
		String si = JOptionPane.showInputDialog("Enter the Captcha:");
		WebElement captcha = driver.findElement(By.id("captcha-form"));
		captcha.sendKeys(si);
		driver.findElement(By.id("tnc_box")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("Submit")).click();
		WebElement verify = driver.findElement(By.xpath("//td[@class='reg_success']"));
		String tv = verify.getText();
		System.out.println(tv);
	}
	
   @Test(enabled=false)
	public void testLogin2() {
		driver.findElement(By.xpath("//a[text()='Click here to login']")).click();
		
	}
		
	//login with the above created account
	@Test(priority=11)
	public void testUsername() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("stevesubash2");
		System.out.println("Accepted the username:"+"stevesubash2");

	   driver.findElement(By.id("password")).sendKeys("123456789");
       System.out.println("Accepted the password:"+"123456789");
       
       Thread.sleep(3500);
	   driver.findElement(By.id("login")).click();
}

  //selecting the hotels 
   @Test(priority=12)
    public void testRegister() {
	WebElement loc = driver.findElement(By.id("location"));
	Select location=new Select(loc);
	location.selectByIndex(1);
	String l = location.getOptions().get(1).getText();
	
	WebElement ht = driver.findElement(By.id("hotels"));
	Select hotel=new Select(ht);
	hotel.selectByIndex(2);
	String h = hotel.getOptions().get(2).getText();
	
	WebElement rt = driver.findElement(By.id("room_type"));
	Select room=new Select(rt);
	room.selectByIndex(2);
	String rm = room.getOptions().get(2).getText();
	
	WebElement rn = driver.findElement(By.id("room_nos"));
	Select roon=new Select(rn);
    roon.selectByIndex(1);
    String r = roon.getOptions().get(1).getText();
    
    WebElement ar = driver.findElement(By.id("adult_room"));
    Select adult =new Select(ar);
    adult.selectByIndex(1);
    String a = adult.getOptions().get(1).getText();
   
    WebElement cr = driver.findElement(By.id("child_room"));
	Select child=new Select(cr);
	child.selectByIndex(0);	
	String c = child.getOptions().get(0).getText();
	
	driver.findElement(By.id("Submit")).click();
	
	        String expectedvalue="Sydney";
	        String actualvalue=l;
	        Assert.assertEquals(actualvalue, expectedvalue);
	
			String expectedvalue1="Hotel Sunshine";
			String actualvalue1=h;
			Assert.assertEquals(actualvalue1, expectedvalue1);
			
			String expectedvalue2="Double";
			String actualvalue2=rm;
			Assert.assertEquals(actualvalue2, expectedvalue2);
			
			String expectedvalue3="1 - One";
			String actualvalue3=r;
			Assert.assertEquals(actualvalue3, expectedvalue3);
			
			String expectedvalue4="1 - One";
			String actualvalue4=a;
			Assert.assertEquals(actualvalue4, expectedvalue4);
			
			String expectedvalue5="0 - None";
			String actualvalue5=c;
			Assert.assertEquals(actualvalue5, expectedvalue5);
	
	Map<String, String> ex=new LinkedHashMap<String, String>();
	ex.put("location",l);
	ex.put("hotel",h);
	ex.put("Room type",rm);
	ex.put("room",r);
	ex.put("adult",a);
	ex.put("child",c);
	
	Set<Entry<String, String>> es = ex.entrySet();
	System.out.println("    ");
	for (Entry<String, String> entry : es) {
		System.out.println(entry);
		
	}
	

}

   //verifying the selected hotel and location
	@Test(priority=13)
	public void selectHotel() {
		
    driver.findElement(By.id("radiobutton_0")).click();
	driver.findElement(By.id("continue")).click();
	
	}
	
	
	//entering the details and booking
	@Test(priority=14)
	public void details() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("first_name")).sendKeys("subash");
		driver.findElement(By.id("last_name")).sendKeys("mappillaisamy");
		driver.findElement(By.id("address")).sendKeys("palani");
		driver.findElement(By.id("cc_num")).sendKeys("4012 3526 1256 2312");
		
		WebElement card = driver.findElement(By.id("cc_type"));
		Select cc=new Select(card);
		cc.selectByIndex(2);
		
		WebElement mo = driver.findElement(By.id("cc_exp_month"));
		Select month=new Select(mo);
		month.selectByIndex(4);
		
		
		WebElement ye = driver.findElement(By.id("cc_exp_year"));
		Select year=new Select(ye);
		year.selectByValue("2022");
		
		driver.findElement(By.id("cc_cvv")).sendKeys("401");
		
		driver.findElement(By.id("book_now")).click();

	}
	@Test(priority=15)
	public void cancel() throws AWTException, InterruptedException {
		Thread.sleep(6000);
		driver.findElement(By.id("my_itinerary")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("check_all")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("cancelall")).click();
		 Robot robot=new Robot();
		 robot.keyPress(KeyEvent.VK_ENTER);	
		
	}
	@Test(priority=16)
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
	driver.findElement(By.id("logout")).click();
	WebElement fe = driver.findElement(By.xpath("//td[@class=\"reg_success\"]"));
	
	String Actualtitle=fe.getText();
	String expectedtitle="You have successfully logged out. Click here to login again";
	Assert.assertEquals(Actualtitle, expectedtitle);
	
	System.out.println(fe.getText());
	System.out.println("        ");
	}
		
	}
			
		
		
		

	

		
	


