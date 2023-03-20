package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import org.apache.commons.io.FileUtils; // FileUtils thực hiện các chức năng đọc, ghi, copy, so sánh file
import org.openqa.selenium.OutputType; // Thực hiện capture screenshot và lưu trữ tại nơi chỉ định
import org.openqa.selenium.TakesScreenshot; // Thực hiện capture screenshot và lưu trữ tại nơi chỉ định

import java.io.File;
import java.sql.Driver;
import java.time.Duration;

public class Testcase04 {
    @Test
    public static void Testcase04(){
        try {
//
            WebDriver driver = driverFactory.getChromeDriver();

        /* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.
        Test Steps:
        1. Go to https://www.meta.vn/
        */

            driver.get("https://www.meta.vn/");
            /*  2. Click on */
            WebElement e1 = driver.findElement(By.cssSelector("#account_history"));
            e1.click();
            /* 3.  */
            WebElement e2 = driver.findElement(By.cssSelector("a[class='signin-openID facebook fb-btn'] div"));
            e2.click();
            WebElement email = driver.findElement(By.cssSelector("#email"));
            WebElement pass = driver.findElement(By.cssSelector("#pass"));
            email.sendKeys("phanvoanhkiet1389@gmail.com");
            pass.sendKeys("Anhkiet1309@");
//            Thread.sleep(2000);
            /* 4. Click Register */
            WebElement Login = driver.findElement(By.cssSelector("#loginbutton"));
            Login.click();
            /* 6. Go to Điện máy menu */
            Thread.sleep(2000);
            WebElement e3 = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/nav[1]/div[1]/div[1]/ul[1]/li[2]/a[1]"));
            e3.click();
            /*7. Add product in your wish list - use product -"máy cà phê"*/
            WebElement e4 = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/aside[1]/div[2]/div[3]/ul[1]/li[1]/label[1]/a[1]/i[1]"));
            e4.click();
            Actions action = new Actions(driver);

            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/section[1]/section[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[3]/a[1]/img[1]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/section[1]/section[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[7]/a[1]")))
                    .build().perform();
            //8.thao tác điền thông tin

            Thread.sleep(2000);
            WebElement name1 = driver.findElement(By.xpath("//input[@id='txtBillingName']"));
            name1.sendKeys("Phan Võ Anh Kiệt");

            WebElement SĐT1 = driver.findElement(By.xpath("//input[@id='txtBillingPhone']"));
            SĐT1.sendKeys("0356558358");


            WebElement TP = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/span[1]"));
            TP.click();
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]")))
                    .build().perform();

            WebElement QUAN = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/span[1]"));
            QUAN.click();
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[18]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[18]")))
                    .build().perform();

            WebElement PHUONG = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/span[1]"));
            PHUONG.click();
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[2]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[2]")))
                    .build().perform();
//            WebElement TP = driver.findElement(By.xpath("//span[contains(text(),'Hồ Chí Minh')]"));
//            Select selecttp = new Select(TP);
//            selecttp.selectByVisibleText("Hồ Chí Minh");

//            WebElement Quan = driver.findElement(By.cssSelector("//span[contains(text(),'Quận Tân Phú')]"));
//            Quan.sendKeys("Quận Tân Phú");
//
//            WebElement Xa = driver.findElement(By.cssSelector("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/span[1]"));
//            Xa.sendKeys("Phường Hòa Thạnh");

            WebElement Sonha = driver.findElement(By.xpath("//input[@id='address']"));
            Sonha.sendKeys("13");
            Thread.sleep(2000);

            TakesScreenshot scrShot = ((TakesScreenshot)driver);
            File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("E://HK5//CNKTUD//dulieu//selenium-DACK//img//hinh_testcase4.png");
            FileHandler.copy(srcFile, destFile);
            Thread.sleep(3000);
//            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
