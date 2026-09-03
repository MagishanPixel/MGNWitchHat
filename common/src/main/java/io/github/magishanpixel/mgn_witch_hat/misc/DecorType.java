package io.github.magishanpixel.mgn_witch_hat.misc;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Predicate;

public enum DecorType implements StringRepresentable {
    SKULL("skull"),
    LANTERN("lantern")
    ;
    private final String name;

    private static ImmutableMap<Item, DecorType> ITEM_MAP = null;

    DecorType(String name) {
        this.name = name;

    }

    public static void mapInit() {
        ImmutableMap.Builder<Item, DecorType> m = new ImmutableMap.Builder<>();

        m.put(Items.SKELETON_SKULL, SKULL);

        ITEM_MAP = m.build();
    }

    public static DecorType getType(ItemStack stack) {
        if (ITEM_MAP != null) {
            Item item = stack.getItem();

            if (ITEM_MAP.containsKey(item)) {
                return ITEM_MAP.get(item);
            }
        }

        return null;
    }

    public static final StringRepresentable.EnumCodec<DecorType> CODEC = StringRepresentable.fromEnum(DecorType::values);


    @Override
    public String getSerializedName() {
        return name;
    }
}