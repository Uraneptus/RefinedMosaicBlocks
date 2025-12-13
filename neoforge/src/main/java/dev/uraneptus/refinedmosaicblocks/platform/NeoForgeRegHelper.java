package dev.uraneptus.refinedmosaicblocks.platform;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.platform.services.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegHelper implements RegHelper {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, RMBConstants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, RMBConstants.MOD_ID);

    @Override
    public <B extends Block> Supplier<B> registerBlock(String id, Supplier<B> block) {
        return BLOCKS.register(id, block);
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String id, Supplier<I> item) {
        return ITEMS.register(id, item);
    }

    @Override
    public <T extends GameRules.Value<T>> GameRules.Key<T> registerGameRule(String name, GameRules.Category category, GameRules.Type<T> type) {
        return GameRules.register(name, category, type);
    }
}
