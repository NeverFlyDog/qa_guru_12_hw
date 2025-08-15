package ru.example.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {
    private final SelenideElement root = $(".modal-content");
    private final SelenideElement title = $(".modal-title");
    private final SelenideElement closeButton = $("#closeLargeModal");

    public void shouldBeVisible() {
        root.shouldBe(visible);
    }

    public void shouldNotBeVisible() {
        root.shouldNotBe(visible);
    }

    public void shouldHaveTitle(String expectedTitle) {
        title.shouldHave(text(expectedTitle));
    }

    public void shouldHaveHeaders(String... expectedHeaders) {
        ElementsCollection headers = root.$$("table tr").first().$$("th");
        for (int i = 0; i < expectedHeaders.length; i++) {
            headers.get(i).shouldHave(text(expectedHeaders[i]));
        }
    }

    public void shouldHaveField(String label, String expectedValue) {
        SelenideElement valueElement = root.$$("table tr")
                .findBy(text(label))
                .$$("td")
                .get(1);

        if (expectedValue.trim().isEmpty()) {
            valueElement.shouldBe(empty);
        } else {
            valueElement.shouldHave(exactText(expectedValue));
        }
    }

    public void clickClose() {
        closeButton.click();
        root.should(disappear);

        // Sanity check
        // sleep(Configuration.timeout);
        // $(".modal-content").should(appear);
    }
}
