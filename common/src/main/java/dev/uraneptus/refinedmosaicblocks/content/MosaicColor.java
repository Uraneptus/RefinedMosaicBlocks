package dev.uraneptus.refinedmosaicblocks.content;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum MosaicColor implements StringRepresentable {
    WHITE("white", 0, "minecraft:white_dye"),
    ORANGE("orange", 1, "minecraft:orange_dye"),
    MAGENTA("magenta", 2, "minecraft:magenta_dye"),
    LIGHT_BLUE("light_blue", 3, "minecraft:light_blue_dye"),
    YELLOW("yellow", 4, "minecraft:yellow_dye"),
    LIME("lime", 5, "minecraft:lime_dye"),
    PINK("pink", 6, "minecraft:pink_dye"),
    GRAY("gray", 7, "minecraft:gray_dye"),
    LIGHT_GRAY("light_gray", 8, "minecraft:light_gray_dye"),
    CYAN("cyan", 9, "minecraft:cyan_dye"),
    PURPLE("purple", 10, "minecraft:purple_dye"),
    BLUE("blue", 11, "minecraft:blue_dye"),
    BROWN("brown", 12, "minecraft:brown_dye"),
    GREEN("green", 13, "minecraft:green_dye"),
    RED("red", 14, "minecraft:red_dye"),
    BLACK("black", 15, "minecraft:black_dye"),
    MAROON("maroon", 16, "dye_depot:maroon_dye"),
    ROSE("rose", 17, "dye_depot:rose_dye"),
    ;

    private final String name;
    private final int indexNumber;
    private final String dyeItemName;

    MosaicColor(String name, int indexNumber, String dyeItemName) {
        this.name = name;
        this.indexNumber = indexNumber;
        this.dyeItemName = dyeItemName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getIndexNumber() {
        return this.indexNumber;
    }

    public String getDyeItemName() {
        return this.dyeItemName;
    }

    public boolean isSameDye(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().equals(this.getDyeItemName());
    }

    public static boolean isDyeItem(ItemStack item) {
        return item.is(RMBConstants.DYES_TAG);
    }

    public static MosaicColor getColorFromItem(ItemStack item) {
        for (MosaicColor color : MosaicColor.values()) {
            if (BuiltInRegistries.ITEM.getKey(item.getItem()).toString().equals(color.getDyeItemName())) {
                return color;
            }
        }
        return WHITE;
    }
}
