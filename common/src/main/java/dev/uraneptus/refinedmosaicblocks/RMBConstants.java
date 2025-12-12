package dev.uraneptus.refinedmosaicblocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RMBConstants {

    public static final String MOD_ID = "refinedmosaicblocks";
    public static final String MOD_NAME = "RefinedMosaicBlocks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final TagKey<Item> DYES_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dyes"));

    public static ResourceLocation modPrefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String createTranslation(String path) {
        final StringBuilder builder = new StringBuilder();

        for (String part : path.split("_")) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return builder.toString();
    }
}
