package edu.pe.cibertec.hooks;

import edu.pe.cibertec.abilities.BrowseTheWebWithPlaywright;
import edu.pe.cibertec.utils.Environment;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;

import java.util.ArrayList;
import java.util.List;

public class PlaywrightCast extends Cast {
    private final List<Actor> actors = new ArrayList<>();

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {
        Actor actor = Actor.named(actorName);

        for (Ability ability : abilities) {
            actor.can(ability);
        }

        actor.can(BrowseTheWebWithPlaywright.withDefaultConfiguration());
        actors.add(actor);
        return actor;
    }

    @Override
    public List<Actor> getActors() {
        return actors;
    }

    public void quitAll() {
        actors.forEach(actor -> {
            BrowseTheWebWithPlaywright ability =
                    actor.abilityTo(BrowseTheWebWithPlaywright.class);
            if (ability != null) {
                ability.quit();
            }
        });
        actors.clear();
    }


}
