package com.frameworkium.ui.integration.github.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.integration.github.pages.components.HeaderComponent;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class ExplorePage extends BasePage<ExplorePage> {

    @Visible
    @Name("Header")
    private HeaderComponent header;

    // Not (yet) used, but ensures we are indeed on the explore page
    @Visible
    private WebElement trending;

    public static ExplorePage open() {
        return new ExplorePage().get("https://github.com/explore");
    }

    public HeaderComponent theHeader() {
        return header;
    }
}
