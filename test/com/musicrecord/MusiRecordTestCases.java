package com.musicrecord;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    public static void initWebDriver() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
	driver = new ChromeDriver();
    }

    @AfterClass
    public static void theEnd() {
	driver.quit();
    }

    @Test
    public void testFetchRecord() throws Exception {
	try {
	    loginAsUser();
	    driver.findElement(By.id("gwt-debug-textSearch")).sendKeys("Animal");
	    new Select(driver.findElement(By.id("gwt-debug-listBoxSearchBy"))).selectByVisibleText("Title");
	    driver.findElement(By.id("gwt-debug-textSearch")).clear();
	    driver.findElement(By.id("gwt-debug-textSearch")).sendKeys("Animal");
	    driver.findElement(By.id("gwt-debug-textSearch")).clear();
	    findRecord();
	} catch (Exception ex) {
	    captureScreenshot("fetchRecordException");
	    throw ex;
	}
    }

    @Test
    public void testEditRecord() throws Exception {
	try {
	    loginAsAdmin();
	    // First Adding a test record to be edited.
	    addRecord("My Title", "My Artist");
	    Thread.sleep(2000);
	    editRecord();
	    Thread.sleep(2000);
	    // Deleting the test record after editing.
	    deleteRecord();
	} catch (Exception ex) {
	    captureScreenshot("edit Record Exception");
	    throw ex;
	}
    }

    @Test
    public void testDeleteRecord() throws Exception {
	try {
	    loginAsAdmin();
	    // Adding a test record for deletion
	    addRecord("My Title", "My Artist");
	    Thread.sleep(2000);
	    deleteRecord();

	} catch (Exception ex) {
	    captureScreenshot("deleteRecordException");
	    throw ex;
	}
    }

    @Test
    public void testAddRecord() throws Exception {
	try {
	    Thread.sleep(2000);
	    loginAsAdmin();
	    Thread.sleep(2000);
	    addRecord("My Title", "My Artist");
	    Thread.sleep(2000);
	    // Deleting the added test record
	    deleteRecord();
	    Thread.sleep(2000);
	} catch (Exception ex) {
	    captureScreenshot("fetchRecordException");
	    throw ex;
	}

    }

    private void addRecord(String newTitle, String newArtist) throws Exception {
	driver.findElement(By.id("gwt-debug-buttonAdd")).click();
	saveUpdateRecord(newTitle, newArtist);
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
	findRecord();
	WebElement editButton = driver
		.findElement(By.xpath("//*[@id='bodyContainer']/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[4]"));
	editButton.click();
	driver.findElement(By.id("gwt-debug-textTitle")).clear();
	driver.findElement(By.id("gwt-debug-textArtist")).clear();
	saveUpdateRecord("My Article Edited", "My Artist Edited");
    }

    private void loginAsAdmin() throws Exception {
	driver.get(pathToGwtApp);
	Thread.sleep(2000);
	driver.findElement(By.id("gwt-debug-textUserName")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-textPassword")).sendKeys("admin");
	driver.findElement(By.id("gwt-debug-buttonSubmit")).click();
	Thread.sleep(2000);
    }

    private void loginAsUser() throws Exception {

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

    public static void captureScreenshot(String screenshotName) {

	try {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String dest = "war/seleniumImages/" + screenshotName + ".png";
	    File destination = new File(dest);
	    FileUtils.copyFile(source, destination);
	    System.err.println("Screenshot saved to: " + dest);
	} catch (IOException e) {
	    System.err.println("Taking screenshot failed for: " + screenshotName);
	    e.printStackTrace();
	}
    }

}
