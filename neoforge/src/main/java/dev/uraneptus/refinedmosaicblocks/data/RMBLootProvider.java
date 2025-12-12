package dev.uraneptus.refinedmosaicblocks.data;

import dev.uraneptus.refinedmosaicblocks.content.MosaicBlock;
import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import dev.uraneptus.refinedmosaicblocks.platform.NeoForgeRegHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class RMBLootProvider extends LootTableProvider {

    public RMBLootProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, Collections.emptySet(), List.of(new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)), pRegistries);
    }

    private static class BlockLoot extends BlockLootSubProvider {

        protected BlockLoot(HolderLookup.Provider pRegistries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return NeoForgeRegHelper.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
        }

        @Override
        protected void generate() {
            this.dropSelf(RMBRegistries.MOSAIC_BLOCK.get());
            MosaicBlock.getMosaicBlocks().forEach(this::dropSelf);
        }
    }
}
