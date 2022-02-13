package com.cone.services.registry;

import java.util.Map;

import de.vandermeer.asciitable.AsciiTable;

public class RegistriesTable {
  private AsciiTable table;
  private Map<String, String> entries;

  public RegistriesTable(Map<String, String> entries) {
    this.entries = entries;
    table = new AsciiTable();
  }

  public String render() {
    addHeader();
    addEntries();
    return table.render();
  }

  private void addHeader() {
    table.addRule();
    table.addRow("ID", "Description");
  }

  private void addEntries() {
    entries.forEach((k, v) -> {
      table.addRule();
      table.addRow(k, v);
    });
    table.addRule();
  }
}
