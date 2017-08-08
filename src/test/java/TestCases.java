import Pages.FilesPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCases extends Config {

    protected static LoginPage loginPage;
    protected static FilesPage filesPage;

    @BeforeTest(dependsOnMethods = "startChrome")
    protected void initPages(){
        loginPage = new LoginPage(driver);
        filesPage = new FilesPage(driver);
    }

    @Test
    protected void failureLoginTest(){
        loginPage.doFailure();
    }

    @Test(groups = "login")
    protected void successLoginTest(){
        loginPage.doSuccess();
    }

    @Test(dependsOnGroups = "login", groups = "upload")
    protected void uploadTest(){
        filesPage.doUpload();
    }

    @Test(dependsOnGroups = {"upload","login"})
    protected void deleteTest(){
        filesPage.doDelete();
    }





}
