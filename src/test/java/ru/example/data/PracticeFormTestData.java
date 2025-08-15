package ru.example.data;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

public class PracticeFormTestData {
    private static final String[] GENDERS = {"Male", "Female", "Other"};
    private static final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private static final String[] SUBJECTS = {
            "Hindi", "English", "Maths", "Physics", "Chemistry", "Biology", "Computer Science", "Commerce",
            "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics"
    };
    private static final String[] FILENAMES = {"test.jpg", "test card.jpg", "test-button.png"};
    private static final Map<String, List<String>> stateToCities = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final String mobile;
    private final String currentAddress;
    private final String state;
    private final String city;
    private final String[] hobbies;
    private final String[] subjects;
    private final CalendarDate dateOfBirth;
    private final String filename;

    public PracticeFormTestData() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.gender = faker.options().option(GENDERS);
        this.dateOfBirth = generateDateOfBirth();
        this.email = faker.internet().emailAddress();
        this.mobile = faker.phoneNumber().subscriberNumber(10);
        this.currentAddress = faker.address().fullAddress();
        this.filename = faker.options().option(FILENAMES);

        String[] stateCity = getRandomStateAndCity();
        this.state = stateCity[0];
        this.city = stateCity[1];

        this.hobbies = getRandomSubset(HOBBIES);
        this.subjects = getRandomSubset(SUBJECTS);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public CalendarDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public String getFilename() {
        return filename;
    }

    private static CalendarDate generateDateOfBirth() {
        int age = random.nextInt(18, 100);

        LocalDate now = LocalDate.now();
        int year = now.getYear() - age;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(YearMonth.of(year, month).lengthOfMonth()) + 1;

        LocalDate birthDate = LocalDate.of(year, month, day);
        String monthName = birthDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return new CalendarDate(String.valueOf(year), monthName, day);
    }

    private static String[] getRandomSubset(String[] array) {
        int count = random.nextInt(1, array.length + 1);

        String[] result = new String[count];
        boolean[] used = new boolean[array.length];

        for (int i = 0; i < count; i++) {
            int index;
            do {
                index = random.nextInt(array.length);
            } while (used[index]);

            used[index] = true;
            result[i] = array[index];
        }
        return result;
    }

    private static String[] getRandomStateAndCity() {
        List<String> states = new ArrayList<>(stateToCities.keySet());
        String randomState = states.get(random.nextInt(states.size()));

        List<String> cities = stateToCities.get(randomState);
        String randomCity = cities.get(random.nextInt(cities.size()));

        return new String[] {randomState, randomCity};
    }
}