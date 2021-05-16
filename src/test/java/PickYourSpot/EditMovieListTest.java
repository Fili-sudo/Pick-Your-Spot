package PickYourSpot;

import PickYourSpot.Controllers.SeeMovieListController;
import PickYourSpot.Model.Movie;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
class EditMovieListTest {

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

        try {
            UserService.addUser("admin", "admin", "Admin");

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
    @Test
    void testAdminAdd(FxRobot robot) {

        //admin login
        robot.clickOn("#login");
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("admin");
        robot.clickOn("#role").clickOn("Cinema");
        robot.clickOn("#adminButton");
        robot.clickOn("#loginButton");

       assertThat(MovieService.getAllMovies()).size().isEqualTo(0);

        robot.clickOn("#new");
        robot.clickOn("#TitluField");
        robot.write("Demolition Man");
        robot.clickOn("#DirectorField");
        robot.write("Marco Brambilla");
        robot.clickOn("#AnAparitieField");
        robot.write("1993");
        robot.clickOn("#GenField");
        robot.write("Comedy, Horror, Mystery");
        robot.clickOn("#DurataField");
        robot.write("97");
        robot.clickOn("#PeopleField");
        robot.write("Sylvester Stallone, Sandra Bullock");
        robot.clickOn("#RatingField");
        robot.write("5.8");
        robot.clickOn("#okAdd");

        robot.clickOn("#new");
        robot.clickOn("#TitluField");
        robot.write("One to Delete");
        robot.clickOn("#DirectorField");
        robot.write("Marco Brambilla");
        robot.clickOn("#AnAparitieField");
        robot.write("1993");
        robot.clickOn("#GenField");
        robot.write("Comedy, Horror, Mystery");
        robot.clickOn("#DurataField");
        robot.write("97");
        robot.clickOn("#PeopleField");
        robot.write("Sylvester Stallone, Sandra Bullock");
        robot.clickOn("#RatingField");
        robot.write("5.8");
        robot.clickOn("#okAdd");

        assertThat(MovieService.getAllMovies()).size().isEqualTo(2);


        //admin deletes One to Delete
        robot.clickOn("#movieView").clickOn("One to Delete");
        robot.clickOn("#delete");

        assertThat(MovieService.getAllMovies()).size().isEqualTo(1);

        //admin edits Demolition Man
        robot.clickOn("#movieView").clickOn("Demolition Man");
        robot.clickOn("#edit");
        robot.clickOn("#TitluFieldEdit");
        robot.eraseText(14);
        robot.write("Changed Title");
        robot.clickOn("#okEdit");
        assertThat(MovieService.getAllMovies().get(0).getTitlu()).isEqualTo("Changed Title");

        //admin adds 1 movie to the movie list
        robot.clickOn("#new");
        robot.clickOn("#TitluField");
        robot.write("Vivarium");
        robot.clickOn("#DirectorField");
        robot.write("Lorcan Finnegan");
        robot.clickOn("#AnAparitieField");
        robot.write("2019");
        robot.clickOn("#GenField");
        robot.write("Comedy, Horror, Mystery");
        robot.clickOn("#DurataField");
        robot.write("97");
        robot.clickOn("#PeopleField");
        robot.write("Imogen Poots, Jesse Eisenberg");
        robot.clickOn("#RatingField");
        robot.write("5.8");
        robot.clickOn("#okAdd");

        assertThat(MovieService.getAllMovies()).size().isEqualTo(2);
    }

}