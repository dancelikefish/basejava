package ru.webapp.model;

public enum ContactType {

    CELLPHONENUMBER("Тел.: "),
    SKYPE("Skype: ") {
        @Override
        public String toHtml0(String value) {
            return "<img src=\"img/skype.png\"><a href='skype: "+ value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта: ") {
        @Override
        public String toHtml0(String value) {
            return "<img src=\"img/email.png\"><a href='mailto: "+ value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("LinkedIn: ") {
        @Override
        public String toHtml0(String value) {
            return "<img src=\"img/lin.png\"><a href='linkedIn: "+ value + "'>" + value + "</a>";
        }
    },
    GITHUB("GitHub: ") {
        @Override
        public String toHtml0(String value) {
            return "<img src=\"img/gh.png\"><a href='gitHub: "+ value + "'>" + value + "</a>";
        }
    },
    STACKOVERFLOW("StackOverflow: ") {
        @Override
        public String toHtml0(String value) {
            return "<img src=\"img/so.png\"><a href='stackOverflow: "+ value + "'>" + value + "</a>";
        }
    },
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

}
