package Semantico;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
  private Map<String, VariableInfo> variables;
  private Map<String, FunctionInfo> functions;

  public SymbolTable() {
    this.variables = new HashMap<>();
    this.functions = new HashMap<>();
  }

  public void declareVariable(String name, String type, int line) {
    variables.put(name, new VariableInfo(type, line));
  }

  public void declareFunction(String name, int line) {
    functions.put(name, new FunctionInfo(line));
  }

  public boolean isDeclared(String name) {
    return variables.containsKey(name) || functions.containsKey(name);
  }

  public boolean isFunction(String name) {
    return functions.containsKey(name);
  }

  public VariableInfo getVariableInfo(String name) {
    return variables.get(name);
  }

  public FunctionInfo getFunctionInfo(String name) {
    return functions.get(name);
  }

  public static class VariableInfo {
    private String type;
    private int declarationLine;

    public VariableInfo(String type, int declarationLine) {
      this.type = type;
      this.declarationLine = declarationLine;
    }

    public String getType() {
      return type;
    }

    public int getDeclarationLine() {
      return declarationLine;
    }
  }

  public static class FunctionInfo {
    private int declarationLine;

    public FunctionInfo(int declarationLine) {
      this.declarationLine = declarationLine;
    }

    public int getDeclarationLine() {
      return declarationLine;
    }
  }
}
