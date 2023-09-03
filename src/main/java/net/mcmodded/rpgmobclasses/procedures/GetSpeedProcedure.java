package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetSpeedProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Speed: " + Math.round(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getBaseValue());
	}
}
