package com.qaprosoft.carina.demo.gui.pages.web;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

public class MyCartPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(className = "title")
    private ExtendedWebElement title;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    private ExtendedWebElement checkoutBtn;

    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement shopCartBadge;

    public MyCartPage(WebDriver driver) {
        super(driver);
        setPageURL("/cart.html");
    }

    public boolean isCartTitleExist() {
        return title.isElementPresent();
    }

    public boolean isCheckoutBtnExist() {
        return checkoutBtn.isElementPresent();
    }

    public Integer getCartItemQuantity() {
        return Integer.parseInt(shopCartBadge.getText());
    }
}
