package Semantico;

import Sintatico.Tree;
import Sintatico.Node;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
  private Tree tree;
  private SymbolTable symbolTable;
  private List<String> errors;

  public SemanticAnalyzer(Tree tree) {
    this.tree = tree;
    this.symbolTable = new SymbolTable();
    this.errors = new ArrayList<>();
  }

  public boolean analyze() {
    analyzeNode(tree.getRoot());
    return errors.isEmpty();
  }

  private void analyzeNode(Node node) {
    if (node == null)
      return;

    // Verifica declaração de variáveis
    if (node.getValue().equals("Declaracao")) {
      String varName = findVariableName(node);
      if (varName != null) {
        symbolTable.declareVariable(varName);
      }
    }
    // Verifica uso de variáveis
    else if (node.getValue().equals("Identificador")) {
      String varName = node.getChildren().get(0).getValue();
      if (!symbolTable.isDeclared(varName)) {
        errors.add("Erro semântico: Variável '" + varName + "' não foi declarada antes do uso.");
      }
    }

    // Analisa os filhos recursivamente
    for (Node child : node.getChildren()) {
      analyzeNode(child);
    }
  }

  private String findVariableName(Node node) {
    // Procura o nome da variável nos filhos da declaração
    for (Node child : node.getChildren()) {
      if (child.getValue().equals("Identificador")) {
        return child.getChildren().get(0).getValue();
      }
    }
    return null;
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
