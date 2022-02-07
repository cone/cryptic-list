package com.cone.mock;

import com.cone.services.utils.IdGenerator;

public class FakeIdGenerator implements IdGenerator {
  public String createNewId() {
    return "12345678";
  }
}
