package com.qaprosoft.carina.demo.gui.pages.web;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MyHomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(className = "login_wrapper")
    private ExtendedWebElement loginWrapper;

    @FindBy(id = "user-name")
    private ExtendedWebElement userName;

    @FindBy(id = "password")
    private ExtendedWebElement password;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private ExtendedWebElement loginBtn;

    @FindBy(className = "error-message-container")
    private ExtendedWebElement errorMsg;


    public MyHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserNameFieldExist() {
        return userName.isElementPresent();
    }

    public boolean isPasswordFieldExist() {
        return password.isElementPresent();
    }

    public boolean isLoginBtnExist() {
        return loginBtn.isElementPresent();
    }

    public boolean verifyLocationLoginBtn() {
        return loginBtn.getLocation().y < password.getLocation().y;
    }

    public boolean verifyLocationUserName() {
        return userName.getLocation().y < password.getLocation().y;
    }

    public boolean isErrorMsgExist() {
        return errorMsg.isElementPresent();
    }

    public void typeUsername(String username) {
        this.userName.type(username);
    }

    public void typePassword(String password) {
        this.password.type(password);
    }

    public MyInventoryPage clickLoginBtn() {
        loginBtn.click();
        LOGGER.info("Login button is clicked");
        return new MyInventoryPage(getDriver());
    }

    public MyInventoryPage userLogin(String userName, String password){
        typeUsername(userName);
        typePassword(password);
        clickLoginBtn();
        return new MyInventoryPage(getDriver());
    }

    public String getLoginError(){
        return errorMsg.getText();
    }

}
