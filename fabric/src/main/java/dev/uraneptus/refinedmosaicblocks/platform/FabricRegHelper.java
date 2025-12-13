package dev.uraneptus.refinedmosaicblocks.platform;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.platform.services.RegHelper;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FabricRegHelper implements RegHelper {
    @Override
    public <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block) {
        B object = block.get();
        Registry.register(BuiltInRegistries.BLOCK, RMBConstants.modPrefix(id), object);
        return () -> object;
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item) {
        I object = item.get();
        Registry.register(BuiltInRegistries.ITEM, RMBConstants.modPrefix(id), object);
        return () -> object;
    }

    @Override
    public <T extends GameRules.Value<T>> GameRules.Key<T> registerGameRule(String name, GameRules.Category category, GameRules.Type<T> type) {
        return GameRuleRegistry.register(name, category, type);
    }

    @Override
    public GameRules.Type<GameRules.IntegerValue> createIntRule(int pDefaultValue, int pMin, int pMax, BiConsumer<MinecraftServer, GameRules.IntegerValue> pChangeListener) {
        return GameRuleFactory.createIntRule(pDefaultValue, pMin, pMax, pChangeListener);
    }
}
