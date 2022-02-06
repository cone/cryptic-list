package com.cone.app;

import java.io.Serializable;
import java.util.Objects;

public class Credentials implements Serializable{
  private String user, password, shortDescription;

  public Credentials(String user, String password, String shortDescription) {
    this.user = user;
    this.password = password;
    this.shortDescription = shortDescription;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj)
          return true;
      if (obj == null || getClass() != obj.getClass())
          return false;
          Credentials credentials = (Credentials) obj;
      return Objects.equals(user, credentials.user) &&
             Objects.equals(password, credentials.password) &&
             Objects.equals(shortDescription, credentials.shortDescription);
  }
}
