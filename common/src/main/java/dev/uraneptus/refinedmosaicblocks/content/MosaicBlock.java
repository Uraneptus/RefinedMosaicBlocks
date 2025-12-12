package dev.uraneptus.refinedmosaicblocks.content;

import com.mojang.serialization.MapCodec;
import dev.uraneptus.refinedmosaicblocks.RMBConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.stream.Collectors;

public class MosaicBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<MosaicBlock> CODEC = simpleCodec(MosaicBlock::new);
    public static final EnumProperty<MosaicColor> COLOR = EnumProperty.create("color", MosaicColor.class);

    public MosaicBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(COLOR, MosaicColor.WHITE));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pState.hasProperty(COLOR) && !pLevel.isClientSide()) {
            ItemStack itemInHand = pPlayer.getItemInHand(pHand);
            if (MosaicColor.isDyeItem(itemInHand)) {
                if (itemInHand.getItem() != pState.getValue(COLOR).getDyeItem()) {
                    pLevel.setBlock(pPos, pState.setValue(COLOR, MosaicColor.getColorFromItem(itemInHand)), Block.UPDATE_ALL);
                    if (pLevel.random.nextFloat() < (20 /*TODO config here*/ / 100.0f) && !pPlayer.isCreative()) {
                        pPlayer.getItemInHand(pHand).shrink(1);
                        pLevel.playSound(null, pPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 0.5f, 1.0F);
                        for(int i = 0; i < 7; ++i) {
                            double d0 = pLevel.random.nextGaussian() * 0.02D;
                            double d1 = pLevel.random.nextGaussian() * 0.02D;
                            double d2 = pLevel.random.nextGaussian() * 0.02D;
                            ((ServerLevel)pLevel).sendParticles(ParticleTypes.SMOKE, pPos.getX() + 0.5D, pPos.getY() + 1.2D, pPos.getZ() + 0.5D, 1,  d0, d1, d2, 0d);
                        }
                    } else {
                        pLevel.playSound(null, pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.5f, 1.0F);
                    }
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack itemStack = new ItemStack(this);
        MosaicColor color = state.getValue(COLOR);
        itemStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(COLOR, color));
        return itemStack;
    }

    @Override
    protected BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (pState.hasProperty(COLOR) && !pLevel.isClientSide()) {
            if (pNeighborState.getBlock() == Blocks.WET_SPONGE) {
                return pState.setValue(COLOR, MosaicColor.WHITE);
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        BlockItemStateProperties blockItemStateProperties = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        if (blockItemStateProperties.isEmpty()) return;

        if (blockItemStateProperties.get(COLOR) != null) {
            MosaicColor color = blockItemStateProperties.get(COLOR);
            tooltipComponents.add(Component.literal(RMBConstants.createTranslation(color.getSerializedName())).withColor(color.getDecimalColor()));
        }
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, COLOR);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        if (pBlockState.hasProperty(COLOR)) {
            return pBlockState.getValue(COLOR).getIndexNumber();
        }
        return 0;
    }

    public static Iterable<MosaicBlock> getMosaicBlocks() {
        return BuiltInRegistries.BLOCK.stream().filter(block -> RMBConstants.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()) && block instanceof MosaicBlock).map(block -> (MosaicBlock) block).collect(Collectors.toList());
    }
}
