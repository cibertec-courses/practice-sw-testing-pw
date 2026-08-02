package edu.pe.cibertec.stepdefinitions;

import com.microsoft.playwright.Page;
import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeStepDefinitions {

    @Dado("que {word} quiere comprar herramientas")
    public void quiereComprarHerramientas(String word) {
        OnStage.theActorCalled(word);
    }

    @Cuando("abre la pagina principal de la tienda")
    public void abreLaPaginaPrincipalDeLaTienda() {
        Page page = BrowseTheWebWithPlaywright.as(OnStage.theActorInTheSpotlight()).getPage();
        page.navigate("/");
        page.waitForSelector("[data-test='product-name']");
    }

    @Entonces("deberia ver productos disponinles en el catalogo")
    public void deberiaVerProductosDisponinlesEnElCatalogo() {
        Page page = BrowseTheWebWithPlaywright.as(OnStage.theActorInTheSpotlight()).getPage();
        int cantidad = page.locator("[data-test='product-name']").count();
        System.out.println("Cantidad de productos: " + cantidad);
        assertThat(cantidad).isGreaterThan(0);
    }
}
