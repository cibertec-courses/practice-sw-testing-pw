package edu.pe.cibertec.abilities;

import com.microsoft.playwright.*;
import edu.pe.cibertec.utils.Environment;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import javax.xml.stream.events.EndElement;
import java.util.Collections;

public class BrowseTheWebWithPlaywright implements Ability {
    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext context;
    private final Page page;

    public BrowseTheWebWithPlaywright(Playwright playwright, Browser browser, BrowserContext context, Page page) {
        this.playwright = playwright;
        this.browser = browser;
        this.context = context;
        this.page = page;
    }


    public static BrowseTheWebWithPlaywright withDefaultConfiguration() {
        Playwright playwright = Playwright.create();

        BrowserType browserType = switch (Environment.browser()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };

        Browser browse = browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(Environment.headless())
                        .setSlowMo(Environment.sloMo())
                        .setArgs(Collections.singletonList("--start-maximized"))
        );

        BrowserContext context = browse.newContext(
                new Browser.NewContextOptions()
                        .setBaseURL(Environment.baseUrl())
                        .setViewportSize(1920, 1080)
        );
        context.setDefaultTimeout(Environment.timeout());

        Page page = context.newPage();

        return new BrowseTheWebWithPlaywright(playwright, browse, context, page);

    }

    public static BrowseTheWebWithPlaywright as(Actor actor) {
        return actor.abilityTo(BrowseTheWebWithPlaywright.class);
    }

    public Page getPage() {
        return page;
    }

    public BrowserContext getContext() {
        return context;
    }

    public byte[] takeScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(false));
    }

    public void quit(){
        try{
            if( page != null && !page.isClosed()){
                page.close();
            }
            if(context != null){
                context.close();
            }
            if(browser != null){
                browser.close();
            }
        }finally{
            if(playwright != null){
                playwright.close();
            }
        }
    }

    @Override
    public String toString() {
        return "navegar con playwright ("+ Environment.browser() + ")";
    }

}
