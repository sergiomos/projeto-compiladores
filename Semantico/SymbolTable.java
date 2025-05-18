package Semantico;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
  private Map<String, Boolean> variables;

  public SymbolTable() {
    this.variables = new HashMap<>();
  }

  public void declareVariable(String name) {
    variables.put(name, true);
  }

  public boolean isDeclared(String name) {
    return variables.containsKey(name);
  }
}
