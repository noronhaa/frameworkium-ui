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

    @Visible
    private WebElement trending;

    public HeaderComponent theHeader() {
        return header;
    }
}
