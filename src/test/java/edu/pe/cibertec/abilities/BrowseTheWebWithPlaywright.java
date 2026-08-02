package edu.pe.cibertec.abilities;

import com.microsoft.playwright.*;
import edu.pe.cibertec.utils.Environment;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import javax.xml.stream.events.EndElement;
import java.util.Collections;

public class BrowseTheWebWithPlaywright implements Ability {
    private static Playwright playwright;
    private static Browser browser;
    private final BrowserContext context;
    private final Page page;


    public BrowseTheWebWithPlaywright(BrowserContext context, Page page) {
        this.context = context;
        this.page = page;
    }

    private static synchronized Browser sharedBrowser() {
        if (browser == null) {
            playwright = Playwright.create();

            BrowserType browserType = switch (Environment.browser()) {
                case "firefox" -> playwright.firefox();
                case "webkit" -> playwright.webkit();
                default -> playwright.chromium();
            };
            browser = browserType.launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Environment.headless())
                            .setSlowMo(Environment.sloMo())
                            .setArgs(Collections.singletonList("--start-maximized"))
            );

            Runtime.getRuntime().addShutdownHook(new Thread(BrowseTheWebWithPlaywright::shutdown));
        }
        return browser;
    }

    public static synchronized void shutdown() {
        try {
            if (browser != null) {
                browser.close();
                browser = null;
            }
        } finally {
            if (playwright != null) {
                playwright.close();
                playwright = null;
            }
        }

    }

    public static BrowseTheWebWithPlaywright withDefaultConfiguration() {


        BrowserContext context = sharedBrowser().newContext(
                new Browser.NewContextOptions()
                        .setBaseURL(Environment.baseUrl())
                        .setViewportSize(1920, 1080)
        );
        context.setDefaultTimeout(Environment.timeout());

        Page page = context.newPage();

        return new BrowseTheWebWithPlaywright(context, page);

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

    public void quit() {
       if (context != null) {
           context.close();
       }
    }

    @Override
    public String toString() {
        return "navegar con playwright (" + Environment.browser() + ")";
    }

}
