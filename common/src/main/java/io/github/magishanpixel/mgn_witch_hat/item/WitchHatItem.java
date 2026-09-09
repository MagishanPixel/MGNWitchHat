package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.client.tooltip.WitchHatTooltip;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }

}
