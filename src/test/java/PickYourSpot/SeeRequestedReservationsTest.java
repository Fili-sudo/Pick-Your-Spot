package PickYourSpot;

import PickYourSpot.Model.Reservation;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class SeeRequestedReservationsTest {

    private static Scene scene;
    private static Stage window;

    public static Stage getWindow() {
        return window;
    }

    public static Scene getScene() {
        return scene;
    }

    @BeforeAll
    static void beforeAll() {
        FileSystemService.APPLICATION_FOLDER = ".test";
        UserService.initDatabase();
        ReservationService.initDatabase();
        MovieService.initDatabase();
        LocuriService.initDatabase();
    }

    @BeforeEach
    void setUp(FxRobot robot) {
        UserService.empty();
        MovieService.empty();
        ReservationService.emptycol();
        ReservationService.addReservation(new Reservation("andrei", "Demolition Man", "23,24,25", "Marti","10:20", "pending",new int[]{3,3,3},new int[]{4,5,6}, 354376565));
        ReservationService.addReservation(new Reservation("mihai", "Tenet", "28,29", "Miercuri","14:20", "pending",new int[]{4,4},new int[]{1,2}, 656747389));


        try {
            UserService.addUser("admin", "admin", "Admin");
            UserService.addUser("staff1", "staff1", "Staff");

        } catch (UsernameAlreadyExistsException e) {
        }
    }


    @Start
    void start(Stage primaryStage) throws IOException {

        window = primaryStage;
        Main.setWindow(window);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("firstpage.fxml")));
        primaryStage.setTitle("Pick Your Spot");
        scene = new Scene(root, 600, 400);
        Main.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testSeeRequestedreservations(FxRobot robot) {
        robot.clickOn("#login");
        robot.clickOn("#username");
        robot.write("staff1");
        robot.clickOn("#password");
        robot.write("staff1");
        robot.clickOn("#role").clickOn("Cinema");
        robot.clickOn("#staffButton");
        robot.clickOn("#loginButton");

        robot.clickOn("#reservationTable").clickOn("354376565");
        robot.clickOn("#reservationTable").clickOn("656747389");

        robot.clickOn("#reservationTable").clickOn("354376565");
        robot.clickOn("#anulleReservationByStaff");
        robot.clickOn("OK");
        robot.clickOn("#reservationTable").clickOn("354376565");
        assertThat(ReservationService.getAllReservations().get(0).getStatus()).isEqualTo("canceled");
        robot.clickOn("#reservationTable").clickOn("656747389");
        robot.clickOn("#acceptReservationByStaff");
        robot.clickOn("OK");
        robot.clickOn("#reservationTable").clickOn("656747389");
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(2);
        assertThat(ReservationService.getAllReservations().get(1).getStatus()).isEqualTo("accepted");
    }
}