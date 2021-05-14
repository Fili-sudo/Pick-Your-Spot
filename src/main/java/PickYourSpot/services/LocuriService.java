package PickYourSpot.services;

import PickYourSpot.Controllers.TimetableController;
import PickYourSpot.Model.Locuri;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.filters.Filters;

import static PickYourSpot.services.FileSystemService.getPathToFile;

public class LocuriService {

    private static NitriteCollection collection;


    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("locuri-database.db").toFile())
                .openOrCreate("test", "test");

        collection = database.getCollection("locuri");
    }

    public static void addLocuri(Locuri locuri, Movie movie){
        boolean sw = false;
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            Locuri loc = new Locuri(doc.get("movieTitle", String.class), doc.get("weekDay", String.class)
                    , doc.get("ora", Integer.class), doc.get("minut", Integer.class)
                    , doc.get("sala", int[][].class));
            if(loc.getMovieTitle().equals(movie.getTitlu()) &&
                    loc.getWeekDay().equals(movie.getTimetable().get(TimetableController.getJ()).getWeek_day()) &&
                    loc.getOra() == movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getOra() &&
                    loc.getMinut() == movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getMinut()){

                sw=true;
                doc.put("sala", locuri.getSala());
                collection.update(doc);
            }
        }
        if(!sw){
            Document doc = Document.createDocument("movieTitle", locuri.getMovieTitle())
                    .put("weekDay", locuri.getWeekDay()).put("ora", locuri.getOra())
                    .put("minut", locuri.getMinut()).put("sala", locuri.getSala());
            collection.insert(doc);
        }

    }


    public static void findByMovie(Movie movie){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            Locuri loc = new Locuri(doc.get("movieTitle", String.class), doc.get("weekDay", String.class)
            , doc.get("ora", Integer.class), doc.get("minut", Integer.class)
            , doc.get("sala", int[][].class));
            if(loc.getMovieTitle().equals(movie.getTitlu()) &&
                loc.getWeekDay().equals(movie.getTimetable().get(TimetableController.getJ()).getWeek_day()) &&
                loc.getOra() == movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getOra() &&
                loc.getMinut() == movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getMinut()){

                movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).setSala(loc.getSala());
            }
        }
    }

    public static void freeSeats(Reservation reservation){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            Locuri loc = new Locuri(doc.get("movieTitle", String.class), doc.get("weekDay", String.class)
                    , doc.get("ora", Integer.class), doc.get("minut", Integer.class)
                    , doc.get("sala", int[][].class));
            String time = loc.getOra() + ":" + loc.getMinut();
            int [][] sala = loc.getSala();
            if(loc.getMovieTitle().equals(reservation.getMovieTitle()) &&
                    loc.getWeekDay().equals(reservation.getWeekDay()) &&
                    time.equals(reservation.getTime())){

                for (int i=0; i<reservation.getRow().length;i++){
                    sala[reservation.getRow()[i]][reservation.getColumn()[i]] = 0;
                }
                doc.put("sala", sala);
                collection.update(doc);
            }
        }

    }
    public static void empty(){
        collection.remove(Filters.ALL);
    }

}
