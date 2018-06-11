package com.frameworkium.ui.integration.heroku.theinternet.pages;

import com.frameworkium.ui.annotations.Invisible;
import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DynamicLoadingExamplePage extends BasePage<DynamicLoadingExamplePage> {

    @Visible
    @Name("Start button")
    @FindBy(css = "#start button")
    private Button startButton;

    @Invisible
    @Timeout(0) // prevents page load taking 5s due to implicit timeout
    @Name("Hidden element")
    @FindBy(id = "finish")
    private HtmlElement dynamicElement;


    public DynamicLoadingExamplePage clickStart() {
        startButton.click();
        wait.until(visibilityOf(dynamicElement));
        return this;
    }


    public DynamicLoadingExamplePage waitForElementToBeDisplayed() {
        wait.until(visibilityOf(dynamicElement));
        return this;
    }

    public boolean isElementDisplayed() {
        return dynamicElement.isDisplayed();
    }

}
