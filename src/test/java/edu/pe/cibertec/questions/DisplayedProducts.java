package edu.pe.cibertec.questions;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import edu.pe.cibertec.ui.HomePage;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class DisplayedProducts {

    private DisplayedProducts() {
    }

    public static Question<Integer> count() {
        return actor -> BrowseTheWebWithPlaywright.as(actor)
                .getPage()
                .locator(HomePage.PRODUCT_NAME)
                .count();
    }

    public static Question<List<String>> names() {
        return actor -> BrowseTheWebWithPlaywright.as(actor)
                .getPage()
                .locator(HomePage.PRODUCT_NAME)
                .allTextContents();
    }

}
