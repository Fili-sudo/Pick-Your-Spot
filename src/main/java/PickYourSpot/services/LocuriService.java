package PickYourSpot.services;

import PickYourSpot.Controllers.TimetableController;
import PickYourSpot.Model.Locuri;
import PickYourSpot.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

import static PickYourSpot.services.FileSystemService.getPathToFile;

public class LocuriService {

    private static NitriteCollection collection;


    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("locuri-database.db").toFile())
                .openOrCreate("test", "test");

        collection = database.getCollection("locuri");
    }

    public static void addLocuri(Locuri locuri){
        Document doc = Document.createDocument("movieTitle", locuri.getMovieTitle())
                .put("weekDay", locuri.getWeekDay()).put("ora", locuri.getOra())
                .put("minut", locuri.getMinut()).put("sala", locuri.getSala());
        collection.insert(doc);
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

}
