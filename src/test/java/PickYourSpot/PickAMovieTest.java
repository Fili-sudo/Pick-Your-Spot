package PickYourSpot;

import PickYourSpot.Model.Movie;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class PickAMovieTest {
    private static Scene scene;
    private static Stage window;

    public static Stage getWindow() {
        return window;
    }

    public static Scene getScene() {
        return scene;
    }



    @AfterEach
    void tearDown() {
        UserService.database.close();
        MovieService.database.close();
        ReservationService.database.close();
        LocuriService.database.close();
    }

    @Start
    void start(Stage primaryStage) throws IOException {

        FileSystemService.APPLICATION_FOLDER = ".testui";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        MovieService.initDatabase();
        ReservationService.initDatabase();
        LocuriService.initDatabase();
        MovieService.addMovie(new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
        MovieService.addMovie(new Movie("Tenet", 2020, "Christopher Nolan", "Jefferson Hall, Juhan Ulfsak",7.4, " Action, Sci-Fi, Thriller", 150));


        try {
            UserService.addUser("admin", "admin", "Admin");
            UserService.addUser("andrei", "andrei", "User");

        } catch (UsernameAlreadyExistsException e) {
        }
        window = primaryStage;
        Main.setWindow(window);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("firstpage.fxml")));
        primaryStage.setTitle("Pick Your Spot");
        scene = new Scene(root, 600, 400);
        Main.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Disabled
    @Test
    void testViewMovieList(FxRobot robot) {
        robot.clickOn("#login");
        robot.clickOn("#username");
        robot.write("andrei");
        robot.clickOn("#password");
        robot.write("andrei");
        robot.clickOn("#role").clickOn("User");
        robot.clickOn("#loginButton");

        robot.clickOn("#movieView").clickOn("Tenet");
        robot.clickOn("#pick");
        robot.clickOn("#h21");
        robot.clickOn("#16");
        robot.clickOn("#17");
        robot.clickOn("#18");
        robot.clickOn("#seats");
        robot.moveTo(640, 550);
        robot.clickOn();
        robot.clickOn("#16");
        robot.clickOn("#next");
        assertThat(robot.lookup("#nextMessage").queryText()).hasText("Please select as many seats as you have chosen");
        robot.clickOn("#16");
        robot.clickOn("#next");
        assertThat(robot.lookup("#day").queryText()).hasText("Miercuri");
        assertThat(robot.lookup("#time").queryText()).hasText("10:20");
        assertThat(robot.lookup("#seats").queryText()).hasText("16, 17, 18");
        assertThat(robot.lookup("#movie").queryText()).hasText("Tenet");
        robot.clickOn("#confirmReservation");
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(1);
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(1);

        robot.clickOn("#movieView").clickOn("Tenet");
        robot.clickOn("#pick");
        robot.clickOn("#h21");
        robot.clickOn("#16");
    }

}