package io.github.notaphplover.catanserver.fixtures.user.domain.model;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.User;

public class UserFixturesUtils {

  private static IFixtureFactory<IUser> userFactory = new UserFixturesFactory(getUser());

  public static IUser getUser() {
    IUser user = new User(1l);
    user.setUsername("username");
    user.setPasswordHash("$2a$10$a0ATFPbhB7CM6bDQL7/sjO0v4WGUKo0B.Pj/KqwmwNhXA.6Vb.JEO");

    return user;
  }

  public static IFixtureFactory<IUser> getUserFactory() {
    return userFactory;
  }
}
