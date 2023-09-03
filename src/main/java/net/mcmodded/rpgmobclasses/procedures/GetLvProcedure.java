package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

public class GetLvProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Lvl: " + Math.round(((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.LVL.get()).getBaseValue());
	}
}
