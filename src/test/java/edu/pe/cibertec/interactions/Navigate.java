package edu.pe.cibertec.interactions;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Navigate implements Interaction{

    private final String path;

    public Navigate(String path) {
        this.path = path;
    }

    @Override
    @Step("{0} navega a '#path'")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWebWithPlaywright.as(actor).getPage().navigate(path);
    }

    public static Navigate to(String path) {
        return instrumented(Navigate.class, path);
    }




}
