package dev.uraneptus.refinedmosaicblocks.data;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.content.MosaicBlock;
import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class RMBRecipeProvider extends RecipeProvider {

    public RMBRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(output, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        mosaicRecipe(recipeOutput);
        MosaicBlock.getMosaicBlocks().forEach(block -> patternedMosaicRecipe(block, recipeOutput));
    }

    private static void patternedMosaicRecipe(ItemLike result, RecipeOutput consumer) {
        ItemLike input = RMBRegistries.MOSAIC_BLOCK.get();
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, result)
                .group("mosaic_block")
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, RMBConstants.modPrefix(getItemName(result)));
    }

    private static void mosaicRecipe(RecipeOutput consumer) {
        ItemLike result = RMBRegistries.MOSAIC_BLOCK.get();
        ItemLike diorite = Items.DIORITE;
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .define('D', diorite)
                .define('G', Items.GLASS)
                .pattern("DG")
                .pattern("GD")
                .unlockedBy(getHasName(diorite), has(diorite))
                .save(consumer, RMBConstants.modPrefix(getItemName(result)));
    }
}
