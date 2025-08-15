package ru.example;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import ru.example.constants.TestTags;
import ru.example.data.CalendarDate;
import ru.example.data.PracticeFormTestData;
import ru.example.pages.PracticeFormPage;

@Epic("Формы")
@Feature("Practice Form")
@DisplayName("Practice Form (позитивные тесты)")
public class PracticeFormPagePositiveTests extends BaseTest {
    private final PracticeFormPage page = new PracticeFormPage();
    private final PracticeFormPage.ResultModal resultModal = new PracticeFormPage.ResultModal();

    private PracticeFormTestData data;

    @BeforeEach
    public void initTestData() {
        data = new PracticeFormTestData();
    }

    @Tag(TestTags.REGRESS)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Заполнение всех полей формы и проверка результата")
    @Test
    void fillAllFieldsTest() {
        page.open()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setEmail(data.getEmail())
                .selectGender(data.getGender())
                .setMobile(data.getMobile())
                .selectDateOfBirth(data.getDateOfBirth())
                .selectSubjects(data.getSubjects())
                .selectHobbies(data.getHobbies())
                .uploadPicture(data.getFilename())
                .setCurrentAddress(data.getCurrentAddress())
                .selectState(data.getState())
                .selectCity(data.getCity())
                .clickSubmit();

        resultModal.shouldBeVisible()
                .shouldHaveTitle()
                .shouldHaveHeaders()
                .shouldHaveStudentName(data.getFirstName(), data.getLastName())
                .shouldHaveStudentEmail(data.getEmail())
                .shouldHaveGender(data.getGender())
                .shouldHaveMobile(data.getMobile())
                .shouldHaveDateOfBirth(data.getDateOfBirth())
                .shouldHaveSubjects(data.getSubjects())
                .shouldHaveHobbies(data.getHobbies())
                .shouldHavePicture(data.getFilename())
                .shouldHaveAddress(data.getCurrentAddress())
                .shouldHaveStateAndCity(data.getState(), data.getCity())
                .clickClose();

        resultModal.shouldNotBeVisible();
    }

    @Tags({
            @Tag(TestTags.REGRESS),
            @Tag(TestTags.SMOKE)
    })
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Заполнение обязательных полей формы и проверка результата")
    @Test
    void fillRequiredFieldsTest() {
        page.open()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .selectGender(data.getGender())
                .setMobile(data.getMobile())
                .clickSubmit();

        resultModal.shouldBeVisible()
                .shouldHaveTitle()
                .shouldHaveHeaders()
                .shouldHaveStudentName(data.getFirstName(), data.getLastName())
                .shouldHaveGender(data.getGender())
                .shouldHaveMobile(data.getMobile())
                .shouldHaveDateOfBirth(CalendarDate.now());

        resultModal.shouldHaveStudentEmail("")
                .shouldHaveSubjects("")
                .shouldHaveHobbies("")
                .shouldHavePicture("")
                .shouldHaveAddress("")
                .shouldHaveStateAndCity("", "")
                .clickClose();

        resultModal.shouldNotBeVisible();
    }
}
