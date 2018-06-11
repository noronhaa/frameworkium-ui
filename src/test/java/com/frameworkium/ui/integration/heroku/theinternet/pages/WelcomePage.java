package com.frameworkium.ui.integration.heroku.theinternet.pages;

import com.frameworkium.ui.annotations.ForceVisible;
import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import com.frameworkium.ui.pages.PageFactory;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class WelcomePage extends BasePage<WelcomePage> {

    @Visible
    @Name("Checkboxes link")
    @FindBy(linkText = "Checkboxes")
    private Link checkboxesLink;

    // ForceVisible not strictly required, just testing it doesn't error
    @ForceVisible
    @Name("Drag and Drop link")
    @FindBy(linkText = "Drag and Drop")
    private Link dragAndDropLink;

    @Name("Dynamic Loading link")
    @FindBy(linkText = "Dynamic Loading")
    private Link dynamicLoadingLink;

    @Name("Hovers Link")
    @FindBy(linkText = "Hovers")
    private Link hoversLink;

    @Name("JavaScript Alerts Link")
    @FindBy(linkText = "JavaScript Alerts")
    private Link javascriptAlertsLink;

    @Name("Key Presses Link")
    @FindBy(linkText = "Key Presses")
    private Link keyPressesLink;

    @Name("Sortable Data Tables Link")
    @FindBy(linkText = "Sortable Data Tables")
    private Link sortableDataTablesLink;


    public static WelcomePage open() {
        return PageFactory.newInstance(
                WelcomePage.class, "http://the-internet.herokuapp.com");
    }

    /**
     * Static factory method for this page object with specified timeout.
     *
     * @param timeout timeout in seconds
     * @return An instance of this page object with specified wait timeout
     */

    public static WelcomePage open(long timeout) {
        return PageFactory.newInstance(
                WelcomePage.class,
                "http://the-internet.herokuapp.com",
                Duration.of(timeout, SECONDS));
    }


    public CheckboxesPage clickCheckboxesLink() {
        // TODO: move both of these to a dedicated test
        logger.trace("Showing example use of the logger in BasePage");
        // example use of BasePage visibility (not actually required here)
        visibility.forceVisible(checkboxesLink);

        checkboxesLink.click();
        return PageFactory.newInstance(
                CheckboxesPage.class, Duration.of(15, SECONDS));
    }


    public DragAndDropPage clickDragAndDropLink() {
        dragAndDropLink.click();
        return PageFactory.newInstance(DragAndDropPage.class);
    }


    public DynamicLoadingPage clickDynamicLoading() {
        dynamicLoadingLink.click();
        return PageFactory.newInstance(DynamicLoadingPage.class);
    }


    public HoversPage clickHoversLink() {
        hoversLink.click();
        return PageFactory.newInstance(HoversPage.class);
    }


    public JavaScriptAlertsPage clickJavascriptAlertsLink() {
        javascriptAlertsLink.click();
        return PageFactory.newInstance(JavaScriptAlertsPage.class);
    }


    public KeyPressesPage clickKeyPressesLink() {
        keyPressesLink.click();
        return PageFactory.newInstance(KeyPressesPage.class);
    }


    public SortableDataTablesPage clickSortableDataTablesLink() {
        sortableDataTablesLink.click();
        return PageFactory.newInstance(SortableDataTablesPage.class);
    }
}
