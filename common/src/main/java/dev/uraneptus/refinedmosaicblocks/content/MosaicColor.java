package dev.uraneptus.refinedmosaicblocks.content;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
    CORAL("coral", 18, "dye_depot:coral_dye"),
    INDIGO("indigo", 19, "dye_depot:indigo_dye"),
    NAVY("navy", 20, "dye_depot:navy_dye"),
    SLATE("slate", 21, "dye_depot:slate_dye"),
    OLIVE("olive", 22, "dye_depot:olive_dye"),
    AMBER("amber", 23, "dye_depot:amber_dye"),
    BEIGE("beige", 24, "dye_depot:beige_dye"),
    TEAL("teal", 25, "dye_depot:teal_dye"),
    MINT("mint", 26, "dye_depot:mint_dye"),
    AQUA("aqua", 27, "dye_depot:aqua_dye"),
    VERDANT("verdant", 28, "dye_depot:verdant_dye"),
    FOREST("forest", 29, "dye_depot:forest_dye"),
    GINGER("ginger", 30, "dye_depot:ginger_dye"),
    TAN("tan", 31, "dye_depot:tan_dye");

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
