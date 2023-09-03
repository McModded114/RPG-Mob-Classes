package net.mcmodded.rpgmobclasses.procedures;

import net.minecraft.world.entity.Entity;

public class GetProfessionProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Profession: " + entity.getPersistentData().getString("Profession");
	}
}
