package edu.pe.cibertec.stepdefinitions;

import edu.pe.cibertec.questions.DisplayedProducts;
import edu.pe.cibertec.questions.SearchOutcome;
import edu.pe.cibertec.tasks.SearchForProduct;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    @Cuando("busca el producto {string}")
    public void buscaElProducto(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SearchForProduct.named(producto)
        );
    }

    @Entonces("deberia ver resultados que contienen {string}")
    public void deberiaVerResultadosQueContienen(String esperado) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("La lista de resultados",
                        DisplayedProducts.names(),
                        hasSize(greaterThan(0))
                ),
                seeThat("cada resultado",
                        DisplayedProducts.names(),
                        everyItem(containsString(esperado)))
        );
    }

    @Entonces("deberia ver el mensaje no hay resultados")
    public void deberiaVerResultadosNoHayResultados() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el mensaje de sin resultados"
                        , SearchOutcome.hasNoResults(), is(true))
        );
    }
}
