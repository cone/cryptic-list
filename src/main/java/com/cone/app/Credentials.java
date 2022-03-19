package com.cone.app;

import java.io.Serializable;
import java.util.Objects;

public class Credentials implements Serializable {
  private static final long serialVersionUID = 1234567L;
  private String user, password;

  public Credentials(String user, String password) {
    this.user = user;
    this.password = password;
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

  @Override
  public boolean equals(Object obj) {
      if (this == obj)
          return true;
      if (obj == null || getClass() != obj.getClass())
          return false;
          Credentials credentials = (Credentials) obj;
      return Objects.equals(user, credentials.user) &&
             Objects.equals(password, credentials.password);
  }
}
