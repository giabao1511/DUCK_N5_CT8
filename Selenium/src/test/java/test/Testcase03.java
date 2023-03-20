package test;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Testcase03 {
    public static void main(String[] args) throws InterruptedException {


            // Create a new instance of the ChromeDriver
            //Step 1. Go to https://www.meta.vn/
            WebDriver driver = driverFactory.getChromeDriver();
            driver.get("https://www.meta.vn/");
            //Step 2. Click on -> Điện máy -> menu
            WebElement dienmayElem = driver.findElement(By.xpath("//a[@id='menuLink1013']"));
            dienmayElem.click();
            WebElement LGElem = driver.findElement(By.xpath("//span[@title='LG']"));
            LGElem.click();

            // Step 3: Click on Mua ngay
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/section[1]/section[1]/div[1]/div[2]/div[1]/ul[1]/li[1]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/section[1]/section[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[7]/a[1]")))
                    .build().perform();


            // Step 4: Change "QTY" value to 1000 and click "UPDATE" button
            Thread.sleep(2000);
            WebElement qtyInput = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div[1]/input[1]"));
            qtyInput.sendKeys("00");
            Thread.sleep(2000);
            WebElement qty1Input = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div[1]/input[1]"));
            qty1Input.sendKeys("00");
            Thread.sleep(2000);
            WebElement qty2Input = driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/div[1]/input[1]"));
            qty2Input.sendKeys("00");

            // Step 6: Click on Xoa
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[2]/a[1]")))
                    .click(driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[2]/a[1]")))
                    .build().perform();
            WebElement delete = driver.findElement(By.xpath("/html[1]/body[1]/div[9]/div[1]/div[2]/div[2]/button[1]"));
            delete.click();

            // Step 7: Verify cart is empty
            Thread.sleep(2000);
            List<WebElement> cartEmptyMsg = driver.findElements(By.xpath("//div[@class='empty-title']"));
            Assert.assertEquals(cartEmptyMsg.size(), 1, "Chưa có sản phẩm nào trong giỏ hàng của bạn!");


            // Screenshot

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile,
                        new File("E://HK5//CNKTUD//dulieu//selenium-DACK//img//hinh_testcase3.png"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Close the browser
            driver.quit();
    }
}