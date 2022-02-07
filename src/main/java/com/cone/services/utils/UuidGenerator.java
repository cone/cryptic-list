package com.cone.services.utils;

import java.util.UUID;

public class UuidGenerator implements IdGenerator {
  public String createNewId() {
    return UUID.randomUUID().toString();
  }
}
