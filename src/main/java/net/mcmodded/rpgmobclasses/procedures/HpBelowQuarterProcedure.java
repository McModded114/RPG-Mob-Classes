package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class HpBelowQuarterProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.25 >= (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)) {
			return true;
		}
		return false;
	}
}
