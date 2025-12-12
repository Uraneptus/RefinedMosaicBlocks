package dev.uraneptus.refinedmosaicblocks.content;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;

public enum MosaicColor implements StringRepresentable {
    WHITE("white", 0, Items.WHITE_DYE, 16777215),
    ORANGE("orange", 1, Items.ORANGE_DYE, 16738335),
    MAGENTA("magenta", 2, Items.MAGENTA_DYE, 16711935),
    LIGHT_BLUE("light_blue", 3, Items.LIGHT_BLUE_DYE, 10141901),
    YELLOW("yellow", 4, Items.YELLOW_DYE, 16776960),
    LIME("lime", 5, Items.LIME_DYE, 12582656),
    PINK("pink", 6, Items.PINK_DYE, 16738740),
    GRAY("gray", 7, Items.GRAY_DYE, 8421504),
    LIGHT_GRAY("light_gray", 8, Items.LIGHT_GRAY_DYE, 13882323),
    CYAN("cyan", 9, Items.CYAN_DYE, 65535),
    PURPLE("purple", 10, Items.PURPLE_DYE, 10494192),
    BLUE("blue", 11, Items.BLUE_DYE, 255),
    BROWN("brown", 12, Items.BROWN_DYE, 9127187),
    GREEN("green", 13, Items.GREEN_DYE, 65280),
    RED("red", 14, Items.RED_DYE, 16711680),
    BLACK("black", 15, Items.BLACK_DYE, 2631720);

    private final String name;
    private final int indexNumber;
    private final Item dyeItem;
    private final int decimalColor;

    MosaicColor(String name, int indexNumber, Item dyeItem, int decimalColor) {
        this.name = name;
        this.indexNumber = indexNumber;
        this.dyeItem = dyeItem;
        this.decimalColor = decimalColor;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getIndexNumber() {
        return this.indexNumber;
    }

    public Item getDyeItem() {
        return this.dyeItem;
    }

    public static boolean isDyeItem(ItemStack item) {
        return item.is(RMBConstants.DYES_TAG);
    }

    public int getDecimalColor() {
        return decimalColor;
    }

    public static MosaicColor getColorFromItem(ItemStack item) {
        return Arrays.stream(MosaicColor.values()).filter(color -> color.getDyeItem() == item.getItem()).toList().getFirst();
    }
}
