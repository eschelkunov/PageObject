import Pages.FilesPage;
import Pages.LoginsPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends ChromeConfig {

    protected static LoginsPage loginsPage;
    protected static FilesPage filesPage;

    @BeforeTest(dependsOnMethods = "startChrome")
    protected void initPages(){
        loginsPage = new LoginsPage(driver);
        filesPage = new FilesPage(driver);
        System.out.println("Pages are initialized");
    }

    @Test
    protected void failureLoginTest(){
        loginsPage.doFailure();
    }

    @Test(groups = "login")
    protected void successLoginTest(){
        loginsPage.doSuccess();
    }

    @Test(dependsOnGroups = "login", groups = "upload")
    protected void uploadTest(){
        filesPage.doUpload();
    }

    @Test(dependsOnGroups = {"login","upload"})
    protected void deleteTest(){
        filesPage.doDelete();
    }

}
