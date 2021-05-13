package PickYourSpot.Model;

public class Locuri {
    private String movieTitle;
    private String weekDay;
    private int ora;
    private int minut;
    private int [][] sala = new int[5][8];

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }

    public int[][] getSala() {
        return sala;
    }

    public void setSala(int[][] sala) {
        this.sala = sala;
    }

    public Locuri(String movieTitle, String weekDay, int ora, int minut, int[][] sala) {
        this.movieTitle = movieTitle;
        this.weekDay = weekDay;
        this.ora = ora;
        this.minut = minut;
        this.sala = sala;
    }

}
