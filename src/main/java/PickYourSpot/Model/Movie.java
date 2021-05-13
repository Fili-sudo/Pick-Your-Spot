package PickYourSpot.Model;

import javafx.beans.property.*;
import org.dizitart.no2.objects.Id;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Movie implements java.io.Serializable{

    private String Titlu;
    private int an_aparitie;
    private String director;
    private String people;
    private double rating;
    private String gen;
    private int durata;

    private List<Days> timetable = new LinkedList<Days>();

    private StringProperty Titlup;
    private IntegerProperty an_aparitiep;
    private StringProperty directorp;
    private StringProperty peoplep;
    private DoubleProperty ratingp;
    private StringProperty genp;
    private IntegerProperty duratap;



    public Movie(String titlu, int an_aparitie, String director, String people, double rating, String gen, int durata) {
        Titlu = titlu;
        this.an_aparitie = an_aparitie;
        this.director = director;
        this.people = people;
        this.rating = rating;
        this.gen = gen;
        this.durata = durata;

        Titlup = new SimpleStringProperty(titlu);
        an_aparitiep = new SimpleIntegerProperty(an_aparitie);
        directorp = new SimpleStringProperty(director);
        peoplep = new SimpleStringProperty(people);
        ratingp = new SimpleDoubleProperty(rating);
        genp = new SimpleStringProperty(gen);
        duratap = new SimpleIntegerProperty(durata);

        timetable.add(new Days("Luni"));
        timetable.add(new Days("Marti"));
        timetable.add(new Days("Miercuri"));
        timetable.add(new Days("Joi"));
        timetable.add(new Days("Vineri"));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return an_aparitie == movie.an_aparitie && Double.compare(movie.rating, rating) == 0 && durata == movie.durata && Objects.equals(Titlu, movie.Titlu) && Objects.equals(director, movie.director) && Objects.equals(people, movie.people) && Objects.equals(gen, movie.gen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Titlu, an_aparitie, director, people, rating, gen, durata);
    }

    public String getTitlu() {
        return Titlu;
    }

    public void setTitlu(String titlu) {
        Titlu = titlu;
    }

    public int getAn_aparitie() {
        return an_aparitie;
    }

    public void setAn_aparitie(int an_aparitie) {
        this.an_aparitie = an_aparitie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }


    public StringProperty titlupProperty() {
        return Titlup;
    }

    public List<Days> getTimetable() {
        return timetable;
    }
}

