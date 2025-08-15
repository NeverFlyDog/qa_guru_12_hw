package ru.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.example.utils.AllureAttachments;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public abstract class BaseTest {

    @BeforeAll
    static void configureSelenide() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

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
