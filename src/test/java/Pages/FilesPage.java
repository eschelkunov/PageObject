package Pages;

import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class FilesPage extends Tools {

    public FilesPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'tools.txt')]")
    private WebElement testFile;

    @FindBy(xpath = "//span[contains(text(),'Upload files')]/ancestor::button")
    private WebElement uploadButton;

    public void doUpload(){
        waitForElementDisplayedXpath("//span[contains(text(),'Upload files')]/ancestor::button");
        uploadButton.click();
        waitForElementDisplayedCss("button[aria-label='Switch to the basic file uploader']");
        driver.findElement(By.cssSelector("button[aria-label='Switch to the basic file uploader']")).click();
        driver.findElement(By.cssSelector("input[type='file'][aria-label='Upload files']")).sendKeys("D:\\tools.txt");
        waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
    }

    public void doDelete(){
        driver.get("https://dropbox.com/home");
        waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
        waitForElementDisplayedXpath("//button[@role='button'][contains(@class,'browse-overflow')]");
        driver.findElement(By.xpath("//button[@role='button'][contains(@class,'browse-overflow')]")).click();
        waitForElementDisplayedXpath("//div//button[@role='menuitem'][contains(text(),'Delete')]");
        driver.findElement(By.xpath("//div//button[@role='menuitem'][contains(text(),'Delete')]")).click();
        waitForElementDisplayedCss("button.button-primary");
        driver.findElement(By.cssSelector("button.button-primary")).click();
        sleep(5);
        Assert.assertTrue(driver.findElements(By.xpath("//span[contains(text(),'tools.txt')]")).isEmpty());
    }

}
