package Lexico;
public class Token {
    private String lexema;
    private String type;
    private int line;

    public Token(String type, String lexema) {
        this.type = type;
        this.lexema = lexema;
        this.line = 0;
    }

    public String getLexema() {
        return lexema;
    }

    public String getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "<" + type + ", " + lexema + ">";
    }
}
