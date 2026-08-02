package edu.pe.cibertec.interactions;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Enter implements Interaction {
    private String texto;
    private String selector;
    public Enter(String texto, String selector) {
        this.texto = texto;
        this.selector = selector;
    }


    @Override
    @Step("{0} escribe '#texto' en '#selector'")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWebWithPlaywright.as(actor).getPage().fill(selector, texto);
    }

    public static Enter theValue(String texto, String selector) {
        return instrumented(Enter.class, texto, selector);
    }
}
