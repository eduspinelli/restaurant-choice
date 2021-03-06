package com.restaurant.choice.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.restaurant.choice.domain.model.User;

public class UserTest {

  @Test
  public void createExistetUser() {
    User user = User.createExistingUserWithVote(99999L, "Eduardo", "Eduardo", "Eduardo", "Spinelli",
        "eduardospinelli.dev@gmail.com");

    assertEquals(Long.valueOf(99999), user.getId());
    assertEquals(String.valueOf("Eduardo"), user.getUsername());
    assertEquals(String.valueOf("Eduardo"), user.getPassword());
    assertEquals(String.valueOf("Eduardo"), user.getFirstName());
    assertEquals(String.valueOf("Spinelli"), user.getLastName());
    assertEquals(String.valueOf("eduardospinelli.dev@gmail.com"), user.getEmail());
    assertEquals(true, user.getVote());

  }

}
