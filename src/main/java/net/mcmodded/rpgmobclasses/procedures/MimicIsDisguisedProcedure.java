package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.Entity;

public class MimicIsDisguisedProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity.getPersistentData().getBoolean("disguised") == true) {
			return true;
		}
		return false;
	}
}
