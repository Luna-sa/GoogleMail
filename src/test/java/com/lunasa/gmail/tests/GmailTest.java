package com.lunasa.gmail.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTest {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
	}

	@Test
	public void GmailTest() {
	        //We need to check a sending letters using Google mail.
            //Precondition: create an account for any mail services mentioned above.
            //Test scenario/flow:

            // Launch the Firefox browser:
        System.setProperty("webdriver.gecko.driver", "D:\\Freelance\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//      Login to the mail box.
        driver.get("https://accounts.google.com/ServiceLogin/signinchooser?");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//      Enter the name of your mail:
        WebElement loginField = driver.findElement(By.id("identifierId"));
        loginField.sendKeys("msslunasa@gmail.com");
        WebElement loginButton1 = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        loginButton1.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
//      Enter your password:
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys("Rty67ui9");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        WebElement loginButton2 = wait2.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
        loginButton2.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//      Assert, that the login is successful.
        driver.get("https://mail.google.com/mail/");
        WebDriverWait wait3 = new WebDriverWait(driver, 10);
        wait3.until(ExpectedConditions.titleContains("msslunasa@gmail.com - Gmail"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//      Create a new mail (fill addressee, subject and body fields).
        driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait4 = new WebDriverWait(driver, 10);
        wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/textarea[@name='to']")));
        WebElement sentTo = driver.findElement(By.xpath("//div/textarea[@name='to']"));
        sentTo.sendKeys("mslunasa@gmail.com");
        WebElement subject = driver.findElement(By.xpath("//div/input[@name='subjectbox']"));
        subject.sendKeys("Hello World");
        WebElement messageBody = driver.findElement(By.xpath("//div[@role='textbox']"));
        messageBody.sendKeys("Just want to say hi");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement closeButton = driver.findElement(By.xpath("//img[@alt='Close']"));
        closeButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//      Verify, that the mail presents in ‘Drafts’ folder.
        driver.get("https://mail.google.com/mail/u/0/#drafts");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement draftLetter = driver.findElement(By.xpath("//*[contains(text(), 'Hello World')]/ancestor::tr/td/div[@role='link']"));
        draftLetter.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//      Verify the draft content (addressee, subject and body – should be the same as in 3).
        driver.findElement(By.xpath("//div/span/a[@title='Drafts (1)']"));
        driver.findElement(By.xpath("//div/span[@email='mslunasa@gmail.com']"));
        driver.findElement(By.xpath("//form/input[@value='Hello World']"));
        driver.findElement(By.xpath("//*[contains(text(), 'Just want to say hi')]"));
//      Send the mail.
        WebElement sendButton = driver.findElement(By.xpath("//div[@role='button' and contains(text(), 'Send')]"));
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//      Verify, that the mail disappeared from ‘Drafts’ folder.
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//      Verify, that the mail is in ‘Sent’ folder.
        driver.findElement(By.xpath("//div/span/a[@title='Drafts']"));
        driver.get("https://mail.google.com/mail/u/0/#sent");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(), 'Hello World')]/ancestor::tr/td/div[@role='link']"));
//      Log off.
        driver.get("https://accounts.google.com/SignOutOptions?hl=en-GB&continue=https://mail.google.com/mail&service=mail");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/Logout?hl=en-GB&continue=https://mail.google.com/mail&service=mail&timeStmp=1533070972&secTok=.AG5fkS9JgVx6uFohW2W2F3tkHZMZiOF11g");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
	}
}
