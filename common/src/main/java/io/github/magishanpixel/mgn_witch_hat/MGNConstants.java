package io.github.magishanpixel.mgn_witch_hat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MGNConstants {
	public static final String MOD_ID = "mgn_witch_hat";
	public static final String MOD_NAME = "MGN's Witch Hat";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation newId(String name) {
		return ResourceLocation.fromNamespaceAndPath(MGNConstants.MOD_ID, name);
	}

	public static ResourceLocation getTexture(String name) {
		return newId("textures/entity/hat/" + name + ".png");
	}

	public static class ItemTags {
		public static final TagKey<Item> WITCH_HAT_DECOR = of("witch_hat_decor");

		public static class Decor {
			public static final TagKey<Item> SKULLS = decorOf("skulls");

			private static TagKey<Item> decorOf(String name) {
				return of("decor/" + name);
			}
		}

		private static TagKey<Item> of(String name) {
			return TagKey.create(Registries.ITEM, newId(name));
		}

	}

}