package io.github.notaphplover.catanserver.user.adapter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.github.notaphplover.catanserver.user.domain.model.IUser;

@Entity
public class User implements IUser {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String passwordHash;

  private String username;

  public Long getId() {
    return id;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

}
