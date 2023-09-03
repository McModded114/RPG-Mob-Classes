package net.mcmodded.rpgmobclasses.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcmodded.rpgmobclasses.entity.GoblinEntity;

public class GoblinModel extends GeoModel<GoblinEntity> {
	@Override
	public ResourceLocation getAnimationResource(GoblinEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "animations/goblin.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(GoblinEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "geo/goblin.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(GoblinEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "textures/entities/" + entity.getTexture() + ".png");
	}

}
