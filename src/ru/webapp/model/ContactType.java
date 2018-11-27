package ru.webapp.model;

public enum ContactType {

    CELLPHONENUMBER("Тел.: "),
    SKYPE("Skype: "),
    MAIL("Почта: "),
    LINKEDIN("LinkedIn: "),
    GITHUB("GitHub: "),
    STACKOVERFLOW("StackOverflow: ");

    String title;

    ContactType(String title) {
        this.title = title;
    }
}
