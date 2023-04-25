package com.TheGreatChicken.PluginLang;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import com.TheGreatChicken.PluginLang.lexer.Lexer;
import com.TheGreatChicken.PluginLang.lexer.LexerFile;
import com.TheGreatChicken.PluginLang.lexer.Token;

public class PluginLoader extends JavaPlugin {
    public static PluginLoader INSTANCE = null;

    private static boolean check_instance () {
        if (PluginLoader.INSTANCE == null) {
            PluginLoader instance = getPlugin(PluginLoader.class);
            if (instance == null) {
                System.out.println("[PluginLang] - PluginLoader instance could not be found, fatal error.");
                return false;
            }

            PluginLoader.INSTANCE = instance;

            instance.getLogger().info("WARNING, PluginLoader was not defined, might cause issues.");
        }

        return true;
    }
    public static boolean compile (String name, String content) {
        if (!check_instance()) return false;
        INSTANCE.getLogger().info("PluginLang::compile( \'" + name + "\' )");

        LexerFile file = new LexerFile(name, content);
        Lexer lexer    = new Lexer(file);

        Token[] tokens = lexer.build();

        for (Token tok : tokens)
            INSTANCE.getLogger().info(tok.toString());

        return true;
    }

    @Override
    public void onEnable() {
        getLogger().info("           PluginLang :: PluginLoader          ");
        getLogger().info("TheGreatChicken/PluginLang     MIT License 2023");
        getLogger().info("Setting up context handler                     ");
        
        PluginLoader.INSTANCE = this;

        PluginLoader.INSTANCE.getLogger().info("TGC / Compile");

        Lexer.OPERAND_TRIE.show();

        compile("<test>", "Some %names $in\n$a %chain");
    }
    @Override
    public void onDisable() {  }
}
