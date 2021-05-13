package PickYourSpot.services;

import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.filters.Filters;

import static PickYourSpot.services.FileSystemService.getPathToFile;

public class ReservationService {

    private static NitriteCollection collection;
    private static ObservableList<Reservation> reservationData = FXCollections.observableArrayList();

    public static ObservableList<Reservation> getReservationData() {
        return reservationData;
    }

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
    public static void populate(){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            Reservation res = new Reservation(doc.get("user",String.class),doc.get("movieTitle",String.class)
            ,doc.get("seats",String.class),doc.get("weekDay",String.class),doc.get("time",String.class)
            ,doc.get("status",String.class),doc.get("row",int[].class),doc.get("column",int[].class)
            ,doc.get("res_no",Integer.class));
            reservationData.add(res);
        }
    }
    public static void populate(String user){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            String who = doc.get("user",String.class);
            if (who.equals(user)) {
                Reservation res = new Reservation(doc.get("user", String.class), doc.get("movieTitle", String.class)
                        , doc.get("seats", String.class), doc.get("weekDay", String.class), doc.get("time", String.class)
                        , doc.get("status", String.class), doc.get("row", int[].class), doc.get("column", int[].class)
                        , doc.get("res_no", Integer.class));
                reservationData.add(res);
            }
        }
    }

    public static  void find(Reservation reservation){
        Cursor cursor = collection.find();
        for (Document doc : cursor) {
            Reservation res = new Reservation(doc.get("user", String.class), doc.get("movieTitle", String.class)
                    , doc.get("seats", String.class), doc.get("weekDay", String.class), doc.get("time", String.class)
                    , doc.get("status", String.class), doc.get("row", int[].class), doc.get("column", int[].class)
                    , doc.get("res_no", Integer.class));
            if (reservation.equals(res)) {
                reservationData.removeAll(reservationData);
                doc.put("status", "canceled");
                collection.update(doc);
            }
        }
    }

    public static  void findAndAccept(Reservation reservation){
        Cursor cursor = collection.find();
        for (Document doc : cursor) {
            Reservation res = new Reservation(doc.get("user", String.class), doc.get("movieTitle", String.class)
                    , doc.get("seats", String.class), doc.get("weekDay", String.class), doc.get("time", String.class)
                    , doc.get("status", String.class), doc.get("row", int[].class), doc.get("column", int[].class)
                    , doc.get("res_no", Integer.class));
            if (reservation.equals(res)) {
                reservationData.removeAll(reservationData);
                doc.put("status", "accepted");
                collection.update(doc);
            }
        }
    }
    public static void emptycol() { collection.remove(Filters.ALL);}
    public static boolean isEmpty(){
        if(collection.find().size()==0){
            return true;
        }
            else{
                return false;
        }
    }
}
