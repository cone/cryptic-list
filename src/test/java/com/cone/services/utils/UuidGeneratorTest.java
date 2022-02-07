package com.cone.services.utils;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UuidGeneratorTest implements WithAssertions {
  @Test
  void testGetId() {
    String uniqueId = new UuidGenerator().createNewId();
    Assert.assertNotEquals("", uniqueId);
  }
}
