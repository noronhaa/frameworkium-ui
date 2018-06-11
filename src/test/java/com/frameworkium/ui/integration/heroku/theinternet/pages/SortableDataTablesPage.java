package com.frameworkium.ui.integration.heroku.theinternet.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

public class SortableDataTablesPage extends BasePage<SortableDataTablesPage> {

    @Visible
    @Name("Table 1")
    @FindBy(css = "table#table1")
    private Table table1;

    @Visible
    @Name("Table 2")
    @FindBy(css = "table#table2")
    private Table table2;


    public List<String> getTable1ColumnContents(String colHeader) {
        return getColumnContents(table1, colHeader);
    }


    public List<String> getTable2ColumnContents(String colHeader) {
        return getColumnContents(table2, colHeader);
    }


    public SortableDataTablesPage sortTable2ByColumnName(String colHeader) {
        sortTableByColumnName(table2, colHeader);
        return this;
    }


    private SortableDataTablesPage sortTableByColumnName(Table table, String colHeader) {
        table.getHeadings()
                .get(table.getHeadingsAsString().indexOf(colHeader))
                .click();
        return this;
    }


    private List<String> getColumnContents(Table table, String colHeader) {
        int colIndex = table.getHeadingsAsString().indexOf(colHeader);
        return table.getColumnsAsString().get(colIndex);
    }

}
