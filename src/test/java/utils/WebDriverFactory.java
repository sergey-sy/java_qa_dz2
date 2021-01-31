package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    public static WebDriver create(String driverName) {

        return create(driverName, null);
    }

    public static WebDriver create(String driverName, MutableCapabilities options) {

        String driverUpperCaseName = driverName.toUpperCase();

        if (driverUpperCaseName.equals(WebDrivers.CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = (options != null) ? (ChromeOptions) options : new ChromeOptions();
            return new ChromeDriver(chromeOptions);
        }

        if (driverUpperCaseName.equals(WebDrivers.FIREFOX.name())) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = (options != null) ? (FirefoxOptions) options : new FirefoxOptions();
            return new FirefoxDriver(firefoxOptions);
        }

        Assert.fail(String.format("Запрашиваемый браузер - %s не поддержан", driverName));
        return null;
    }
}
