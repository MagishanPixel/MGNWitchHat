package io.github.magishanpixel.mgn_witch_hat;

import net.minecraft.resources.ResourceLocation;
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
}