package ru.example.data;

import net.datafaker.Faker;

public class TextBoxTestData {
    private static final Faker faker = new Faker();

    private final String userName;
    private final String email;
    private final String currentAddress;
    private final String permanentAddress;

    public TextBoxTestData() {
        this.userName = faker.internet().username();
        this.email = faker.internet().emailAddress();
        this.currentAddress = faker.address().fullAddress();
        this.permanentAddress = faker.address().fullAddress();
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }
}
