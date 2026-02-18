package org.lushplugins.regrowthentityvariants.handler;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.client.ClientTextureAsset;
import io.papermc.paper.registry.event.RegistryComposeEvent;
import net.kyori.adventure.key.Key;
import org.lushplugins.regrowthentityvariants.config.ConfigManager;

import java.util.function.Consumer;
import java.util.function.Function;

public class VariantConstructor {
    private final LifecycleEventManager<BootstrapContext> lifecycleManager;
    private final ConfigManager config;

    public VariantConstructor(BootstrapContext context, ConfigManager config) {
        this.lifecycleManager = context.getLifecycleManager();
        this.config = config;
    }

    public <T, B extends RegistryBuilder<T>> void variants(
        LifecycleEventType.Prioritizable<BootstrapContext, RegistryComposeEvent<T, B>> registryEvent,
        String entityType,
        final Function<String, TypedKey<T>> keyConstructor,
        final Function<String, Consumer<? super B>> builder
    ) {
        lifecycleManager.registerEventHandler(registryEvent, (event -> {
            for (String variant : config.getNewVariants(entityType)) {
                event.registry().register(
                    keyConstructor.apply(variant),
                    builder.apply(variant)
                );
            }
        }));
    }

    public <T, B extends RegistryBuilder<T>> void simpleVariants(
        LifecycleEventType.Prioritizable<BootstrapContext, RegistryComposeEvent<T, B>> registryEvent,
        String entityType,
        Function<Key, TypedKey<T>> keyConstructor,
        Function<ClientTextureAsset, Consumer<? super B>> builder
    ) {
        variants(
            registryEvent,
            entityType,
            variant -> keyConstructor.apply(Key.key("%s:%s".formatted(config.getDefaultNamespace(), variant))),
            variant -> builder.apply(ClientTextureAsset.clientTextureAsset(Key.key("%s:entity/%s/%s"
                .formatted(config.getDefaultNamespace(), entityType, variant))))
        );
    }
}
