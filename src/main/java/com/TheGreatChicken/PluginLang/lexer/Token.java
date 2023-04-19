package com.TheGreatChicken.PluginLang.lexer;

public class Token {
    public final TokenType type;
    public final String   value;

    public Token (TokenType type, String value) {
        this.type  = type;
        this.value = value;
    }

    public LexerFile file;
    public int idx;
    public int col;
    public int row;

    public Token setPosition (LexerFile file, int idx, int col, int row) {
        this.file = file;
        this.idx  = idx;
        this.col  = col;
        this.row  = row;

        return this;
    }

    public String getMessage () {
        return "at file " + file.name + " at col " + this.col + " at line " + this.row;
    }
}
