package com.TheGreatChicken.PluginLang;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("--- PluginLoader ---");
    }
    @Override
    public void onDisable() {  }
}
