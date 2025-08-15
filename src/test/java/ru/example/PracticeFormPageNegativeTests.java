package ru.example;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import ru.example.constants.TestTags;
import ru.example.data.PracticeFormTestData;
import ru.example.pages.PracticeFormPage;

@Tags({
        @Tag(TestTags.REGRESS),
        @Tag(TestTags.SMOKE)
})
@Epic("Формы")
@Feature("Practice Form")
@DisplayName("Practice Form (негативные тесты)")
public class PracticeFormPageNegativeTests extends BaseTest {
    private static final String invalidMobile = "123"; // should have 10 digits
    private static final String invalidEmail = "123@123"; // should have domain

    private final PracticeFormPage page = new PracticeFormPage();
    private final PracticeFormPage.ResultModal resultModal = new PracticeFormPage.ResultModal();

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Отображается ошибка, если обязательные поля не заполнены")
    @Test
    void emptyRequiredFieldsShowsErrorTest() {
        PracticeFormTestData data = new PracticeFormTestData();

        page.open()
                .setEmail(data.getEmail())
                .setCurrentAddress(data.getCurrentAddress())
                .clickSubmit();

        page.shouldHaveInvalidFirstNameField()
                .shouldHaveInvalidLastNameField()
                .shouldHaveInvalidGenderRadioGroup()
                .shouldHaveInvalidMobileField();
        resultModal.shouldNotBeVisible();
    }

    @DisplayName("Отображается ошибка, если не заполнено поле 'First Name'")
    @Disabled("В разработке")
    @Test
    void emptyFirstNameShowsErrorTest() {
    }

    @DisplayName("Отображается ошибка, если не заполнено поле 'Last Name'")
    @Disabled("В разработке")
    @Test
    void emptyLastNameShowsErrorTest() {
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При вводе номера телефона в некорректном формате отображается ошибка")
    @Test
    void invalidMobileFormatShowsErrorTest() {
        page.open()
                .setMobile(invalidMobile)
                .clickSubmit();

        page.shouldHaveInvalidMobileField();
        resultModal.shouldNotBeVisible();
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При вводе электронной почты в некорректном формате отображается ошибка")
    @Test
    void invalidEmailFormatShowsErrorTest() {
        page.open()
                .setEmail(invalidEmail)
                .clickSubmit();

        page.shouldHaveInvalidEmailField();
        resultModal.shouldNotBeVisible();
    }
}
