package io.github.notaphplover.catanserver.user.adapter.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
  uniqueConstraints=
      @UniqueConstraint(columnNames={ "username" })
)
public class UserDb {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String passwordHash;

  private String username;

  public UserDb(Long id) {
    this.id = id;
  }

  public UserDb() {
    this(null); 
  }

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
