package com.qaprosoft.carina.demo.gui.pages.web;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MyInventoryPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    private ExtendedWebElement title;

    @FindBy(id = "inventory_container")
    private ExtendedWebElement inventory;

    @FindBy(className = "inventory_list")
    private ExtendedWebElement list;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private ExtendedWebElement addToCart;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private List<ExtendedWebElement> addToCartBtns;

    @FindBy(xpath = "//*[@id=\"inventory_container\"]/div/div[1]")
    private ExtendedWebElement inventoryItem;

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cartLink;

    @FindBy(className = "product_sort_container")
    private ExtendedWebElement dropDownMenu;

    @FindBy(xpath = "//option[@value='az']")
    private ExtendedWebElement sortItemAZ;

    @FindBy(xpath = "//option[@value='za']")
    private ExtendedWebElement sortItemZA;

    @FindBy(xpath = "//option[@value='lohi']")
    private ExtendedWebElement sortItemLH;

    @FindBy(xpath = "//option[@value='hilo']")
    private ExtendedWebElement sortItemHL;

    public MyInventoryPage(WebDriver driver) {
        super(driver);
        setPageURL("/inventory.html");
    }

    public boolean isProductTitleExist() {
        return title.isElementPresent();
    }

    public boolean isProductInventoryExist() {
        return inventory.isElementPresent();
    }

    public boolean isAddToCartBtnExist() {
        LOGGER.info("Check if button exist on the product");
        return addToCart.isElementPresent();
    }

    public boolean isCartLinkExist() {
        return cartLink.isElementPresent();
    }

    public boolean isProductInventoryItemExist() {
        return inventoryItem.isElementPresent();
    }

    public boolean isDropDownMenuExist() {
        return dropDownMenu.isElementPresent();
    }

    public MyCartPage clickCartIcon() {
        cartLink.click();
        return new MyCartPage(getDriver());
    }

    public String getDefaultDropDownValue(){
        return dropDownMenu.getSelectedValue();
    }

    public boolean clickAddToCart(int index){
        addToCartBtns.get(index).click();
        return StringUtils.equalsIgnoreCase(addToCartBtns.get(index).getText(), "remove");
    }

    public boolean isDropDownFilterAZPresent(){
        return sortItemAZ.isElementPresent();
    }

    public boolean isDropDownFilterZAPresent(){
        return sortItemZA.isElementPresent();
    }

    public boolean isDropDownFilterHLPresent(){
        return sortItemHL.isElementPresent();
    }

    public boolean isDropDownFilterLHPresent(){
        return sortItemLH.isElementPresent();
    }

    public String selectNonDefaultDropDownValue(String selectedOption){
        String value = "sortItem" + selectedOption;
        
        return "Selected Value: ";
    }
}