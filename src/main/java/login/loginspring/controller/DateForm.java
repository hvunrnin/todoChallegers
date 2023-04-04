package login.loginspring.controller;

public class DateForm {
    private String feed_date;
    private String onlyDate;
    private String day;

    public String getFeed_date() {
        return feed_date;
    }

    public String getOnlyDate() { return onlyDate; }

    public String getDay() { return day; }

    public void setFeed_date(String feed_date) {
        this.feed_date = feed_date;
    }

    public void setOnlyDate(String onlyDate) { this.onlyDate = onlyDate; }

    public void setDay(String day) { this.day = day; }
}
