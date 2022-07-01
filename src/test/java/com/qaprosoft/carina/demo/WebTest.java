/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.demo.gui.pages.web.MyCartPage;
import com.qaprosoft.carina.demo.gui.pages.web.MyHomePage;
import com.qaprosoft.carina.demo.gui.pages.web.MyInventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.testng.asserts.SoftAssert;


public class WebTest implements IAbstractTest {
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private static final String EMPTY_USERNAME = "Epic sadface: Username is required";
    private static final String EMPTY_PASSWORD = "Epic sadface: Password is required";

    MyHomePage myHomePage = new MyHomePage(getDriver());

    public MyInventoryPage inventoryAfterLogin(){
        myHomePage.open();
        MyInventoryPage myInventoryPage = myHomePage.userLogin(USERNAME, PASSWORD);
        return myInventoryPage;
    }

    @Test()
    public void testLoginForm() {
        // Open home page and verify page is opened
        myHomePage.open();
        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened");
        // Test if username field exist on the page
        Assert.assertTrue(myHomePage.isUserNameFieldExist(), "User name wasn't found!");
        // Test if password field exist on the page
        Assert.assertTrue(myHomePage.isPasswordFieldExist(), "Password field wasn't found!");
        // Test if btn field exist on the page
        Assert.assertTrue(myHomePage.isLoginBtnExist(), "Button wasn't found!");
    }

        @Test()
        public void testVerifyLoginFormPosition() {
        // Test if all elements of login form placed as expected
        myHomePage.open();
        Assert.assertTrue(myHomePage.verifyLocationLoginBtn(), "Login btn isn't below password field");
        Assert.assertTrue(myHomePage.verifyLocationUserName(), "Username field isn't above password field");
    }

    @Test()
    public void testEmptyUsernameField() {
        // Test when username is empty
        myHomePage.open();
        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened");
        myHomePage.clickLoginBtn();

        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(myHomePage.isErrorMsgExist(), "Error msg isn't exist");
        String actualErrorMsg = myHomePage.getLoginError();
        Assert.assertEquals(actualErrorMsg, EMPTY_USERNAME, "Error's msg isn't the same");
    }

    @Test()
    public void testWrongCredentials(){
        myHomePage.open();
        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened");
        myHomePage.userLogin("test_user", "test_password");
        String actualErrorMsg = myHomePage.getLoginError();
        Assert.assertEquals(actualErrorMsg, "Epic sadface: Username and password do not match any user in this service",
                "Error's msg isn't the same");
    }
    @Test()
    public void testEmptyPasswordField() {
        // Test when password is empty
        myHomePage.open();
        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened");
        myHomePage.typeUsername(USERNAME);
        myHomePage.clickLoginBtn();

        Assert.assertTrue(myHomePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(myHomePage.isErrorMsgExist(), "Error msg isn't exist");
        String actualErrorMsg = myHomePage.getLoginError();
        Assert.assertEquals(actualErrorMsg, EMPTY_PASSWORD, "Error's msg isn't the same");
    }

    @Test()
    public void testCheckItems() {
        // Test if items is present
        MyInventoryPage myInventoryPage = inventoryAfterLogin();
        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(myInventoryPage.isPageOpened(), "Inventory page isn't opened");
        softAssert.assertTrue(myInventoryPage.isProductInventoryExist(), "List of products is not exist");
        softAssert.assertTrue(myInventoryPage.isProductTitleExist(), "Product name isn't exist");
        softAssert.assertTrue(myInventoryPage.isAddToCartBtnExist(), "Add to cart btn isn't exist");
        softAssert.assertAll();
    }

    @Test()
    public void testIfCartIsOPen() {
        MyInventoryPage myInventoryPage = inventoryAfterLogin();

        Assert.assertTrue(myInventoryPage.isCartLinkExist(), "Cart icon isn't exist");
        MyCartPage myCartPage = myInventoryPage.clickCartIcon();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(myCartPage.isPageOpened(), "Cart page isn`t opened");
        softAssert.assertTrue(myCartPage.isCartTitleExist(), "Title of the cart is not exist");
        softAssert.assertTrue(myCartPage.isCheckoutBtnExist(), "Checkout button doesn't exist");
        softAssert.assertAll();
    }

    @Test
    public void testCartQuantity(){
        MyInventoryPage myInventoryPage = inventoryAfterLogin();
        myInventoryPage.clickAddToCart();
        myInventoryPage.clickAddToCart();
        MyCartPage myCartPage = myInventoryPage.clickCartIcon();
        Assert.assertEquals(myCartPage.getCartItemQuantity(), 2);
    }

    @Test
    public void testVerifyDropDownMenu(){
        MyInventoryPage myInventoryPage = inventoryAfterLogin();
        Assert.assertTrue(myInventoryPage.isPageOpened(), "Inventory page isn't opened");
        Assert.assertEquals(myInventoryPage.getActiveOptionValue(), "NAME (A TO Z)", "Selected option isn't default");
        myInventoryPage.clickDropDownValue("lohi");
        Assert.assertEquals(myInventoryPage.getActiveOptionValue(), "PRICE (LOW TO HIGH)", "Selected option isn't equal");
    }

    @Test
    public void testDropDownSortVerify(){
        MyInventoryPage myInventoryPage = inventoryAfterLogin();
        Assert.assertTrue(myInventoryPage.isPageOpened(), "Inventory page isn't opened");
        myInventoryPage.clickSortDropDown();
        myInventoryPage.clickDropDownValue("az");
        Assert.assertTrue(myInventoryPage.verifyAlphabeticalOrder(), "Items isn't ordered from A to Z");
        myInventoryPage.clickDropDownValue("za");
        Assert.assertTrue(myInventoryPage.verifyReverseAlphabeticalOrder(), "Items isn't ordered from Z to A");
        myInventoryPage.clickDropDownValue("lohi");
        Assert.assertTrue(myInventoryPage.verifyPriceLowToHigh(), "Items isn't ordered from Low to High price");
        myInventoryPage.clickDropDownValue("hilo");
        Assert.assertTrue(myInventoryPage.verifyPriceHighToLow(), "Items isn't ordered from Low to High price");
    }
}