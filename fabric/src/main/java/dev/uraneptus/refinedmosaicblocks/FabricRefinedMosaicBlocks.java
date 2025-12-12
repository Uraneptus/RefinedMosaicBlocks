package dev.uraneptus.refinedmosaicblocks;

import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class FabricRefinedMosaicBlocks implements ModInitializer {

    @Override
    public void onInitialize() {
        RMBCommon.init();
        RMBRegistries.initRegIndex();
        addToItemGroup();
    }



    private static void addToItemGroup() {
        RMBRegistries.REG_INDEX.forEach(item -> {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
                entries.accept(item);
            });
        });
    }
}
