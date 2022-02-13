package com.cone.services.jsonfile;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.App;

public class JsonFileLocator {
  public static String getPath(String fileName) throws URISyntaxException {
    File jarFile = new File(
      App.class.getProtectionDomain().
        getCodeSource().getLocation().
        toURI().getPath()
    );
    return jarFile.getParent() + File.separator + fileName; 
  }
}
