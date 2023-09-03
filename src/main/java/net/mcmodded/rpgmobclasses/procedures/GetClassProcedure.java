package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.Entity;

public class GetClassProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Class: " + entity.getPersistentData().getString("Class");
	}
}
