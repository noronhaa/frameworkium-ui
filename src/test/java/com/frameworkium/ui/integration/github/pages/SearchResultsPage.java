package com.frameworkium.ui.integration.github.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.List;
import java.util.stream.Stream;

public class SearchResultsPage extends BasePage<SearchResultsPage> {

    // Check the visibility of at most 2 just to confirm we are on the page
    @Visible(checkAtMost = 2)
    @Name("Repository Links")
    @FindBy(css = "ul.repo-list h3 > a")
    private List<Link> repoLinks;

    /**
     * Unlike returning a {@link List}, a {@link Stream} is lazy, therefore
     * not every repo link on the page will necessarily be access each time
     * this method is called, potentially speeding up the test.
     *
     * @return a {@link Stream} of repository names.
     */
    public Stream<String> getRepoNames() {
        return repoLinks.stream()
                .map(Link::getText);
    }
}
