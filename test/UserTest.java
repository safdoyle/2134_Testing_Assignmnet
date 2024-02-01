import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class UserTest {
  private static String[] friendNames = {"Alice", "Bob", "Carol"};

  // clear the static users HashMap before each test
  @AfterEach
  void clearUsers() {
    User.users.clear();
  }

  // Test constructing a user adds them to the HashMap
  @Test
  void testConstructor() {
    User u = new User(friendNames[0]);
    assertEquals(friendNames[0], u.name, "Incorrect name");
    assertEquals(1, User.users.size(), "Incorrect size");
    assertEquals(u, User.users.get(friendNames[0]), "User not in HashMap");
  }

  @Test
  void testFind() {
  }

  @Test
  void testFriend() {
  }

  @Test
  void testUnfriend() {
  }

  @Test
  void testLeave() {
  }

  @Test
  void testIsFriend() {
  }

  // add more tests as needed using white-box, black-box or a mix of testing strategies
  // Note: you need to add multiple tests for each method in User.java
}