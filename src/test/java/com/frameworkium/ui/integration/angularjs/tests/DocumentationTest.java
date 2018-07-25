package com.frameworkium.ui.integration.angularjs.tests;

import com.frameworkium.base.RetryFlakyTest;
import com.frameworkium.ui.integration.angularjs.pages.DeveloperGuidePage;
import com.frameworkium.ui.tests.BaseUITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

public class DocumentationTest extends BaseUITest {

    @BeforeMethod
    public void test_configure_browser_before_use() {
        // TODO: figure out why this is here and if still needed with reuseBrowser
        configureBrowserBeforeUse();
        DeveloperGuidePage.open();
    }

    @Test(description =
            "Tests the AngularJS developer documentation and search function",
            retryAnalyzer = RetryFlakyTest.class)

    public void angular_documentation_test() {
        String guideTitle = DeveloperGuidePage.open()
                .searchDeveloperGuide("Bootstrap")
                .clickBootstrapSearchItem()
                .getGuideTitle();

        assertThat(guideTitle)
                .endsWith("Bootstrap");
    }

}
