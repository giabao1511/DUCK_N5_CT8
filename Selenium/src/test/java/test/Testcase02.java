package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class Testcase02 {
    public static void main(String[] args) throws InterruptedException {
        //Step 1. Go to https://www.meta.vn/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("https://www.meta.vn/");
        //Step 2. Click on -> Điện máy -> menu
        WebElement dienmayElem = driver.findElement(By.xpath("//a[@id='menuLink1013']"));
        dienmayElem.click();
        WebElement LGElem = driver.findElement(By.xpath("//span[@title='LG']"));
        LGElem.click();

        // Step 3: Read the cost of the Máy giặt LG Inverter 9kg FM1209S6W (6.790.000 đ)
        Thread.sleep(2000);
        WebElement maygiatPrice = driver.findElement(By.xpath("//div[@class='list-product-highlight']//span[@class='product-price-meta'][contains(text(),'6.790.000 đ')]"));
        String maygiatPriceText = maygiatPrice.getText();
        System.out.println("The cost of the Máy giặt LG Inverter 9kg FM1209S6W is: " + maygiatPriceText);
        Thread.sleep(2000);
        // Step 4: Click on the Máy giặt LG Inverter 9kg FM1209S6W LG (Điện máy)
        WebElement maygiatElem = driver.findElement(By.xpath("//h3[contains(text(),'Máy giặt LG Inverter 9kg FM1209S6W')]"));
        maygiatElem.click();

        // Step 5: Read the Máy giặt LG Inverter 9kg FM1209S6W from the detail page
        WebElement maygiatDetail = driver.findElement(By.xpath("//h1[@title='Máy giặt LG Inverter 9kg FM1209S6W']"));
        String maygiatDetailText = maygiatDetail.getText();
        System.out.println(" Máy giặt LG Inverter 9kg FM1209S6W in detail page is: " + maygiatDetailText);

        // Step 6: Compare the product value in the list and detail pages
        WebElement maygiatDetailPrice = driver.findElement(By.xpath("//span[text()='6.790.000 đ']"));
        String maygiatDetailPriceText = maygiatDetailPrice.getText();
        if (maygiatPriceText.equals(maygiatDetailPriceText)) {
            System.out.println("The product value in the list and detail pages is equal.");
        } else {
            System.out.println("The product value in the list and detail pages is not equal.");
        }
        //screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File("E://HK5//CNKTUD//dulieu//selenium-DACK//img//hinh_testcase2.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the browser
        driver.quit();
    }
}
