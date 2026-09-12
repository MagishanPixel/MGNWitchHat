package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WitchCandleItem extends Item {
    public WitchCandleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> comp, TooltipFlag tooltipFlag) {
        comp.add(Component.translatable("item.mgn_witch_hat.desc.usage").withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(" ")).append(
                        Component.translatable("item.mgn_witch_hat.witch_candle.usage").withStyle(ChatFormatting.WHITE)
                ));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.isCrouching()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (!level.isClientSide()) {
                stack.set(ModDataComponents.CANDLE_LIT.value(), !stack.get(ModDataComponents.CANDLE_LIT.value()));
                level.playSound(null, player, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1, 1);
            }

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }
        return super.use(level, player, usedHand);
    }
}
