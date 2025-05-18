package Sintatico;

import java.util.ArrayList;
import java.util.List;

public class DerivationTree {
  private String node;
  private List<DerivationTree> children;
  private int level;

  public DerivationTree(String node) {
    this.node = node;
    this.children = new ArrayList<>();
    this.level = 0;
  }

  public void addChild(DerivationTree child) {
    child.level = this.level + 1;
    this.children.add(child);
  }

  public void print() {
    StringBuilder indent = new StringBuilder();
    for (int i = 0; i < level; i++) {
      indent.append("  ");
    }
    System.out.println(indent + "└─ " + node);
    for (DerivationTree child : children) {
      child.print();
    }
  }
}
