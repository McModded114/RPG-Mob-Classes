package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

public class GetXpProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Xp: " + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.XP.get()).getBaseValue());
	}
}
