package ru.webapp.model;

enum ContactType {

    CELLPHONENUMBER("Тел.: ", "+7(921) 855-0482"),
    SKYPE("Skype: ", "grigory.kislin"),
    MAIL("Почта: ", "gkislin@yandex.ru"),
    LINKEDIN("LinkedIn: ", "https://www.linkedin.com/in/gkislin"),
    GITHUB("GitHub: ", "https://github.com/gkislin"),
    STACKOVERFLOW("StackOverflow: ", "https://stackoverflow.com/users/548473/gkislin");

    String title;
    String value;

    ContactType(String title, String value) {
        this.title = title;
        this.value = value;
    }

    String printContacts() {
        StringBuilder sb = new StringBuilder();
        for (ContactType s : ContactType.values()) {
            sb.append(s.title + s.value + "\n");
        }
        return sb.toString();
    }
}
