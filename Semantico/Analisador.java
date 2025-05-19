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

  public Analisador(Tree tree) {
    this.tree = tree;
    this.symbolTable = new SymbolTable();
    this.errors = new ArrayList<>();
  }

  public boolean run() {
    currentNode = tree.getRoot();
    return errors.isEmpty();
  }

  private void analyzeNode(Node node) {
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
