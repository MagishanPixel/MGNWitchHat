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

    private static final String ITEM_TRANSLATE = "item.mgn_witch_hat.";

    private static Component createComp(String descName, String v) {
        return Component.translatable(ITEM_TRANSLATE + "desc." + descName).append(": ").append(Component.translatable(ITEM_TRANSLATE + v));

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> c, TooltipFlag tooltipFlag) {
        /*
        DyeColor dyeHat = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? stack.get(ModDataComponents.WITCH_HAT_COLOR.value()) : null;
        BuckleType buckleType = stack.has(ModDataComponents.BUCKLE_TYPE.value()) ? stack.get(ModDataComponents.BUCKLE_TYPE.value()) : null;

        if (dyeHat != null) {
            c.add(createComp("dye", "col." + dyeHat.getSerializedName()));
        }

        if (buckleType != null) {
            c.add(createComp("buckle", "buckle." + buckleType.getSerializedName()));
        }

        if (stack.get(ModDataComponents.HAS_BAND.value())) {
            String str = "default";

            if (stack.has(ModDataComponents.BAND_COLOR.value())) {
                DyeColor v = stack.get(ModDataComponents.BAND_COLOR.value());
                str = v.getSerializedName();
            }

            c.add(createComp("hat_band", "col." + str));
        }

        */
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<ItemStack> stackList = new ArrayList<>();

        DyeColor dyeHat = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? stack.get(ModDataComponents.WITCH_HAT_COLOR.value()) : null;
        BuckleType buckleType = stack.has(ModDataComponents.BUCKLE_TYPE.value()) ? stack.get(ModDataComponents.BUCKLE_TYPE.value()) : null;

        if (dyeHat != null) {
            stackList.add(DyeItem.byColor(dyeHat).getDefaultInstance());
        }

        if (buckleType != null) {
            stackList.add(BuckleItem.byType(buckleType).getDefaultInstance());
        }

        if (stack.get(ModDataComponents.HAS_BAND.value())) {
            if (stack.has(ModDataComponents.BAND_COLOR.value())) {
                DyeColor v = stack.get(ModDataComponents.BAND_COLOR.value());
                stackList.add(ColoredItem.byColor(ColoredItem.ItemType.HAT_BAND,v).getDefaultInstance());
            } else {
                stackList.add(ModItems.HAT_BAND.createStack());
            }
        }

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
