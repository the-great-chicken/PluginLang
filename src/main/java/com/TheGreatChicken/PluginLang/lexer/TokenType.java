package com.TheGreatChicken.PluginLang.lexer;

public enum TokenType {
    NAME, STRING, REFERENCE, KEYWORD, EOL,

    PLUS(1), MINUS(1), TIMES(1), DIVIDE(1),

    PIPE(1), LBRACKET(1), RBRACKET(1),
    EQ(2), NEQ(2), GT(1), LE(1), GTEQ(2), LEEQ(2);

    final int fixed_delta;

    TokenType () {
        this.fixed_delta = 0;
    }
    TokenType (int d) {
        this.fixed_delta = d;
    }
}
