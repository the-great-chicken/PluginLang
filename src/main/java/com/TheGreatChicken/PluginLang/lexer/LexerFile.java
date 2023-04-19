package com.TheGreatChicken.PluginLang.lexer;

public class LexerFile {
    public final String name;
    public final String source;

    public int size () {
        return source.length();
    }

    LexerFile (String name, String source) {
        this.name   = name;
        this.source = source;
    }
}
