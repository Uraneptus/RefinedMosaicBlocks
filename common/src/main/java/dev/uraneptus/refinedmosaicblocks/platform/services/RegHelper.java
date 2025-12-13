package dev.uraneptus.refinedmosaicblocks.platform.services;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface RegHelper {

    <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block);
    <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item);
    <T extends GameRules.Value<T>> GameRules.Key<T> registerGameRule(String name, GameRules.Category category, GameRules.Type<T> type);
    GameRules.Type<GameRules.IntegerValue> createIntRule(int pDefaultValue, int pMin, int pMax, BiConsumer<MinecraftServer, GameRules.IntegerValue> pChangeListener);
}
