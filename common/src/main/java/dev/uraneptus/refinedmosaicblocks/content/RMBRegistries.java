package dev.uraneptus.refinedmosaicblocks.content;

import dev.uraneptus.refinedmosaicblocks.platform.RMBServices;
import dev.uraneptus.refinedmosaicblocks.platform.services.RegHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.function.Supplier;

public class RMBRegistries {
    private static final RegHelper REG = RMBServices.REG;
    public static List<Block> REG_INDEX;

    public static final Supplier<Block> MOSAIC_BLOCK = registerBlockAndItem("mosaic_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA)));
    public static final Supplier<Block> ARROW_MOSAIC_BLOCK = registerMosaicBlock("arrow_mosaic_block");
    public static final Supplier<Block> BOWL_MOSAIC_BLOCK = registerMosaicBlock("bowl_mosaic_block");
    public static final Supplier<Block> CORNER_MOSAIC_BLOCK = registerMosaicBlock("corner_mosaic_block");
    public static final Supplier<Block> CRESCENT_MOSAIC_BLOCK = registerMosaicBlock("crescent_mosaic_block");
    public static final Supplier<Block> DOWNWARD_POINT_MOSAIC_BLOCK = registerMosaicBlock("downward_point_mosaic_block");
    public static final Supplier<Block> DOWNWARD_SLOPE_MOSAIC_BLOCK = registerMosaicBlock("downward_slope_mosaic_block");
    public static final Supplier<Block> FOLD_MOSAIC_BLOCK = registerMosaicBlock("fold_mosaic_block");
    public static final Supplier<Block> FULL_MOSAIC_BLOCK = registerMosaicBlock("full_mosaic_block");
    public static final Supplier<Block> HALF_MOSAIC_BLOCK = registerMosaicBlock("half_mosaic_block");
    public static final Supplier<Block> QUARTER_MOSAIC_BLOCK = registerMosaicBlock("quarter_mosaic_block");
    public static final Supplier<Block> ROUND_MOSAIC_BLOCK = registerMosaicBlock("round_mosaic_block");
    public static final Supplier<Block> STAIR_MOSAIC_BLOCK = registerMosaicBlock("stair_mosaic_block");
    public static final Supplier<Block> TRIANGLE_MOSAIC_BLOCK = registerMosaicBlock("triangle_mosaic_block");
    public static final Supplier<Block> UPWARD_POINT_MOSAIC_BLOCK = registerMosaicBlock("upward_point_mosaic_block");
    public static final Supplier<Block> UPWARD_SLOPE_MOSAIC_BLOCK = registerMosaicBlock("upward_slope_mosaic_block");
    public static final Supplier<Block> WEDGE_MOSAIC_BLOCK = registerMosaicBlock("wedge_mosaic_block");

    public static final GameRules.Key<GameRules.IntegerValue> DYE_CONSUME_CHANCE = REG.registerGameRule("dye_consume_chance", GameRules.Category.PLAYER, GameRules.IntegerValue.create(20, 0, 100, (server, integerValue) -> {}));

    public static void init() {}

    static Supplier<Block> registerBlockAndItem(String id, Supplier<Block> blockSupplier) {
        Supplier<Block> block = REG.registerBlock(id, blockSupplier);
        REG.registerItem(id, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    static Supplier<Block> registerMosaicBlock(String id) {
        return registerBlockAndItem(id, () -> new MosaicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA)));
    }

    public static void initRegIndex() {
        REG_INDEX = List.of(
                MOSAIC_BLOCK.get(),
                ARROW_MOSAIC_BLOCK.get(),
                BOWL_MOSAIC_BLOCK.get(),
                CORNER_MOSAIC_BLOCK.get(),
                CRESCENT_MOSAIC_BLOCK.get(),
                DOWNWARD_POINT_MOSAIC_BLOCK.get(),
                DOWNWARD_SLOPE_MOSAIC_BLOCK.get(),
                FOLD_MOSAIC_BLOCK.get(),
                FULL_MOSAIC_BLOCK.get(),
                HALF_MOSAIC_BLOCK.get(),
                QUARTER_MOSAIC_BLOCK.get(),
                ROUND_MOSAIC_BLOCK.get(),
                STAIR_MOSAIC_BLOCK.get(),
                TRIANGLE_MOSAIC_BLOCK.get(),
                UPWARD_POINT_MOSAIC_BLOCK.get(),
                UPWARD_SLOPE_MOSAIC_BLOCK.get(),
                WEDGE_MOSAIC_BLOCK.get()
        );
    }
}
