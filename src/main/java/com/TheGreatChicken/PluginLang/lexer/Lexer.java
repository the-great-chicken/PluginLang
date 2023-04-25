package com.TheGreatChicken.PluginLang.lexer;

import java.util.ArrayList;

import com.TheGreatChicken.PluginLang.utils.CharList;
import com.TheGreatChicken.PluginLang.utils.Trie;

public class Lexer {
    public final LexerFile file;

    private int idx = 0;
    private int bol = - 1;
    private int row = 1;

    public int __p_idx = 0;
    public int __p_bol = 0;
    public int __p_row = 0;

    public static final Trie<Character, TokenType> OPERAND_TRIE;

    static {
        OPERAND_TRIE = new Trie<>();

        OPERAND_TRIE.put( new CharList("+" ), TokenType.PLUS   );
        OPERAND_TRIE.put( new CharList("-" ), TokenType.MINUS  );
        OPERAND_TRIE.put( new CharList("*" ), TokenType.TIMES  );
        OPERAND_TRIE.put( new CharList("/" ), TokenType.DIVIDE );
        OPERAND_TRIE.put( new CharList("|" ), TokenType.PIPE   );

        OPERAND_TRIE.put( new CharList("=="), TokenType.EQ   );
        OPERAND_TRIE.put( new CharList("!="), TokenType.NEQ  );
        OPERAND_TRIE.put( new CharList(">="), TokenType.GTEQ );
        OPERAND_TRIE.put( new CharList(">" ), TokenType.GT   );
        OPERAND_TRIE.put( new CharList("<="), TokenType.LEEQ );
        OPERAND_TRIE.put( new CharList("<" ), TokenType.LE   );
    }

    private void prepare ()  {
        __p_idx = idx;
        __p_bol = bol;
        __p_row = row;
    }

    private Token make_token (TokenType type) {
        return make_token(type, null);
    }
    private Token make_token (TokenType type, String value) {
        return new Token(type, value)
            .setPosition(file, __p_idx, __p_idx - __p_bol, __p_row);
    }

    public Lexer (LexerFile file) {
        this.file = file;
    }

    public boolean is_empty () {
        return idx >= file.size();
    }
    public char get_char () {
        return file.source.charAt(idx);
    }
    public void chop_char () {
        if (is_empty()) return ;

        idx ++;
        if (is_empty() || get_char() != '\n') return ;

        bol = idx;
        row ++;
    }

    public void trim_left () {
        while ((!this.is_empty()) && Character.isWhitespace(this.get_char()))
            this.chop_char();
    }
    public boolean is_name () {
        return (Character.isLetterOrDigit(this.get_char()) || this.get_char() == '_');
    }

    public Token compute_name (TokenType type, boolean offset) {
        prepare();
        if (offset) this.chop_char();

        int start = idx;
        while ( (!this.is_empty()) && is_name())
            this.chop_char();
        
        String value = this.file.source.substring(start, idx);

        return make_token(type, value);
    }
    public Token next_token () {
        return next_token(true);
    }
    public Token next_token (boolean log) {
        this.trim_left();
        if (is_empty()) return null;

        if (get_char() == '%') return compute_name(TokenType.KEYWORD, true);
        if (get_char() == '$') return compute_name(TokenType.REFERENCE, true);

        if (is_name())
            return compute_name(TokenType.NAME, false);   
        
        TokenType operand = OPERAND_TRIE.collision(this.file.sourceList, idx);
        if (operand != null) {
            prepare();

            for (int d = 0; d < operand.fixed_delta; d ++)
                chop_char();
            
            return make_token(operand);
        }

        if (log) {
            prepare();
            System.out.println("[PluginLang] Could not read element " + make_token(TokenType.NAME).getMessage());
        }
        return null;
    }

    public Token[] build () {
        Token token;
        ArrayList<Token> tokens = new ArrayList<Token>();
        while ((token = this.next_token()) != null)
            tokens.add(token);
        
        Token[] result = new Token[tokens.size()];
        for (int i = 0; i < tokens.size(); i ++)
            result[i] = tokens.get(i);

        return result;
    }
}
