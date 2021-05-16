package PickYourSpot;

import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.*;
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
class RegistrationTest {



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

        } catch (UsernameAlreadyExistsException e) { }
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
    void testRegistrationAndLoginUseCase(FxRobot robot) {
        robot.clickOn("#login");
        robot.clickOn("#username");
        robot.write("user1");
        robot.clickOn("#password");
        robot.write("user1");
        robot.clickOn("#role").clickOn("User");
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Incorrect username or password.\n Please reenter your credentials or select the correct role");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("An account with the username user1 already exists!");

        robot.clickOn("#password");
        robot.write("user1");
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Incorrect username or password.\n Please reenter your credentials or select the correct role");

        robot.clickOn("#password");
        robot.eraseText(5);
        robot.clickOn("#loginButton");
    }
}