package ru.example.pages.components;

import com.codeborne.selenide.SelenideElement;
import ru.example.data.CalendarDate;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement element;

    public CalendarComponent(SelenideElement element) {
        this.element = element;
    }

    public void setDate(CalendarDate date) {
        $(".react-datepicker__year-select").selectOption(date.year());
        $(".react-datepicker__month-select").selectOption(date.month());

        String formattedDay = String.format("%03d", date.day()); // 1 -> "001"
        $(".react-datepicker__day--" + formattedDay + ":not(.react-datepicker__day--outside-month)").click();
    }

    public void click() {
        element.click();
    }
}
