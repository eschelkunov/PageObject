import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCases {

    protected WebDriver driver;

    @BeforeTest
    protected void startChrome(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div>input[name='login_email']")
    private WebElement email_field;

    @FindBy(css = "div>input[name='login_password']")
    private WebElement pass_field;

    @FindBy(css = "button[type='submit']>div.sign-in-text")
    private WebElement signIn;

    @FindBy(xpath = "//span[contains(text(),'tools.txt')]")
    private WebElement testFile;

    @FindBy(xpath = "//span[contains(text(),'Upload files')]/ancestor::button")
    private WebElement uploadButton;
    private String SERVICE_URL = "http://dropbox.com/login";
    private String USERNAME = "testusermail2017@gmail.com";


    private void doLogin(String PASS){
        driver.navigate().to(SERVICE_URL);
        email_field.sendKeys(USERNAME);
        pass_field.sendKeys(PASS);
        signIn.click();
        //sleep(5);
    }

    public void doSuccess(){
        doLogin("Password11");
        Assert.assertTrue(driver.getTitle().contains("Dropbox"));
    }

    public void doFailure(){
        doLogin("sdgfdsfgf");
    }

    public void doUpload(){
       // waitForElementDisplayedXpath("//span[contains(text(),'Upload files')]/ancestor::button");
        uploadButton.click();
       // waitForElementDisplayedCss("button[aria-label='Switch to the basic file uploader']");
        driver.findElement(By.cssSelector("button[aria-label='Switch to the basic file uploader']")).click();
        driver.findElement(By.cssSelector("input[type='file'][aria-label='Upload files']")).sendKeys("D:\\tools.txt");
        //waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
    }

    public void doDelete(){
        driver.get("https://dropbox.com/home");
        //waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
        new Actions(driver).moveToElement(testFile).contextClick().perform();
        //waitForElementDisplayedXpath("//button[@role='button'][contains(@class,'browse-overflow')]");
        driver.findElement(By.xpath("//button[@role='button'][contains(@class,'browse-overflow')]")).click();
        //waitForElementDisplayedXpath("//div//button[@role='menuitem'][contains(text(),'Delete')]");
        driver.findElement(By.xpath("//div//button[@role='menuitem'][contains(text(),'Delete')]")).click();
        //waitForElementDisplayedCss("button.button-primary");
        driver.findElement(By.cssSelector("button.button-primary")).click();
        //sleep(5);
        Assert.assertTrue(driver.findElements(By.xpath("//span[contains(text(),'tools.txt')]")).isEmpty());
    }



    @Test
    protected void failureLoginTest(){
        doFailure();
    }

    @Test(groups = "login")
    protected void successLoginTest(){
        doSuccess();
    }

    @Test(dependsOnGroups = "login", groups = "upload")
    protected void uploadTest(){
        doUpload();
    }

    @Test(dependsOnGroups = {"upload","login"})
    protected void deleteTest(){
        doDelete();
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }



}
