package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by eshelkunov on 08.08.2017.
 */
public class LoginPage extends Tools {

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div>input[name='login_email']")
    private WebElement email_field;

    @FindBy(css = "div>input[name='login_password']")
    private WebElement pass_field;

    @FindBy(css = "button[type='submit']>div.sign-in-text")
    private WebElement signIn;

    private String SERVICE_URL = "http://dropbox.com/login";
    private String USERNAME = "testusermail2017@gmail.com";

    private void doLogin(String PASS){
        driver.navigate().to(SERVICE_URL);
        email_field.sendKeys(USERNAME);
        pass_field.sendKeys(PASS);
        signIn.click();
        sleep(5);
    }

    public void doSuccess(){
        doLogin("Password11");
        Assert.assertTrue(driver.getTitle().contains("Dropbox"));
    }

    public void doFailure(){
        doLogin("sdgfdsfgf");
    }

}
