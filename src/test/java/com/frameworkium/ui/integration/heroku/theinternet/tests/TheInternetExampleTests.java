package com.frameworkium.ui.integration.heroku.theinternet.tests;

import com.frameworkium.ui.integration.heroku.theinternet.pages.*;
import com.frameworkium.ui.tests.BaseUITest;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TheInternetExampleTests extends BaseUITest {

    @Test(description = "Checkboxes")
    public void checkBoxes() {

        final int timeout = 15;
        CheckboxesPage checkboxesPage = WelcomePage.open(timeout)
                .clickCheckboxesLink()
                .checkAllCheckboxes();

        // Assert that all checkboxes are checked
        assertThat(checkboxesPage.getAllCheckboxCheckedStatus())
                .named("check status of checkboxes")
                .doesNotContain(false);
    }

    @Test(description = "Drag and Drop")
    public void dragAndDrop() {

        List<String> headings = WelcomePage.open()
                .clickDragAndDropLink()
                .dragAontoB()
                .getListOfHeadings();

        assertThat(headings)
                .named("Order of headings")
                .containsExactly("B", "A");
    }

    @Test(description = "Dynamic loading")
    public void dynamicLoading() {

        DynamicLoadingExamplePage dynamicLoadingPage =
                WelcomePage.open()
                        .clickDynamicLoading()
                        .clickExample2()
                        .clickStart().
                        waitForElementToBeDisplayed();

        // Assert that the element is indeed displayed
        assertThat(dynamicLoadingPage.isElementDisplayed())
                .named("element visibility")
                .isTrue();
    }

    @Test(description = "Hovers")
    public void hovers() {

        // Navigate to the hovers page
        HoversPage hoversPage = WelcomePage.open().clickHoversLink();

        // Confirm that the caption under the first figure contains expected text
        assertThat(hoversPage.getFirstFigureCaption()).contains("name: user1");

    }

    @Test(description = "Javascript Alerts")
    public void javascriptAlerts() {

        // Navigate to the javascript alerts page
        JavaScriptAlertsPage javascriptAlerts =
                WelcomePage.open()
                        .clickJavascriptAlertsLink()
                        .clickAlertButtonAndAccept();

        assertThat(javascriptAlerts.getResultText())
                .isEqualTo("You successfuly clicked an alert");
    }

    @Test(description = "Key Presses")
    public void keypresses() {

        // Navigate to the key presses page
        KeyPressesPage keyPressesPage = WelcomePage
                .open()
                .clickKeyPressesLink()
                .enterKeyPress(Keys.ENTER);

        assertThat(keyPressesPage.getResultText())
                .isEqualTo("You entered: " + Keys.ENTER.name());
    }

    @Test(description = "Table Manipulation & Validation")
    public void sortDataTable() {

        // Navigate to the sortable data tables page
        SortableDataTablesPage sortableDataTablesPage =
                WelcomePage.open().clickSortableDataTablesLink();

        // Assert that Table 1 contains "http://www.jdoe.com" in the web site column
        assertThat(sortableDataTablesPage.getTable1ColumnContents("Web Site"))
                .contains("http://www.jdoe.com");

        // Sort Table 2 by last name column
        sortableDataTablesPage.sortTable2ByColumnName("Last Name");

        List<String> lastNameColumn =
                sortableDataTablesPage.getTable2ColumnContents("Last Name");

        // Confirm that the column is then ordered by the last name
        assertThat(lastNameColumn).isOrdered();

        // Confirm that "Bach" is then the first surname in table 2
        assertThat(lastNameColumn.get(0)).isEqualTo("Bach");
    }

}
