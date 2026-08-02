package edu.pe.cibertec.tasks;

import edu.pe.cibertec.interactions.Navigate;
import edu.pe.cibertec.interactions.WaitForElement;
import edu.pe.cibertec.ui.HomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTheStore implements Task {

    @Override
    @Step("{0} abre la pagina principal de la tienda Toolshop")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Navigate.to("/")
                , WaitForElement.visible(HomePage.PRODUCT_NAME)
        );
    }

    public static OpenTheStore homePage() {
        return instrumented(OpenTheStore.class);
    }
}
