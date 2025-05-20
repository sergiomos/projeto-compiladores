package Semantico;

import Sintatico.Tree;
import Sintatico.Node;
import java.util.ArrayList;
import java.util.List;

public class Analisador {
  private Tree tree;
  private SymbolTable symbolTable;
  private List<String> errors;
  private Node currentNode;
  private int currentLine;

  public Analisador(Tree tree) {
    this.tree = tree;
    this.symbolTable = new SymbolTable();
    this.errors = new ArrayList<>();
  }

  public boolean run() {
    currentNode = tree.getRoot();
    analyzeNode(currentNode);
    return errors.isEmpty();
  }

  private void analyzeNode(Node node) {
    if (node == null) return;

    // Update current line for error reporting
    if (node.getLine() > 0) {
      currentLine = node.getLine();
    }

    // Handle variable declarations
    if (node.getName().equals("DECLARACAO")) {
      handleDeclaration(node);
    }
    // Handle function declarations
    else if (node.getName().equals("FUNCAO")) {
      handleFunction(node);
    }
    // Handle variable usage
    else if (node.getName().equals("IDENTIFICADOR")) {
      handleIdentifier(node);
    }

    // Recursively analyze child nodes
    for (Node child : node.getChildren()) {
      analyzeNode(child);
    }
  }

  private void handleDeclaration(Node declarationNode) {
    String type = null;
    String varName = null;

    // Find type and variable name in declaration
    for (Node child : declarationNode.getChildren()) {
      if (child.getName().equals("TIPO")) {
        type = child.getChildren().get(0).getName();
      } else if (child.getName().equals("IDENTIFICADOR")) {
        varName = child.getChildren().get(0).getName();
      }
    }

    if (type != null && varName != null) {
      if (symbolTable.isDeclared(varName)) {
        SymbolTable.VariableInfo info = symbolTable.getVariableInfo(varName);
        if (info != null) {
          errors.add(String.format("Erro na linha %d: Variável '%s' já declarada na linha %d",
            currentLine, varName, info.getDeclarationLine()));
        } else {
          SymbolTable.FunctionInfo funcInfo = symbolTable.getFunctionInfo(varName);
          errors.add(String.format("Erro na linha %d: Nome '%s' já usado como função na linha %d",
            currentLine, varName, funcInfo.getDeclarationLine()));
        }
      } else {
        symbolTable.declareVariable(varName, type, currentLine);
      }
    }
  }

  private void handleFunction(Node functionNode) {
    String funcName = null;

    // Find function name in declaration
    for (Node child : functionNode.getChildren()) {
      if (child.getName().equals("IDENTIFICADOR")) {
        funcName = child.getChildren().get(0).getName();
        break;
      }
    }

    if (funcName != null) {
      if (symbolTable.isDeclared(funcName)) {
        SymbolTable.VariableInfo varInfo = symbolTable.getVariableInfo(funcName);
        if (varInfo != null) {
          errors.add(String.format("Erro na linha %d: Nome '%s' já usado como variável na linha %d",
            currentLine, funcName, varInfo.getDeclarationLine()));
        } else {
          SymbolTable.FunctionInfo funcInfo = symbolTable.getFunctionInfo(funcName);
          errors.add(String.format("Erro na linha %d: Função '%s' já declarada na linha %d",
            currentLine, funcName, funcInfo.getDeclarationLine()));
        }
      } else {
        symbolTable.declareFunction(funcName, currentLine);
      }
    }
  }

  private void handleIdentifier(Node identifierNode) {
    String varName = identifierNode.getChildren().get(0).getName();
    
    // Check if variable is used in a declaration (skip check)
    Node parent = identifierNode.getParent();
    if (parent != null && (parent.getName().equals("DECLARACAO") || parent.getName().equals("FUNCAO"))) {
      return;
    }

    // Check if variable or function is declared before use
    if (!symbolTable.isDeclared(varName)) {
      errors.add(String.format("Erro na linha %d: Identificador '%s' não declarado",
        currentLine, varName));
    }
  }

  public List<String> getErrors() {
    return errors;
  }

  public void printErrors() {
    if (errors.isEmpty()) {
      System.out.println("Análise semântica concluída sem erros.");
    } else {
      System.out.println("Erros encontrados na análise semântica:");
      for (String error : errors) {
        System.out.println(error);
      }
    }
  }
}
