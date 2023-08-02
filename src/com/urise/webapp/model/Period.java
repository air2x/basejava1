package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Period extends Company {
    private List<LocalDate> startDates = new ArrayList<>();
    private List<LocalDate> endDates = new ArrayList<>();
    private String description;
    private String title;

    public List<LocalDate> getStartDates() {
        return startDates;
    }

    public void setStartDates(List<LocalDate> startDates) {
        this.startDates = startDates;
    }

    public List<LocalDate> getEndDates() {
        return endDates;
    }

    public void setEndDates(List<LocalDate> endDates) {
        this.endDates = endDates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Period period = (Period) o;
        return startDates.equals(period.startDates) && endDates.equals(period.endDates) && Objects.equals(description, period.description) && title.equals(period.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDates, endDates, description, title);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDates=" + startDates +
                ", endDates=" + endDates +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
