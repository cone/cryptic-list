package com.cone.app;

import com.cone.services.utils.IdGenerator;

public class EntryData {
  String entriesFilePath, encriptedObjectsFilePath, password;
  IdGenerator idGenerator;

  public EntryData(
    String entriesFilePath,
    String encriptedObjectsFilePath,
    String password,
    IdGenerator idGenerator) 
  {
    this.entriesFilePath = entriesFilePath;
    this.encriptedObjectsFilePath = encriptedObjectsFilePath;
    this.password = password;
    this.idGenerator = idGenerator;
  }

  public String getEntriesFilePath() {
    return entriesFilePath;
  }

  public void setEntriesFilePath(String entriesFilePath) {
    this.entriesFilePath = entriesFilePath;
  }

  public String getEncriptedObjectsFilePath() {
    return encriptedObjectsFilePath;
  }

  public void setEncriptedObjectsFilePath(String encriptedObjectsFilePath) {
    this.encriptedObjectsFilePath = encriptedObjectsFilePath;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public IdGenerator getIdGenerator() {
    return idGenerator;
  }

  public void setIdGenerator(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }
}
