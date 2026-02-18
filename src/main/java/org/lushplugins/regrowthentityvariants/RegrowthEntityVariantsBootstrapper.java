package org.lushplugins.regrowthentityvariants;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.data.ChickenVariantRegistryEntry;
import io.papermc.paper.registry.data.CowVariantRegistryEntry;
import io.papermc.paper.registry.data.PigVariantRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.*;
import org.jspecify.annotations.NonNull;
import org.lushplugins.regrowthentityvariants.config.ConfigManager;
import org.lushplugins.regrowthentityvariants.handler.VariantConstructor;

public final class RegrowthEntityVariantsBootstrapper implements PluginBootstrap {

    @Override
    public void bootstrap(@NonNull BootstrapContext context) {
        ConfigManager config = new ConfigManager(context);
        VariantConstructor constructor = new VariantConstructor(context, config);

        constructor.simpleVariants(RegistryEvents.CAT_VARIANT.compose(), "cat", CatVariantKeys::create, texture -> b -> b.clientTextureAsset(texture));
        constructor.simpleVariants(RegistryEvents.FROG_VARIANT.compose(), "frog", FrogVariantKeys::create, texture -> b -> b.clientTextureAsset(texture));
        constructor.simpleVariants(RegistryEvents.CHICKEN_VARIANT.compose(), "chicken", ChickenVariantKeys::create, texture -> b -> b.clientTextureAsset(texture)
            .model(ChickenVariantRegistryEntry.Model.NORMAL));
        constructor.simpleVariants(RegistryEvents.COW_VARIANT.compose(), "cow", CowVariantKeys::create, texture -> b -> b.clientTextureAsset(texture)
            .model(CowVariantRegistryEntry.Model.NORMAL));
        constructor.simpleVariants(RegistryEvents.PIG_VARIANT.compose(), "pig", PigVariantKeys::create, texture -> b -> b.clientTextureAsset(texture)
            .model(PigVariantRegistryEntry.Model.NORMAL));
    }
}
