package ru.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.example.utils.AllureAttachments;
import ru.example.utils.Config;

import java.util.Map;

public abstract class BaseTest {

    @BeforeAll
    static void configureSelenide() {
        Configuration.browser = Config.BROWSER;
        Configuration.browserVersion = Config.BROWSER_VERSION;
        Configuration.browserSize = Config.BROWSER_SIZE;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = Config.REMOTE_URL;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEachTest() {
        attachArtifacts();
        Selenide.closeWebDriver();
    }

    private void attachArtifacts() {
        AllureAttachments.attachScreenshot("Final state");
        AllureAttachments.attachVideo();
        AllureAttachments.attachPageSource();
        AllureAttachments.attachBrowserConsoleLogs();
    }
}
