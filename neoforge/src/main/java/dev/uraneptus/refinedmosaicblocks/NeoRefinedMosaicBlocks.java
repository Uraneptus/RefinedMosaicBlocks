package dev.uraneptus.refinedmosaicblocks;


import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import dev.uraneptus.refinedmosaicblocks.data.*;
import dev.uraneptus.refinedmosaicblocks.platform.NeoForgeRegHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.concurrent.CompletableFuture;

@Mod(RMBConstants.MOD_ID)
public class NeoRefinedMosaicBlocks {

    public NeoRefinedMosaicBlocks(IEventBus eventBus) {
        RMBCommon.init();
        NeoForgeRegHelper.BLOCKS.register(eventBus);
        NeoForgeRegHelper.ITEMS.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        RMBRegistries.initRegIndex();
    }

    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new RMBLangProvider(packOutput));
        generator.addProvider(includeClient, new RMBBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new RMBItemModelProvider(packOutput, fileHelper));

        generator.addProvider(includeServer, new RMBLootProvider(packOutput, lookupProvider));
        generator.addProvider(includeServer, new RMBRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(includeServer, new RMBBlockTagsProvider(packOutput, lookupProvider, fileHelper));
    }

    @Mod(value = RMBConstants.MOD_ID, dist = Dist.CLIENT)
    public static class Client {

        public Client(IEventBus bus, ModContainer modContainer) {
            bus.addListener(this::buildTabContents);
        }

        private void buildTabContents(BuildCreativeModeTabContentsEvent event) {
            ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
            RMBRegistries.REG_INDEX.forEach(item -> {
                if (tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
                    event.accept(item, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            });
        }
    }
}
