package dev.uraneptus.refinedmosaicblocks.data;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.content.MosaicBlock;
import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class RMBItemModelProvider extends ItemModelProvider {

    public RMBItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RMBConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        MosaicBlock.getMosaicBlocks().forEach(this::mosaicBlockItem);
        basicBlockItem(RMBRegistries.MOSAIC_BLOCK.get());
    }

    private void mosaicBlockItem(Block blockForItem) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(blockForItem).getPath(), modBlockLocation("white_" + BuiltInRegistries.BLOCK.getKey(blockForItem).getPath().replace("_mosaic_block", "")));
    }

    private void basicBlockItem(Block blockForItem) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(blockForItem).getPath(), modBlockLocation(BuiltInRegistries.BLOCK.getKey(blockForItem).getPath()));
    }

    public static ResourceLocation modBlockLocation(String path) {
        return RMBConstants.modPrefix(ModelProvider.BLOCK_FOLDER + "/" + path);
    }
}
