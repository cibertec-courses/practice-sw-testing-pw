package edu.pe.cibertec.tasks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import edu.pe.cibertec.interactions.Click;
import edu.pe.cibertec.interactions.Enter;
import edu.pe.cibertec.ui.HomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SearchForProduct implements Task {
    private String termino;

    public SearchForProduct(String termino) {
        this.termino = termino;
    }

    @Override
    @Step("{0} busca el prodcto '#termino'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(termino, HomePage.SEARCH_INPUT)
        );
        Page page = BrowseTheWebWithPlaywright.as(actor).getPage();
        page.waitForResponse(
                response -> response.url().contains("/products/search")
                && response.status() == 200,
                () -> actor.attemptsTo(Click.on(HomePage.SEARCH_SUBMIT))
        );


    }

    public static SearchForProduct named(String termino) {
        return instrumented(SearchForProduct.class, termino);
    }
}


