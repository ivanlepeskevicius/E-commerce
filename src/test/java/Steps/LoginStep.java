package Steps;

import Base.BaseUtil;
import Pages.AccountPage;
import Pages.NavigationBasics;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import Pages.LoginPage;
import java.util.List;


public class LoginStep extends BaseUtil{

    private  BaseUtil b;

    public LoginStep(BaseUtil base) {
        this.b = base;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage(){

        System.out.println("Navigate Login Page");
        b.Driver.get("http://automationpractice.com/");

        NavigationBasics nav = new NavigationBasics (b.Driver);
        nav.LoginPage();

    }

    @When("^I enter the following for Login$")
    public void iEnterTheFollowingForLogin(DataTable table){
        //Create an ArrayList
        List<User> users;
        //Store all the users
        users = table.asList(User.class);

        LoginPage page = new LoginPage(b.Driver);

        for (User user: users){
            page.Login(user.username, user.password);
        }

    }

    @And("^I click login button$")
    public void iClickLoginButton(){

        LoginPage page = new LoginPage(b.Driver);
        page.ClickLogin();

    }

    @And("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterUsernameAndPassword(String userName, String password){

        System.out.println("UserName is : " + userName);
        System.out.println("Password is : " + password);

        LoginPage page = new LoginPage(b.Driver);
        page.Login(userName, password);

    }

    @Then("^I should see my account page$")
    public void iShouldSeeMyAccountPage(){

        AccountPage page = new AccountPage(b.Driver);
        assertEquals(page.PageTitle(), "MY ACCOUNT");

    }

    @Then("^I should see an error message$")
    public void iShouldSeeAnErrorMessage(){

        WebDriverWait wait = new WebDriverWait(b.Driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-danger")));

        assertTrue(b.Driver.findElement(By.className("alert-danger")).isDisplayed());
    }


    public class User {
        private String username;
        private String password;

        public User(String userName, String passWord) {
            username = userName;
            password = passWord;
        }
    }

}
