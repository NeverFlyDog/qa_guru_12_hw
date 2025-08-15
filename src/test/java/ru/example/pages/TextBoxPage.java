package ru.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {
    private static final String RESULT_NAME_LABEL = "Name";
    private static final String RESULT_EMAIL_LABEL = "Email";
    private static final String RESULT_CURRENT_ADDRESS_LABEL = "Current Address";
    private static final String RESULT_PERMANENT_ADDRESS_LABEL = "Permananet Address";

    private final SelenideElement userNameField = $("#userName");
    private final SelenideElement emailField = $("#userEmail");
    private final SelenideElement currentAddressField = $("#currentAddress");
    private final SelenideElement permanentAddressField = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputBlock = $("#output");

    @Step("Открываем страницу формы")
    public TextBoxPage open() {
        Selenide.open("/text-box");

        $("h1").shouldHave(text("Text Box"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Заполняем поле 'Full Name' значением: {userName}")
    public TextBoxPage setUserName(String userName) {
        userNameField.setValue(userName);
        return this;
    }

    @Step("Заполняем поле 'Email' значением: {email}")
    public TextBoxPage setEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    @Step("Заполняем поле 'Current Address' значением: {currentAddress}")
    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressField.setValue(currentAddress);
        return this;
    }

    @Step("Заполняем поле 'Permanent Address' значением: {permanentAddress}")
    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressField.setValue(permanentAddress);
        return this;
    }

    @Step("Нажимаем кнопку 'Submit'")
    public void clickSubmit() {
        submitButton.click();
    }

    @Step("Проверяем значение в поле 'Name'")
    public TextBoxPage shouldHaveUserName(String userName) {
        shouldHaveResult(RESULT_NAME_LABEL, userName);
        return this;
    }

    @Step("Проверяем значение в поле 'Email'")
    public TextBoxPage shouldHaveEmail(String email) {
        shouldHaveResult(RESULT_EMAIL_LABEL, email);
        return this;
    }

    @Step("Проверяем значение в поле 'Current Address'")
    public TextBoxPage shouldHaveCurrentAddress(String currentAddress) {
        shouldHaveResult(RESULT_CURRENT_ADDRESS_LABEL, currentAddress);
        return this;
    }

    @Step("Проверяем значение в поле 'Permanent Address'")
    public TextBoxPage shouldHavePermanentAddress(String permanentAddress) {
        shouldHaveResult(RESULT_PERMANENT_ADDRESS_LABEL, permanentAddress);
        return this;
    }

    private void shouldHaveResult(String key, String value) {
        outputBlock.$$("p")
                .findBy(text(key))
                .shouldHave(text(value));
    }
}
