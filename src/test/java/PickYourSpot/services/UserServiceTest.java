package PickYourSpot.services;


import PickYourSpot.Model.User;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {
    @BeforeAll
    static void beforeAll() {
        FileSystemService.APPLICATION_FOLDER = ".test";
        UserService.initDatabase();
    }

    @BeforeEach
    void setUp(){
        UserService.empty();
    }

    @Test
    void testDatabaseIsInitialisedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser("client", "client", "User");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("client");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("client", "client"));
    }

    @Test
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser("client", "client", "User");
            UserService.addUser("client", "client", "User");
        });
    }

    @Test
    void testVerifyCredentials() throws UsernameAlreadyExistsException {
        UserService.addUser("client", "client", "User");
        assertThat(UserService.verifyCredentials("client" ,"client", "User")).isEqualTo(true);
        assertThat(UserService.verifyCredentials("client" ,"asass", "User")).isEqualTo(false);

    }
}