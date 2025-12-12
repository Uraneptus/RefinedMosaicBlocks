package dev.uraneptus.refinedmosaicblocks.platform.services;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface RegHelper {

    <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block);
    <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item);
}
