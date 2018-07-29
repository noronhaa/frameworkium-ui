package com.frameworkium.ui.integration.github.tests;

import com.frameworkium.ui.integration.github.pages.ExplorePage;
import com.frameworkium.ui.integration.github.pages.HomePage;
import com.frameworkium.ui.tests.BaseUITest;
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

public class ComponentExampleTest extends BaseUITest {

    @Test(description = "Simple test showing the use of components")
    public final void component_example_test() {

        // Go directly to the explore page, home page is tested elsewhere
        ExplorePage explorePage = ExplorePage.open();

        // not a great assertion, improving this is an exercise for the reader
        assertThat(explorePage.getTitle()).isEqualTo("Explore · GitHub");

        // Search for "Selenium" and check SeleniumHQ/selenium is one of the returned repos.
        boolean isTheSeleniumRepoDisplayed =
                explorePage.with().theHeader()
                        .search("Selenium")
                        .getRepoNames()
                        .anyMatch("SeleniumHQ/selenium"::equals);

        assertThat(isTheSeleniumRepoDisplayed).isTrue();
    }

    @Test(description = "force visible makes hidden element visible")
    public void force_visible_makes_hidden_element_visible() {
        HomePage.open()
                .with().theHeader()
                .testForceVisible();
    }

    @Test(dependsOnMethods = {"force_visible_makes_hidden_element_visible"})
    public void ensure_BaseUITest_wait_is_updated_after_browser_reset() {
        // tests bug whereby BasePage.wait wasn't updated after browser reset
        HomePage.open().waitForSomething();
    }
}
