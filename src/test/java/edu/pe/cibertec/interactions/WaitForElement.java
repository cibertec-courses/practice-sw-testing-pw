package edu.pe.cibertec.interactions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class WaitForElement implements Interaction {

    private final String selector;

    public WaitForElement(String selector) {
        this.selector = selector;
    }


    @Override
    @Step("{0} espera a que aparezca '#selector'")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWebWithPlaywright.as(actor).getPage()
                .waitForSelector(selector, new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE));
    }

    public static WaitForElement visible(String selector) {
        return instrumented(WaitForElement.class, selector);
    }

}
