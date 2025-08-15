package ru.example;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import ru.example.constants.TestTags;
import ru.example.data.TextBoxTestData;
import org.junit.jupiter.api.Test;
import ru.example.pages.TextBoxPage;

@Epic("Формы")
@Feature("Text Box")
@DisplayName("Text Box (позитивные тесты)")
public class TextBoxTests extends BaseTest {
    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Tag(TestTags.REGRESS)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Заполнение всех полей формы и проверка результата")
    @Test
    void fillAllFieldsTest() {
        TextBoxTestData data = new TextBoxTestData();

        textBoxPage
                .open()
                .setUserName(data.getUserName())
                .setEmail(data.getEmail())
                .setCurrentAddress(data.getCurrentAddress())
                .setPermanentAddress(data.getPermanentAddress())
                .clickSubmit();

        textBoxPage
                .shouldHaveUserName(data.getUserName())
                .shouldHaveEmail(data.getEmail())
                .shouldHaveCurrentAddress(data.getCurrentAddress())
                .shouldHavePermanentAddress(data.getPermanentAddress());
    }
}
