package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcmodded.rpgmobclasses.init.RpgMobClassesModAttributes;

public class BecomeBlacksmithProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putString("Profession", "Blacksmith");
		((LivingEntity) entity).getAttribute(RpgMobClassesModAttributes.SMITHINGSKILL.get()).setBaseValue(1);
		CloseGuiProcedure.execute(entity);
	}
}
