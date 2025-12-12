package dev.uraneptus.refinedmosaicblocks.data;

import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import dev.uraneptus.refinedmosaicblocks.content.MosaicBlock;
import dev.uraneptus.refinedmosaicblocks.content.RMBRegistries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class RMBLangProvider extends LanguageProvider {

    public RMBLangProvider(PackOutput output) {
        super(output, RMBConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        MosaicBlock.getMosaicBlocks().forEach(block -> {
            add(block, RMBConstants.createTranslation(BuiltInRegistries.BLOCK.getKey(block).getPath()));
        });
        add(RMBRegistries.MOSAIC_BLOCK.get(), "Mosaic Block");
    }
}
