package com.example.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.example.automation.base.BaseFactory;
import com.example.automation.utils.commonUtils.UIUtils;

public class LandingPage extends BaseFactory {

     UIUtils uiUtils = new UIUtils();
     
    public LandingPage() {
        // Constructor logic if needed
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_item']")
    List<WebElement> inventoryItems;

    public int getInventoryItemCount() {
        UIUtils.waitForElementVisiblibility(inventoryItems, 5);
        return inventoryItems.size();
    }

}
