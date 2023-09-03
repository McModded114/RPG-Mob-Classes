package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

public class GetStrengthProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Strength: " + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.STRENGTH.get()).getBaseValue());
	}
}
