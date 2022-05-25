import java.util.ArrayList;

//<Braces> ::= <Item> <Tail>
//<Tail>::= <Braces> | ε
//<Item>::= ( <Braces> ) | { <Braces> } | [ <Braces> ] | NUMBER


import java.util.List;

public class Parser {
    public static void main(String[] args) {
        //String expressionText = "10 20 { 30 [ 40 ] ( 50 60 ) }";
        String expressionText = "10 20 {\n" +
                "30 [ 40 ] ( 50 60 )\n" +
                "}";
        List<Lexeme> lexemes = lexAnalyze(expressionText);
        System.out.println(braces(new LexemeBuffer(lexemes)));
        // <Item> <Item> { <Item> [ <Item> ] ( <Item> <Item> ) } <EOF>

    }

    public enum LexemeType { // типы лексем
        LB, RB,
        NUM,
        EOF;
    }

    public static class Lexeme { // класс лексемы
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "\nLexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class LexemeBuffer {
        private int pos;

        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }

    }

    public static List<Lexeme> lexAnalyze(String text) { // лексический анализ
        ArrayList<Lexeme> lexemes = new ArrayList<Lexeme>(); // массив лексем
        int pos = 0;
        while (pos < text.length()) {
            char sym = text.charAt(pos);
            switch (sym) {
                case '(':
                case '{':
                case '[':
                    lexemes.add(new Lexeme(LexemeType.LB, sym));
                    pos++;
                    continue;
                case ')':
                case '}':
                case ']':
                    lexemes.add(new Lexeme(LexemeType.RB, sym));
                    pos++;
                    continue;
                default:
                    if (sym >= '0' && sym <= '9') {
                        StringBuilder num = new StringBuilder();
                        do {
                            num.append(sym);
                            pos++;
                            if (pos >= text.length()) break;
                            sym = text.charAt(pos);
                        } while (sym >= '0' && sym <= '9');
                        lexemes.add(new Lexeme(LexemeType.NUM, num.toString()));
                    } else {
                        if (sym != ' ' && sym != '\n') {
                            throw new RuntimeException("Unexpected character: " + sym);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    //<Braces> ::= <Item> <Tail>
    //<Tail>::= <Braces> | ε
    //<Item>::= ( <Braces> ) | { <Braces> } | [ <Braces> ] | NUMBER

    public static String braces(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case LB:
            case NUM:
                lexemes.back();
                String val = item(lexemes);
                return val + tail(lexemes);
            default:
                lexemes.back();
                return "";
        }
    }

    public static String tail(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return "<EOF>";
        } else {
            lexemes.back();
            return braces(lexemes);
        }
    }

    public static String item(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUM:
                return "<Item> ";
            case LB:
                String lb = lexeme.value;
                String val = braces(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RB) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value +
                            " at position: " + lexemes.getPos());
                }
                String rb = lexeme.value;
                return lb + ' ' + val + rb + ' ';
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value +
                        " at position: " + lexemes.getPos());
        }
    }
}