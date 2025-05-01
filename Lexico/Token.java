package Lexico;
public class Token {
    private String lexema;
    private String type;

    public Token(String type, String lexema) {
        this.type = type;
        this.lexema = lexema;
    }

    public String getLexema() {
        return lexema;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "<" + type + ", " + lexema + ">";
    }
}
