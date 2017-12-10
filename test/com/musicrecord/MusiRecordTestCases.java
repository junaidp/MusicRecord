package com.musicrecord;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

@RunWith(BlockJUnit4ClassRunner.class)
public class MusiRecordTestCases extends TestCase {

    private static WebDriver driver;
    private String pathToGwtApp = "http://127.0.0.1:8888/MusicRecord.html";

    @BeforeClass
    public static void initWebDriver() throws IOException {
	System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
	driver = new ChromeDriver();
    }

    @AfterClass
    public static void theEnd() {
	driver.quit();
    }

    @Test
    public void testAddRecord() throws InterruptedException {

	loginAsAdmin();
	Thread.sleep(2000);
	saveRecord("My Title", "My Artist");
	deleteRecord();
    }

    @Test
    public void testFetchRecord() throws InterruptedException {

	loginAsUser();
	driver.findElement(By.id("gwt-debug-textSearch")).sendKeys("My Title");
	new Select(driver.findElement(By.id("gwt-debug-listBoxSearchBy"))).selectByVisibleText("Title");
	driver.findElement(By.id("gwt-debug-textSearch")).clear();
	driver.findElement(By.id("gwt-debug-textSearch")).sendKeys("Animal");
	driver.findElement(By.id("gwt-debug-textSearch")).clear();
	findRecord();
    }

    @Test
    public void testEditRecord() throws InterruptedException {

	loginAsAdmin();
	Thread.sleep(2000);
	saveRecord("My Title", "My Artist");
	findRecord();
	editRecord();
	deleteRecord();
    }

    @Test
    public void testDeleteRecord() throws InterruptedException {
	loginAsAdmin();
	driver.findElement(By.id("gwt-debug-buttonAdd")).click();
	saveRecord("My Title", "My Artist");
	deleteRecord();
    }

    private void loginAsAdmin() throws InterruptedException {
	driver.get(pathToGwtApp);
	Thread.sleep(2000);
	driver.findElement(By.id("gwt-debug-textUserName")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-textPassword")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-buttonSubmit")).click();
    }

    private void loginAsUser() throws InterruptedException {
	driver.get(pathToGwtApp);
	Thread.sleep(2000);
	driver.findElement(By.id("gwt-debug-textUserName")).sendKeys("wronguser");
	driver.findElement(By.id("gwt-debug-textPassword")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-buttonSubmit")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("gwt-debug-textUserName")).clear();
	driver.findElement(By.id("gwt-debug-textPassword")).clear();
	driver.findElement(By.id("gwt-debug-textUserName")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-textPassword")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-buttonSubmit")).click();
	Thread.sleep(2000);
    }

    private void saveRecord(String newTitle, String newArtist) throws InterruptedException {
	driver.findElement(By.id("gwt-debug-buttonAdd")).click();
	saveUpdateRecord(newTitle, newArtist);
	Thread.sleep(2000);
    }

    private void saveUpdateRecord(String newTitle, String newArtist) {
	driver.findElement(By.id("gwt-debug-textTitle")).sendKeys(newTitle);
	driver.findElement(By.id("gwt-debug-textArtist")).sendKeys(newArtist);
	new Select(driver.findElement(By.id("gwt-debug-listCategory"))).selectByVisibleText("rock");
	driver.findElement(By.id("gwt-debug-buttonSave")).click();
    }

    private void findRecord() {
	new Select(driver.findElement(By.id("gwt-debug-listBoxSearchBy"))).selectByVisibleText("Artist");
	driver.findElement(By.id("gwt-debug-textSearch")).clear();
	driver.findElement(By.id("gwt-debug-textSearch")).sendKeys("My Artist");
    }

    private void deleteRecord() {

	findRecord();
	WebElement deleteButton = driver.findElement(
		By.xpath("//*[@id='bodyContainer']/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[5]/div/button"));
	deleteButton.click();
    }

    private void editRecord() {
	WebElement editButton = driver
		.findElement(By.xpath("//*[@id='bodyContainer']/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[4]"));
	editButton.click();
	driver.findElement(By.id("gwt-debug-textTitle")).clear();
	driver.findElement(By.id("gwt-debug-textArtist")).clear();
	saveUpdateRecord("My Article Edited", "My Artist Edited");
    }

}
