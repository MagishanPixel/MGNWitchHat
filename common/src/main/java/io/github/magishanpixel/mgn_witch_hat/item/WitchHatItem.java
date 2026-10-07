package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.client.tooltip.WitchHatTooltip;
import io.github.magishanpixel.mgn_witch_hat.entity.WitchHatDisplayEntity;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorPlacement;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.blay09.mods.balm.api.Balm;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class WitchHatItem extends Item implements Equipable {
    public WitchHatItem(Properties properties) {
        super(properties);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<DataDecor> stackList = stack.has(ModDataComponents.DECOR_TYPES.value()) ? List.copyOf(stack.get(ModDataComponents.DECOR_TYPES.value()).values()) : List.of();
        return stackList.isEmpty() ? Optional.empty() : Optional.of(new WitchHatTooltip.DisplayStacks(stackList));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> comp, TooltipFlag tooltipFlag) {
       if (Screen.hasShiftDown()) {
           if (stack.has(ModDataComponents.DECOR_TYPES.value())) {
               comp.add(Component.translatable("item.mgn_witch_hat.witch_hat.key.removedecor").withStyle(ChatFormatting.YELLOW)
                       .append(Component.literal(" ")).append(
                               Component.translatable("item.mgn_witch_hat.witch_hat.desc.removedecor").withStyle(ChatFormatting.WHITE)
                       ));
           }

           comp.add(Component.translatable("item.mgn_witch_hat.witch_hat.key.placeblock").withStyle(ChatFormatting.YELLOW).append(
                   Component.literal(" ").append(
                           Component.translatable("item.mgn_witch_hat.witch_hat.desc.placeblock").withStyle(ChatFormatting.WHITE)
                   )
           ));
       } else {
            comp.add(Component.literal("[ ").withStyle(ChatFormatting.GRAY).append(
                    Component.translatable("item.mgn_witch_hat.witch_hat.shift").withStyle(ChatFormatting.DARK_GRAY))
                    .append(" ]").withStyle(ChatFormatting.GRAY)
            );
       }
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

    @Override
    public InteractionResult useOn(UseOnContext c) {
        if (c.getPlayer().isCrouching()) {
            BlockPlaceContext context = new BlockPlaceContext(c);
            Level level = context.getLevel();
            BlockState bottomState = level.getBlockState(context.getClickedPos().below());

            if (bottomState.isSolid() || DiodeBlock.isDiode(bottomState)) {
                if (!level.isClientSide()) {
                    ItemStack stack = context.getPlayer().getItemInHand(context.getHand());
                    WitchHatDisplayEntity displayEntity = new WitchHatDisplayEntity(level, context.getClickedPos(), stack.copy(), RotationSegment.convertToSegment(context.getRotation()));

                    stack.consume(1, context.getPlayer());

                    level.addFreshEntity(displayEntity);
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }

        }

        return InteractionResult.PASS;
    }
}
