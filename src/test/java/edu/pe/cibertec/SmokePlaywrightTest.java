package edu.pe.cibertec;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import org.junit.jupiter.api.Test;

import  static  org.assertj.core.api.Assertions.assertThat;
public class SmokePlaywrightTest {

    @Test
    void abrirElSitioYleeElTitulo(){
        BrowseTheWebWithPlaywright ability = BrowseTheWebWithPlaywright.withDefaultConfiguration();
        try{
            ability.getPage().navigate("/");
            String titulo = ability.getPage().title();
            System.out.println(titulo);
            assertThat(titulo).isNotEmpty();
        }
        finally{
            ability.quit();
        }
    }
}
