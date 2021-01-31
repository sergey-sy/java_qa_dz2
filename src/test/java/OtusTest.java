import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import utils.WebDriverFactory;

public class OtusTest {

    public static String envDriverName = null;
    private final Logger logger = LogManager.getLogger(OtusTest.class);
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        envDriverName = System.getenv("browser");
    }

    @Before
    public void setDriver() {
        logger.info("Настраиваю веб-драйвер");
        if (envDriverName == null) {
            envDriverName = "CHROME";
        }
        driver = WebDriverFactory.create(envDriverName);

        int implicity = 3;
        logger.info(String.format("Устанавливаю неявное ожидание для драйвера в секундах, равным %s", implicity ));
        driver.manage().timeouts().implicitlyWait(implicity, TimeUnit.SECONDS);
    }

    @After
    public void quitDriver() {
        logger.info("Выхожу из драйвера");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void yandexTitleTest() {
        driver.get("https://yandex.ru");
        driver.manage().window().maximize();
        Assert.assertTrue(driver.getTitle().equals("Яндекс"));
    }

    @Test
    public void tele2SearchFieldTest() throws InterruptedException {
        driver.get("https://msk.tele2.ru/shop/number");
        WebElement searchField = driver.findElement(By.id("searchNumber"));
        searchField.sendKeys("97");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("number-box"), 0));

        List<WebElement> phoneNumbers = driver.findElements(By.className("phone-number"));
        Assert.assertTrue(phoneNumbers.size() > 0);
    }

}
