package dev.uraneptus.refinedmosaicblocks.platform;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.platform.services.RegHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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
}
