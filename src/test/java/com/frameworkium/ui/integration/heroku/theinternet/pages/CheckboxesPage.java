package com.frameworkium.ui.integration.heroku.theinternet.pages;

import com.frameworkium.ui.ExtraExpectedConditions;
import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;

import java.util.List;
import java.util.stream.Collectors;

public class CheckboxesPage extends BasePage<CheckboxesPage> {

    @Visible(checkAtMost = 2)
    @Name("All checkboxes")
    @FindBy(css = "form input[type='checkbox']")
    private List<CheckBox> allCheckboxes;


    public CheckboxesPage checkAllCheckboxes() {

        allCheckboxes.forEach(CheckBox::select);

        // TODO: move to dedicated test
        // not required for this test, just testing it doesn't fail
        wait.until(ExtraExpectedConditions.jQueryAjaxDone());

        return this;
    }


    public List<Boolean> getAllCheckboxCheckedStatus() {

        return allCheckboxes.stream()
                .map(CheckBox::isSelected)
                .collect(Collectors.toList());
    }

}
