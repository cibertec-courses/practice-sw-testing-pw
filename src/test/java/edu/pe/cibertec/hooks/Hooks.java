package edu.pe.cibertec.hooks;

import com.microsoft.playwright.Page;
import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private static final Path EVIDENCE_DIR = Paths.get("target", "evidence");
    private PlaywrightCast cast;

    @Before(order = 0)
    public void preparaEscenario(Scenario scenario) {
        cast = new PlaywrightCast();
        OnStage.setTheStage(cast);
        System.out.println("Preparando Escenario -> " + scenario.getName());
    }

    @After(order = 1)
    public void capturarEvidencie(Scenario scenario) {
        if(!scenario.isFailed()) {
            return;
        }
        try{
            Files.createDirectories(EVIDENCE_DIR);
            for (Actor actor: cast.getActors()) {
                Page page = BrowseTheWebWithPlaywright.as(actor).getPage();
                if(page == null || page.isClosed()){
                    continue;
                }
                String nombreArchivo = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                        + "_" + actor.getName()+ "_"+ System.currentTimeMillis() + ".png";
                Path destino = EVIDENCE_DIR.resolve(nombreArchivo);

                byte[] imagen = page.screenshot(
                        new Page.ScreenshotOptions().setPath(destino).setFullPage(true)
                );

                scenario.attach(imagen,"image/png", "Fallo - "+ actor.getName());

                Serenity.recordReportData()
                        .withTitle("Evidencia del fallo - "+ actor.getName())
                        .downloadable()
                        .fromFile(destino);

            }
        } catch (Exception e) {
            System.err.println("No se pudo capturar la evidencia: " + e.getMessage());
        }

    }
    @After(order = 0)
    public void cerrarEscenario(Scenario scenario) {
        if(cast != null) {
            cast.quitAll();
        }
        OnStage.drawTheCurtain();
        System.out.println("Cerrando Escenario -> " + scenario.getName());
    }

}
