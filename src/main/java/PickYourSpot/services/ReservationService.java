package PickYourSpot.services;

import PickYourSpot.Model.Reservation;
import PickYourSpot.Model.User;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

import static PickYourSpot.services.FileSystemService.getPathToFile;

public class ReservationService {

    private static NitriteCollection collection;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("reservation-database.db").toFile())
                .openOrCreate("test", "test");

        collection = database.getCollection("rezervari");
    }

    public static void addReservation(Reservation reservation){
        Document doc = Document.createDocument("movieTitle", reservation.getMovieTitle())
                .put("user", reservation.getUser())
                .put("seats", reservation.getSeats())
                .put("weekDay", reservation.getWeekDay())
                .put("time", reservation.getTime())
                .put("status", reservation.getStatus())
                .put("res_no", reservation.getRes_no())
                .put("row", reservation.getRow())
                .put("column", reservation.getColumn());

        collection.insert(doc);
    }
    public static void allRes(){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            String movie = doc.get("movieTitle", String.class);
            System.out.println(movie);
        }
    }


}
