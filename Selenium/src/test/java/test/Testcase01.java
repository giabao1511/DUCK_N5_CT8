package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.time.Duration;

public class Testcase01 {
    @Test
    public static void Testcase01() {

        try {
            //Step 1. Go to https://www.meta.vn/
            WebDriver driver = driverFactory.getChromeDriver();
            driver.get("https://www.meta.vn/");
            //Step 2. Click on -> Điện máy -> menu
            WebElement dienmayElem = driver.findElement(By.xpath("//a[@id='menuLink1013']"));
            dienmayElem.click();
            //Step 3. In the list of all Điện máy ,  dropdown as LG

            WebElement LGElem = driver.findElement(By.xpath("//span[@title='LG']"));
            LGElem.click();


            Thread.sleep(2000);
            //Step 4. Verify all products are
            TakesScreenshot scrShot = ((TakesScreenshot)driver);
            File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("E://HK5//CNKTUD//dulieu//selenium-DACK//img//hinh_testcase1.png");
            FileHandler.copy(srcFile, destFile); // Chuyển file image từ SrcFile sang DestFile
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}