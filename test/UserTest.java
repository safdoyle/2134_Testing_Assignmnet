import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
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
  void testFindWithValidUser() {
    User expectedUser = new User(friendNames[0]);
    User res = User.find("Alice");
    assertEquals(res, expectedUser);
  }

  @Test
  void testFindWithInvalidUser() {
    User res = User.find("Damian");
    assertNull(res);
  }

  @Test
  void testFindWithNull() {
    User res = User.find(null);
    assertNull(res);
  }

  @Test
  void testFriend() {
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);
    User res = b.friend("Alice");
    assertTrue(b.adj.containsValue(a));
    assertTrue(a.adj.containsValue(b));
    assertEquals(res, a);
  }


  @Test
  void testFriendForPriorFriendships(){
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);
    User res1 = b.friend("Alice");
    User res2 = b.friend("Alice");
    assertEquals(res1.adj, res2.adj);
  }

  @Test
  void testUnfriend() {
    User b = new User(friendNames[1]);
    User c = new User(friendNames[2]);
    b.friend("Carol");
    User res = b.unfriend("Carol");
    assertFalse(b.adj.containsValue(c));
    assertFalse(c.adj.containsValue(b));
    assertEquals(c, res);
  }


  @Test
  void testLeave() {
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);
    User c = new User(friendNames[2]);
    a.friend("Bob");
    a.friend("Carol");
    a.leave();
    assertFalse(User.users.containsValue(a));
    assertFalse(b.adj.containsValue(a));
    assertFalse(c.adj.containsValue(a));
    assertTrue(User.users.containsValue(b));
    assertTrue(User.users.containsValue(c));
  }

  @Test
  void testIsFriendWithFriendship() {
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);
    b.friend("Alice");
    assertTrue(b.isFriend(a));
    assertTrue(a.isFriend(b));
  }

  @Test
  void testIsFriendWithoutFriendship() {
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);

    for (User v : b.adj.values()){
      User d = b.adj.get(v.name);
      b.adj.remove(d.name);
    }

    for (User v : a.adj.values()){
      User d = a.adj.get(v.name);
      a.adj.remove(d.name);
    }
    assertFalse(b.isFriend(a));
    assertFalse(a.isFriend(b));
  }

}