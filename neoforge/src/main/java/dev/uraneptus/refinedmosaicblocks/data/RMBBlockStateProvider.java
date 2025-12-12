package dev.uraneptus.refinedmosaicblocks.data;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.content.MosaicBlock;
import dev.uraneptus.refinedmosaicblocks.content.MosaicColor;
import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;

public class RMBBlockStateProvider extends BlockStateProvider {
    public RMBBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RMBConstants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        MosaicBlock.getMosaicBlocks().forEach(this::patternedMosaicBlock);
        mosaicBlock(RMBRegistries.MOSAIC_BLOCK.get());
    }

    private void patternedMosaicBlock(Block block) {
        getVariantBuilder(block).forAllStates(blockState -> {
            String pattern = name(block).replace("_mosaic_block", "");
            MosaicColor color = blockState.getValue(MosaicBlock.COLOR);

            String name = color.name().toLowerCase() + "_" + pattern;

            ModelFile modelFile = models().withExistingParent(name, name(Blocks.WHITE_GLAZED_TERRACOTTA))
                    .texture("pattern", modBlockLocation(name));

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .build();
        });
    }

    private void mosaicBlock(Block block) {
        getVariantBuilder(block).forAllStates(blockState -> {
            ModelFile[] files = new ModelFile[4];
            for (int i = 0; i <= 3; i++) {
                int rotNum = 90 * i;
                String suffix = rotNum != 0 ? "_" + rotNum : "";
                ModelFile modelFile = models()
                        .withExistingParent(name(block) + suffix, name(Blocks.WHITE_GLAZED_TERRACOTTA))
                        .texture("pattern", modBlockLocation(name(block) + suffix));
                files[i] = modelFile;
            }

            return ConfiguredModel.builder()
                    .modelFile(files[0])
                    .nextModel().modelFile(files[1])
                    .nextModel().modelFile(files[2])
                    .nextModel().modelFile(files[3])
                    .build();
        });
    }

    public static String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public static ResourceLocation modBlockLocation(String path) {
        return RMBConstants.modPrefix(ModelProvider.BLOCK_FOLDER + "/" + path);
    }
}
