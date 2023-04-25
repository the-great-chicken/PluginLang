package com.TheGreatChicken.PluginLang.lexer;

import com.TheGreatChicken.PluginLang.utils.CharList;

public class LexerFile {
    public final String name;
    public final String source;
    public final CharList sourceList;

    public int size () {
        return source.length();
    }

    public LexerFile (String name, String source) {
        this.name   = name;
        this.source = source;

        sourceList = new CharList(source);
    }
}
