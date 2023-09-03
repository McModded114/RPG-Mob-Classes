package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.Entity;

public class MimicHurtProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getBoolean("disguised") == false) {
			entity.getPersistentData().putBoolean("disguised", true);
		}
	}
}
