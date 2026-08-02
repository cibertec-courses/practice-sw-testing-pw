package edu.pe.cibertec.questions;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import edu.pe.cibertec.ui.HomePage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.htmlunit.xpath.operations.Bool;

public class SearchOutcome {
    private SearchOutcome(){}

    public static Question<Boolean> hasNoResults(){
        return actor -> BrowseTheWebWithPlaywright.as(actor)
                .getPage()
                .locator(HomePage.NO_RESULTS)
                .isVisible();
    }
}
