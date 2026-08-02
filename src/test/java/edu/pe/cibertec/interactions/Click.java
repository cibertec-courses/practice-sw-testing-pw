package edu.pe.cibertec.interactions;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Click implements Interaction {
    private final String selector;

    public Click(String selector) {
        this.selector = selector;
    }

    @Override
    @Step("{0} hace click en '#selector'")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWebWithPlaywright.as(actor).getPage().click(selector);
    }

    public static Click on(String selector) {
        return instrumented(Click.class, selector);
    }
}
