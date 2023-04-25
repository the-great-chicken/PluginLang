package com.TheGreatChicken.PluginLang.lexer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class LexerTest {
    @Test
    public void nameTest () {
        LexerFile file = new LexerFile("<test>", "Some names in\na chain");
        Lexer    lexer = new Lexer(file);
        
        String[] words = { "Some", "names", "in", "a", "chain" };
        int local_col  = 1;
        int local_row  = 1;
        int local_idx  = 0;
        for (String str : words) {
            Token tok = lexer.next_token();

            assertEquals(str, tok.value);
            assertEquals(true, tok.type == TokenType.NAME);
            assertEquals(local_col, tok.col);
            assertEquals(local_idx, tok.idx);
            assertEquals(local_row, tok.row);
            assertEquals(true, tok.file == file);

            local_col += str.length() + 1;
            local_idx += str.length() + 1;
            if (str.length() == 2) {
                local_col = 1;
                local_row ++;
            }
        }

        assertEquals(null, lexer.next_token());
    }

    @Test
    public void mixedTest () {
        LexerFile file = new LexerFile("<test>", "Some %names $in\n$a %chain");
        Lexer    lexer = new Lexer(file);
        
        String[] words = { "Some", "%names", "$in", "$a", "%chain" };
        int local_col  = 1;
        int local_row  = 1;
        int local_idx  = 0;
        for (String str : words) {
            Token tok = lexer.next_token();
            
            String    rstr = str;
            TokenType type = TokenType.NAME;

            if (rstr.startsWith("%")) {
                rstr = rstr.substring(1);
                type = TokenType.KEYWORD;
            }
            if (rstr.startsWith("$")) {
                rstr = rstr.substring(1);
                type = TokenType.REFERENCE;
            }

            assertEquals(rstr, tok.value);
            assertEquals(type, tok.type);
            assertEquals(local_col, tok.col);
            assertEquals(local_idx, tok.idx);
            assertEquals(local_row, tok.row);
            assertEquals(true, tok.file == file);

            local_col += str.length() + 1;
            local_idx += str.length() + 1;
            if (str.length() == 3) {
                local_col = 1;
                local_row ++;
            }
        }

        assertEquals(null, lexer.next_token());
    }
    @Test
    public void testUnkownChar () {
        String s_u = "~^#£\"'`";
        for (char u : s_u.toCharArray()) {
            LexerFile file = new LexerFile("<test>", String.valueOf(u));
            Lexer    lexer = new Lexer(file);

            assertEquals(null, lexer.next_token(false));
        }
    }
    @Test
    public void operandArray () {
        String[] operands = {
            "+",
            "-",
            "*",
            "/",
            "|",
            ">",
            "<",
            ">=",
            "<=",
            "!=",
            "=="
        };
        TokenType[] types = {
            TokenType.PLUS,
            TokenType.MINUS,
            TokenType.TIMES,
            TokenType.DIVIDE,
            TokenType.PIPE,
            TokenType.GT,
            TokenType.LE,
            TokenType.GTEQ,
            TokenType.LEEQ,
            TokenType.NEQ,
            TokenType.EQ
        };
        int [] indices = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            8, 2, 7, 3, 4, 0, 10, 9, 1, 5, 6,
            1, 0, 3, 9, 10, 7, 5, 8, 2, 6, 4,
            6, 4, 7, 0, 8, 10, 1, 3, 9, 2, 5
        };

        String s = "";
        for (int id : indices) s += operands[id];

        LexerFile file = new LexerFile("<test>", String.valueOf(s));
        Lexer    lexer = new Lexer(file);

        int col = 1;
        int idx = 0;
        int lin = 1;
        int tid = 0;
        
        Token[] tokens = lexer.build();
        assertEquals(indices.length, tokens.length);

        for (Token tok : tokens) {
            assertEquals(types[indices[tid]], tok.type);
            assertEquals(null, tok.value);
            assertEquals(file, tok.file);
            assertEquals(idx, tok.idx);
            assertEquals(col, tok.col);
            assertEquals(lin, tok.row);

            idx += operands[indices[tid]].length();
            col += operands[indices[tid]].length();
            tid ++;
        }
    }
}
