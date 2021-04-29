package PickYourSpot.Model;

import java.util.LinkedList;
import java.util.List;

public class Days {
    private String week_day;
    private List<representation> program = new LinkedList<representation>();


    public Days(String week_day) {
        this.week_day = week_day;

        program.add(new representation(10, 20));
        program.add(new representation(14, 30));
        program.add(new representation(18, 40));
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public List<representation> getProgram() {
        return program;
    }

    public void setProgram(List<representation> program) {
        this.program = program;
    }
}
