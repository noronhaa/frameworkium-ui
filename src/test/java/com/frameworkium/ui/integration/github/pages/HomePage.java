package com.frameworkium.ui.integration.github.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.integration.github.pages.components.HeaderComponent;
import com.frameworkium.ui.pages.BasePage;
import com.frameworkium.ui.pages.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HomePage extends BasePage<HomePage> {

    @Visible
    private HeaderComponent header;

    @FindBy(css = "form[class*='signup'] button")
    private WebElement signUpButton;

    public static HomePage open() {
        return PageFactory.newInstance(HomePage.class, "https://github.com");
    }

    public HeaderComponent theHeader() {
        return header;
    }

    public void waitForSomething() {
        wait.until(visibilityOfElementLocated(By.cssSelector("body")));
    }
}
