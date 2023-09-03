package net.mcmodded.rpgmobclasses.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcmodded.rpgmobclasses.entity.MimicEntity;

public class MimicModel extends GeoModel<MimicEntity> {
	@Override
	public ResourceLocation getAnimationResource(MimicEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "animations/mimic.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MimicEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "geo/mimic.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MimicEntity entity) {
		return new ResourceLocation("rpg_mob_classes", "textures/entities/" + entity.getTexture() + ".png");
	}

}
