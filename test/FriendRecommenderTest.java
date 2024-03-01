import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class FriendRecommenderTest {
  private static String[] friendNames = {"Alice", "Bob", "Carol"};

  // clear the static users HashMap before each test
  @AfterEach
  void clearUsers() {
    User.users.clear();
  }

  // recommending friends between two users where the second has another friend should recommend that friend
  @Test
  void testTwoFriends() {
    User u = new User(friendNames[0]);
    User f = new User(friendNames[1]);
    User g = new User(friendNames[2]);
    u.friend(f.name);
    f.friend(g.name);
    FriendRecommender fr = new FriendRecommender();
    ArrayList<String> al = new ArrayList<String>();
    fr.makeRecommendations(u, f, al);
    assertEquals(1, al.size(), "Wrong recommendation count");
    assertEquals(u.name + " and " + g.name + " should be friends", al.get(0), "Incorrect recommendation");
  }

  // Check if the suggestion is made.
  @Test
  void testMakeRecommendationsWithValidFriends() {
    User a = new User(friendNames[0]);
    User b = new User(friendNames[1]);
    User c = new User(friendNames[2]);
    a.friend("Bob");
    a.friend("Carol");
    String expectedSuggestion = "Bob and Carol should be friends";
    FriendRecommender fr = new FriendRecommender();
    ArrayList<String> al = new ArrayList<>();
    fr.makeRecommendations(b, a, al);
    assertEquals(1, al.size());
    assertTrue(al.get(0).equals(expectedSuggestion));
  }

}