package org.lushplugins.regrowthentityvariants;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthEntityVariants extends JavaPlugin {
    private static RegrowthEntityVariants plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthEntityVariants getInstance() {
        return plugin;
    }
}
