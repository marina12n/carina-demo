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

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.gui.pages.BrandModelsPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.ModelInfoPage;

/**
 * This sample shows how create Web test.
 *
 * @author marina
 */
public class WebLoginTest implements IAbstractTest {

    @FindBy(className = "login_logo")
    private ExtendedWebElement newsColumn;

    @Test()
    @MethodOwner(owner = "marina")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testLoginForm() {
        // Open GSM Arena home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.setUiLoadedMarker(newsColumn);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        //Closing advertising if it's displayed
        //homePage.getWeValuePrivacyAd().closeAdIfPresent();
/*
        // Select phone brand
        homePage = new HomePage(getDriver());
        BrandModelsPage productsPage = homePage.selectBrand("Samsung");
        // Select phone model
        ModelInfoPage productInfoPage = productsPage.selectModel("Galaxy A52 5G");
        // Verify phone specifications
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productInfoPage.readDisplay(), "6.5\"", "Invalid display info!");
        softAssert.assertEquals(productInfoPage.readCamera(), "64MP", "Invalid camera info!");
        softAssert.assertEquals(productInfoPage.readRam(), "6/8GB RAM", "Invalid ram info!");
        softAssert.assertEquals(productInfoPage.readBattery(), "4500mAh", "Invalid battery info!");
        softAssert.assertAll();
*/
    }
}
