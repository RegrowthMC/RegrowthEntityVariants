package org.lushplugins.regrowthentityvariants.config;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.regrowthentityvariants.util.FileUtils;

import java.util.*;

public class ConfigManager {
    private final String defaultNamespace;
    private final Map<String, List<String>> newVariants = new HashMap<>();

    public ConfigManager(BootstrapContext context) {
        FileUtils.saveResource(context, "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(context.getDataDirectory().resolve("config.yml").toFile());

        this.defaultNamespace = config.getString("default-namespace", "regrowth");

        ConfigurationSection variantSection = config.getConfigurationSection("entity-variants");
        Set<String> entityTypes = variantSection.getKeys(false);
        for (String entityType : entityTypes) {
            List<String> newVariants = variantSection.getStringList(entityType);
            this.newVariants.put(entityType, newVariants);
        }
    }

    public String getDefaultNamespace() {
        return defaultNamespace;
    }

    public @NotNull List<String> getNewVariants(String entityType) {
        return newVariants.getOrDefault(entityType, Collections.emptyList());
    }
}
