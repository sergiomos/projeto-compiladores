package Sintatico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {

  String nome;
  List<Node> nodes;
  String enter;
  String exit;
  Node parent;
  int line;

  public Node(String nome) {
    this.nome = nome;
    nodes = new ArrayList<>();
    this.enter = "";
    this.exit = "";
    this.line = 0;
  }

  public void addNode(Node newNode) {
    newNode.parent = this;
    nodes.add(newNode);
  }

  public Node addNode(String nodeName) {
    Node newNode = new Node(nodeName);
    newNode.parent = this;
    nodes.add(newNode);
    return newNode;
  }

  public Node addNode(String enter, String nodeName, String exit) {
    Node newNode = new Node(nodeName);
    newNode.enter = enter;
    newNode.exit = exit;
    newNode.parent = this;
    nodes.add(newNode);
    return newNode;
  }

  @Override
  public String toString() {
    return this.enter + " " + this.nome + " " + this.exit;
  }

  // adaptado de
  // https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
  public String getTree() {
    System.out.println("AST");
    StringBuilder buffer = new StringBuilder(50);
    print(buffer, "", "");
    return buffer.toString();
  }

  private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
    buffer.append(prefix);
    buffer.append(nome);
    buffer.append('\n');
    for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
      Node next = it.next();
      if (it.hasNext()) {
        next.print(buffer, childrenPrefix + "+-- ", childrenPrefix + "|   ");
      } else {
        next.print(buffer, childrenPrefix + "`-- ", childrenPrefix + "   ");
      }
    }
  }

  public Node getNext() {
    return nodes.remove(0);
  }

  public String getName() {
    return nome;
  }

  public List<Node> getChildren() {
    return nodes;
  }

  public Node getParent() {
    return parent;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }
}
