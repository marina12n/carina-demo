package com.qaprosoft.carina.demo.gui.pages.web;

import com.google.common.collect.Ordering;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
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

    @FindBy(xpath = "//*[./*[./*[text()= '%s']]]/following-sibling::*/*[contains(@class, 'btn btn_primary btn_small btn_inventory')]")
    private List<ExtendedWebElement> addToCartBtns;

    @FindBy(xpath = "//*[@class='inventory_item']")
    private List<ExtendedWebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cartLink;

    @FindBy(xpath = "//*[@class='product_sort_container']")
    private ExtendedWebElement dropDownMenu;

    @FindBy(xpath = "//*[@class='product_sort_container']//*[@value='%s']")
    private ExtendedWebElement dropDownValue;


    @FindBy(xpath = "//*[@class='inventory_item_price']")
    private List<ExtendedWebElement> itemPrices;

    @FindBy(xpath = "//*[@class='active_option']")
    private ExtendedWebElement activeOption;

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
        return addToCart.isElementPresent();
    }

    public boolean isCartLinkExist() {
        return cartLink.isElementPresent();
    }

    public boolean isActiveOptionExist() {
        return activeOption.isElementPresent();
    }

    public boolean isDropDownMenuExist() {
        return dropDownMenu.isElementPresent();
    }

    public boolean isDropDownValueExist(){
        return dropDownValue.isElementPresent();
    }


    public MyCartPage clickCartIcon() {
        cartLink.click();
        return new MyCartPage(getDriver());
    }

    public String getDefaultDropDownValue(){
        return dropDownMenu.getSelectedValue();
    }


    public String getActiveOptionValue(){
        return activeOption.getText();
    }

    public void clickAddToCart(){
        addToCart.click();
    }

    public void clickSortDropDown(){
        LOGGER.info("Drop Down menu is clicked");
        dropDownMenu.click();
    }

    public void clickDropDownValue(String value) {
        LOGGER.info("Drop down value is: " + value);
        dropDownValue.format(value).click();
    }

    public boolean verifyAlphabeticalOrder() {
        List<String> sortedItem = new ArrayList<>();
        for (ExtendedWebElement item: inventoryItems) {
            sortedItem.add(String.valueOf(item.getText().charAt(0)));
        }
        return Ordering.natural().isOrdered(sortedItem);
    }

    public boolean verifyReverseAlphabeticalOrder() {
        List<String> sortedItem = new ArrayList<>();
        for (ExtendedWebElement item: inventoryItems) {
            sortedItem.add(String.valueOf(item.getText().charAt(0)));
        }
        return Ordering.natural().reverse().isOrdered(sortedItem);
    }

    public boolean verifyPriceLowToHigh() {
        List<Double> sortedItem = new ArrayList<>();
        for (ExtendedWebElement item: itemPrices) {
            sortedItem.add(Double.parseDouble(item.getText().substring(1)));
        }
        return Ordering.natural().isOrdered(sortedItem);
    }

    public boolean verifyPriceHighToLow() {
        List<Double> sortedItem = new ArrayList<>();
        for (ExtendedWebElement item: itemPrices) {
            sortedItem.add(Double.parseDouble(item.getText().substring(1)));
        }
        return Ordering.natural().reverse().isOrdered(sortedItem);
    }

}