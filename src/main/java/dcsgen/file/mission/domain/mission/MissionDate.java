package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MissionDate implements FilePart {
    private static final String HEADER = "[\"date\"] = ";

    private int day;
    private int year;
    private int month;

    public MissionDate(int day, int year, int month) {
        this.day = day;
        this.year = year;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);
        parts.add("\t\t[\"Day\"] = " + day);
        parts.add("\t\t[\"Year\"] = " + year);
        parts.add("\t\t[\"Month\"] = " + month);
        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
