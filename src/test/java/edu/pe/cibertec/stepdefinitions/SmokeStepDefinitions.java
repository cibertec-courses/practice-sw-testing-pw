package edu.pe.cibertec.stepdefinitions;

import com.microsoft.playwright.Page;
import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import edu.pe.cibertec.questions.DisplayedProducts;
import edu.pe.cibertec.tasks.OpenTheStore;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class SmokeStepDefinitions {

    @Dado("que {word} quiere comprar herramientas")
    public void quiereComprarHerramientas(String word) {
        OnStage.theActorCalled(word);
    }

    @Cuando("abre la pagina principal de la tienda")
    public void abreLaPaginaPrincipalDeLaTienda() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenTheStore.homePage()
        );
    }

    @Entonces("deberia ver productos disponinles en el catalogo")
    public void deberiaVerProductosDisponinlesEnElCatalogo() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("la cantidad de productos",
                        DisplayedProducts.count(), greaterThan(0))
        );
    }
}
