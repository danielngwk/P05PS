package com.myapplicationdev.android.p04_revisionnotes;

public class Songs {

    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSinger(String singer) {
        this.singers = singer;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Songs(int _id, String title, String singers, int year, int stars) {
        this._id = _id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;

    }

    @Override
    public String toString() {
        return "ID: " + _id + ", "
                + title + ", "
                + singers + ", "
                + year + ", "
                + stars;
    }
}
