package sigulia.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import sigulia.pages.components.CalendarComponent;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistationFormPage {

    CalendarComponent calendar = new CalendarComponent();

    // locators
    SelenideElement firstName = $("#firstName"),
                    lastName = $("#lastName"),
                    userEmail = $("#userEmail"),
                    mobileNumber = $("#userNumber"),
                    gender = $("#genterWrapper"),
                    subjects = $("#subjectsInput"),
                    subjectsOptions = $(".subjects-auto-complete__option"),
                    address = $("#currentAddress"),
                    city = $("#city"),
                    state = $("#state"),
                    stateCityWrapper = $("#stateCity-wrapper"),
                    submitButton = $("#submit"),
                    table = $(".table-responsive"),
                    picture = $("#uploadPicture"),
                    hobbies = $("#hobbiesWrapper"),
                    dateCalendar = $("#dateOfBirthInput");



    @Attachment(value = "Скриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot () {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "PageSource", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource () {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    // actions
    public RegistationFormPage openPage () {
        open("/automation-practice-form");
        return this;
    }

    public RegistationFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RegistationFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RegistationFormPage setUserEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public RegistationFormPage setMobileNumber(String value) {
        mobileNumber.setValue(value);
        return this;
    }
    public RegistationFormPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    public RegistationFormPage setState(String value) {
        state.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistationFormPage setCity(String value) {
        city.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistationFormPage setGender(String value) {
        gender.$(byText(value)).click();
        return this;
    }


    public RegistationFormPage setPicture (String value) {
        picture.uploadFromClasspath(value);
        return this;
    }

    public RegistationFormPage setSubjects(String value) {
        subjects.sendKeys(value);
        subjectsOptions.click();
        return this;
    }

    public RegistationFormPage setHobbies(String value) {
        hobbies.$(byText(value)).click();
        return this;
    }

    public RegistationFormPage setDateCalendar(String day, String month, String year) {
        dateCalendar.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistationFormPage clickOnSubmitButton() {
        submitButton.click();
        table.shouldBe(visible);
        return this;
    }

    public RegistationFormPage checkResult(String key, String value) {
        table.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }


}
