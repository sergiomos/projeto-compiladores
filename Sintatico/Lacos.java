// package Sintatico;

// public class Lacos {
// private Parser parser;

// public Lacos(Parser parser) {
// this.parser = parser;
// }

// /*
// * para (i=0; i < 10; i++) {
// * bloco
// * }
// *
// *
// * for i in 0..10 {
// * }
// */

// protected boolean para() {
// return parser.matcher.matchT("para", "for ")
// && parser.matcher.matchL("(", " ")
// && parser.variaveis.declaracao()
// // && parser.expressao.expr_logica()
// && parser.matcher.matchL(")", ")")
// && parser.matcher.matchL("{", "{\n")
// && parser.programa.bloco()
// && parser.matcher.matchL("}", "}");
// }

// }
