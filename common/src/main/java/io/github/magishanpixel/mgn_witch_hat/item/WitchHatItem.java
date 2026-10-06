package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.block.WitchHatBlockEntity;
import io.github.magishanpixel.mgn_witch_hat.client.tooltip.WitchHatTooltip;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorPlacement;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class WitchHatItem extends BlockItem implements Equipable {
    public WitchHatItem(Block block,Properties properties) {
        super(block,properties);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<DataDecor> stackList = stack.has(ModDataComponents.DECOR_TYPES.value()) ? List.copyOf(stack.get(ModDataComponents.DECOR_TYPES.value()).values()) : List.of();
        return stackList.isEmpty() ? Optional.empty() : Optional.of(new WitchHatTooltip.DisplayStacks(stackList));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> comp, TooltipFlag tooltipFlag) {
        if (stack.has(ModDataComponents.DECOR_TYPES.value())) {
            comp.add(Component.translatable("item.mgn_witch_hat.desc.usage").withStyle(ChatFormatting.YELLOW)
                    .append(Component.literal(" ")).append(
                            Component.translatable("item.mgn_witch_hat.witch_hat.usage").withStyle(ChatFormatting.WHITE)
                    ));
        }

    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState state) {
        if (level.getBlockEntity(pos) instanceof WitchHatBlockEntity blockEntity) {
            blockEntity.setHatStack(stack.copy());
            return true;
        }
        return false;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.isCrouching()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.has(ModDataComponents.DECOR_TYPES.value())) {
                Map<DecorType, DataDecor> map = stack.get(ModDataComponents.DECOR_TYPES.value());

                for (DataDecor data : map.values()) {
                    player.addItem(data.stack().copy());
                }

                stack.remove(ModDataComponents.DECOR_TYPES.value());

                level.playSound(null, player, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.PLAYERS, 1, 1);

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }



}
