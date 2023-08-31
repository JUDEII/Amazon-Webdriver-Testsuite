package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class StartBrowser {

    private WebDriver driver;

    public StartBrowser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver initializeBrowser() throws IOException {
        Properties config = new Properties();
        FileInputStream fetchFile = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/config/config.properties");
        config.load(fetchFile);
        String browserName = config.getProperty("browser");
        String chromeSettings = config.getProperty("setChromeToHeadless");
        String chromeBrowserResolution = config.getProperty("chromeBrowserResolution");

        boolean useHeadless = false;
        if(!StringUtils.isBlank(chromeSettings) && chromeSettings.equalsIgnoreCase("true")) {
            useHeadless = true;
            System.out.println("Tests will be run in *HEADLESS* mode");
        } else {
            System.out.println("Tests will NOT be run in headless mode");
        }

        if (StringUtils.isBlank(browserName)){
            System.out.println("No browser has been selected");
            return driver;
        }

        if (browserName.equalsIgnoreCase("chrome115")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver/Google-Chrome-for-Testing.app");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(useHeadless);
            options.addArguments(chromeBrowserResolution);
            this.driver = new ChromeDriver(options);
        } else if(browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver/geckodriver");
            driver = new FirefoxDriver();
        } else {
            System.out.println("No browser has been selected");
        }

        System.out.println("Browser Initialised. Test Passed");
        return driver;
    }

    public void launchBrowser() throws IOException {
        Properties config = new Properties();
        FileInputStream fetchFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/config.properties");
        config.load(fetchFile);
        String url = config.getProperty("baseUrl");
        System.out.println(url);

        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.get(url);
        this.driver.manage().window().maximize();
        System.out.println("Browser Successfully Initialised");
    }
}
